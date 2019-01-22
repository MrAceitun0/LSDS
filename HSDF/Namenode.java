import java.io.IOException;
import java.net.*;

/**
 * Code that implements a TCP socket server simulating a HDFS Namenode
 */
public class Namenode {

    public static void main(String argv[]) {
        if (argv.length != 1) {
            System.err.println("Format: Namenode <port>");
            System.exit(-1);
        }
        ServerSocket socket = null;
        int serverPort = Integer.parseInt(argv[0]);
        try {
        	// Creating the server socket
            socket = new ServerSocket(serverPort);
            // Establishing a timeout of 30 seconds
            socket.setSoTimeout(30000);

            while (true) {
            	// Waiting for connections
            	System.out.println("Namenode is listening");
            	Socket clientSocket = socket.accept();
            	System.out.println("Connected to client");
            	
            	// Creating a new thread -ThreadServidor-, passing the new connection
            	ThreadNamenode thread = new ThreadNamenode(clientSocket);
            	
                // Running the new thread using the method start()
            	thread.start();            	
            }
        } catch (SocketTimeoutException e) {
            System.err.println("30 seconds without activity");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
        	// Closing the server socket
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
