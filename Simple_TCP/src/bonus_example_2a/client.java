//Client connects to the server and is asked to guess a number from 1 to 20.
// Each client gets their own secret number to guess
package bonus_example_2a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {

	public client() throws Exception {
		
		Socket socket = new Socket("127.0.0.1",2020);
		System.out.println("Successful connection to the server.");
		
		// I/O streams
		BufferedReader in_socket = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter (new OutputStreamWriter (socket.getOutputStream()), true);
		Scanner keyboard = new Scanner (System.in);
		String user_number;
		
		while((in_socket.readLine()).startsWith("Guess")) { // this condition will be met as long as we're hitting the wrong number, as long as we are getting a sentence beginning with "Guess", we need to be in this loop. As soon aswe get something else, we get "You got it"
			System.out.println("Server says: Guess a number [1-20].");
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
