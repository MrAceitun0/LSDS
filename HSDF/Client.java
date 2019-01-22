import java.net.*;
import java.io.*;

/**
 * Implementation of a TCP Client
 */
public class Client {

    public static void main(String argv[]) {
        if (argv.length != 3) {
            System.err.println("Format: Client <serverIP> <serverPort> <blockNumber>");
            System.exit(-1);
        }
        Socket socket = null;
        try {
			// We get the IP of the server
            InetAddress serverIP = InetAddress.getByName(argv[0]);
            
            // We get the port of the server
            int serverPort = Integer.parseInt(argv[1]);
            
            // We get the message
            String block = argv[2];
            
            // We create the socket and establish the connection
            socket = new Socket(serverIP, serverPort);
            
            // We establish a timeout of 30seconds
            socket.setSoTimeout(30000);
            System.out.println("CLIENT: Established connection with "
                    + serverIP.toString() + " port " + serverPort);
            
            // Establishing input channel
            BufferedReader socketInputChannel = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            // Establishing output channel
            PrintWriter socketOutputChannel = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("CLIENT: Asking allocation for block " + block);
            
            // Sending the message to the server
            socketOutputChannel.println(block);
            
            // Receiving the message from the server 
            String ipList = socketInputChannel.readLine();
            System.out.println("CLIENT: Block will be allocated in Datanodes " + ipList);
            
            // We close the channels and the socket
            socketOutputChannel.close();
            socketInputChannel.close();
            
        } catch (SocketTimeoutException e) {
            System.err.println("30 secs without activity");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
