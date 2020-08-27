package Simple_TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class client {

	
	public client() throws Exception{
		
		// For the client, we only created a regular socket
		Socket socket= new Socket("localhost", 2020);
		System.out.println("Successful connection"); //by seeing this line on console, we know that we have successfuly passed this line of code
		//I/O Streams
		BufferedReader in_socket = new BufferedReader(new InputStreamReader (socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter (socket.getOutputStream()), true);
        String message = in_socket.readLine(); //receive the message from the server, we read the message from the our socket with "Readline()"
        System.out.println("server says "+message);
        out_socket.println("thanks");
        socket.close();
        System.out.println("socket closed");
	}
	
	
	
	
	public static void main(String[] args) {
		try {
			new client();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}
}
