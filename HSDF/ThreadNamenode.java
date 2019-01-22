import java.net.*;
import java.util.Hashtable;
import java.util.Random;
import java.io.*;

/**
 * Thread that implements the response to one connection to the server
 */
public class ThreadNamenode extends Thread {

	Socket socket = null;
	Hashtable<Integer, String> t = new Hashtable<Integer, String>();
	
	public ThreadNamenode(Socket s) {
		
		// Assigning the connection socket 
		this.socket = s;
		
		// Defining the hashtable to store the IP related to each datanode
		t.put(0, "127.0.0.0");
		t.put(1, "127.0.0.1");
		t.put(2, "127.0.0.2");
		t.put(3, "127.0.0.3");
		t.put(4, "127.0.0.4");
		t.put(5, "127.0.0.5");
		t.put(6, "127.0.0.6");
		t.put(7, "127.0.0.7");
		t.put(8, "127.0.0.8");
		t.put(9, "127.0.0.9");
	}

	public void run() {
		try {
			
			// Establishing input channel
            BufferedReader socketInputChannel = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Establishing output channel
            PrintWriter socketOutputChannel = new PrintWriter(socket.getOutputStream(), true);

			// Receiving client message and generate the response
            String blockString = socketInputChannel.readLine();
            
			// Sending message to the client
            Random r = new Random();
            socketOutputChannel.println(t.get(r.nextInt(10)) + " / " + t.get(r.nextInt(10)) + " / " + t.get(r.nextInt(10)));
            
			// Closing the channels
            socketInputChannel.close();
            socketOutputChannel.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

