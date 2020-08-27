//we created a simple game. We generated a random number between 1 and 10 on the server, and then 
// we are going to ask the client to guess the number.
package Simple_TCP_2;

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
		String user_number;
		
		while((in_socket.readLine()).startsWith("Guess")) { // this condition will be met as long as we're hitting the wrong number, as long as we are getting a sentence beginning with "Guess", we need to be in this loop. As soon aswe get something else, we get "You got it"
			System.out.println("Server says: Guess a number [1-10].");
			user_number = keyboard.nextLine();
			out_socket.println(user_number);
		}
		
		System.out.println("You got it!!!");
		
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
