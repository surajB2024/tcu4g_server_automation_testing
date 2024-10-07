import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceServerSimulation {
    private static final String SERVER_IP = "20.219.88.214";
    private static final int SERVER_PORT = 6200;
    private static String LOGIN_PACKET = "55AA,1,2,1724233342,868794064363483,89916490634628941878,ACON4IA202200099433,2.0.14,ACCDEV14012081966,UF,PrimaryIP,PrimaryPort,SecondaryIP,SecondaryPort,1,225,1108,437,511,500,1,1,350,1108,525,511,500,0,0.000000,-,0.000000,-,24.3,0.0,1,C,0,1,0";
    private static final int RESPONSE_TIMEOUT = 3000; // Increased to 20 seconds
    private static final int POST_CHUNK_WAIT = 5000; // 5 seconds wait after chunks
    private static final int CHUNK_SIZE = 1024;
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            System.out.println("Connected to server.");

            // Send initial LOGIN_PACKET
            String response = sendPacketAndGetResponse(out, in, LOGIN_PACKET);
            System.out.println("Initial response: " + response);

            if (response.isEmpty()) {
                System.out.println("No response received from server. Exiting.");
                return;
            }

            // Parse f.length
            int fLength = parseFLength(response);
            System.out.println("f.length: " + fLength);

            // Send chunk requests
            sendChunkRequests(out, in, fLength);

            // Wait for 5 seconds after sending chunks
            System.out.println("Waiting for 5 seconds after sending chunks...");
            Thread.sleep(POST_CHUNK_WAIT);

            // Send LOGIN_PACKET again
            out.write(LOGIN_PACKET.getBytes());
            out.flush();
            System.out.println("Sent LOGIN_PACKET: " + LOGIN_PACKET);

            // Handle primary IP/port update
            boolean isModified = handleServerResponse(in, true);
            if (isModified) {
                out.write(LOGIN_PACKET.getBytes());
                out.flush();
                System.out.println("Sent updated LOGIN_PACKET after primary IP/Port update: " + LOGIN_PACKET);
            }

            // Handle secondary IP/port update
            isModified = handleServerResponse(in, false);
            if (isModified) {
                out.write(LOGIN_PACKET.getBytes());
                out.flush();
                System.out.println("Sent final updated LOGIN_PACKET after secondary IP/Port update: " + LOGIN_PACKET);
            }

            System.out.println("Final LOGIN_PACKET: " + LOGIN_PACKET);

        } catch (IOException | InterruptedException e) {
            System.err.println("Error in communication with server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String sendPacketAndGetResponse(OutputStream out, InputStream in, String packet) throws IOException {
        out.write(packet.getBytes());
        out.flush();
        System.out.println("Sent packet: " + packet);
        return readResponseWithTimeout(in);
    }

    private static String readResponseWithTimeout(InputStream in) throws IOException {
        StringBuilder response = new StringBuilder();
        byte[] buffer = new byte[1024];
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < RESPONSE_TIMEOUT) {
            if (in.available() > 0) {
                int bytesRead = in.read(buffer);
                response.append(new String(buffer, 0, bytesRead));
                if (response.toString().endsWith("\n")) {
                    break;  // Assuming each response ends with a newline
                }
            } else {
                try {
                    Thread.sleep(100);  // Wait a bit before checking again
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted while waiting for response");
                }
            }
        }

        return response.toString().trim();
    }

    private static int parseFLength(String response) {
        String[] parts = response.split(",");
        return Integer.parseInt(parts[8]);
    }

    private static void sendChunkRequests(OutputStream out, InputStream in, int fLength) throws IOException {
        int startByte = 0;
        while (startByte < fLength) {
            int remainingBytes = fLength - startByte;
            int chunkSize = Math.min(CHUNK_SIZE, remainingBytes);

            String request = String.format("55AA,2,%d,%d,FF", startByte, chunkSize);
            String response = sendPacketAndGetResponse(out, in, request);

            System.out.println("Chunk request: " + request);
            System.out.println("Response length: " + response.length());
            System.out.println();

            startByte += chunkSize;
        }
    }

    private static final int SERVER_RESPONSE_TIMEOUT = 20000; // 20 seconds timeout for server response

    private static boolean handleServerResponse(InputStream in, boolean isPrimary) throws IOException {
        long startTime = System.currentTimeMillis();
        String response = "";

        while (System.currentTimeMillis() - startTime < SERVER_RESPONSE_TIMEOUT) {
            if (in.available() > 0) {
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                response += new String(buffer, 0, bytesRead);

                // Check if we have a complete response
                if (response.endsWith("\n")) {
                    break;
                }
            } else {
                try {
                    Thread.sleep(100);  // Wait a bit before checking again
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted while waiting for response");
                }
            }
        }

        if (response.isEmpty()) {
            System.out.println("No response received from server within the timeout period.");
            return false;
        }

        System.out.println("Received response: " + response);

        Pattern pattern;
        if (response.startsWith("*SET#CHTP#") || response.startsWith("*SET#CIP1#")) {
            pattern = Pattern.compile("\\*SET#C(?:HTP|IP1)#(.+)#(\\d+)#");
        } else if (response.startsWith("SET PU:") || response.startsWith("SET EU:")) {
            pattern = Pattern.compile("SET (?:PU|EU):http://(.+):(\\d+)");
        } else {
            System.out.println("No IP/Port change required.");
            return false;
        }

        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            String newIp = matcher.group(1);
            String newPort = matcher.group(2);
            updateLoginPacket(newIp, newPort, isPrimary);
            return true;
        }

        System.out.println("Failed to parse server response for IP/Port update.");
        return false;
    }

    private static void updateLoginPacket(String newIp, String newPort, boolean isPrimary) {
        String[] parts = LOGIN_PACKET.split(",");
        if (isPrimary) {
            parts[10] = newIp;    // Update PrimaryIP
            parts[11] = newPort;  // Update PrimaryPort
        } else {
            parts[12] = newIp;    // Update SecondaryIP
            parts[13] = newPort;  // Update SecondaryPort
        }
        LOGIN_PACKET = String.join(",", parts);
        System.out.println("Updated LOGIN_PACKET: " + LOGIN_PACKET);
    }
}