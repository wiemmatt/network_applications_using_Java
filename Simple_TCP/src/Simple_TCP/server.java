package Simple_TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server{
	
	
	
	public server() throws Exception{
		// here the server is listening for connection
		ServerSocket server_socket= new ServerSocket(2020); //opening a new port
		System.out.println("Port 2020 has been opened");
		//once the connection arrives, it is going to accept the connection
		Socket socket= server_socket.accept();
		System.out.println("Client"+socket.getInetAddress()+ "has connected");
		
	//	I/O buffers
		BufferedReader in_socket = new BufferedReader(new InputStreamReader (socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter (socket.getOutputStream()), true);
         out_socket.println("welcome"); // send "Welcome" to the Client
         String message= in_socket.readLine(); // the server is going to receive the message (Thanks) from the client
 		System.out.println("Client says: " + message); // display Client's message in the console
 		socket.close();
		System.out.println("Socket is closed."); // the line, where we initiate disconnection
	}
	
	public static void main(String[] args) {
		try {
			new server();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
