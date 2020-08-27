//we created a simple game. We generated a random number between 1 and 10 on the server, and then 
// we are going to ask the client to guess the number.
package simple_tcp_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {

	
	public client() throws Exception{
		
		// For the client, we only created a regular socket
		Socket socket= new Socket("localhost", 2020);
		System.out.println("Successful connection"); //by seeing this line on console, we know that we have successfuly passed this line of code
		
		//I/O Streams
		BufferedReader in_socket = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter (new OutputStreamWriter (socket.getOutputStream()), true);
		Scanner keyboard = new Scanner (System.in);
		
	    String message= in_socket.readLine();
	    System.out.println("Server says "+message);
	    message = keyboard.nextLine();
	    out_socket.println(message);
		
		socket.close();
		System.out.println("Socket closed.");
		
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
