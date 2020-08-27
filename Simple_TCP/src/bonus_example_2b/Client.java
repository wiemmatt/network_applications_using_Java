//Clients connects to the server and is asked to guess a number from 1 to 20.
//All clients are going after the same number
//Clients try to guess a number as long as the number has not been guessed yet.
// once one of the clients guesses the secret number, all other clients can't guess anymore. Instead, the next time they send their guess, they are told that the number is already out.


package bonus_example_2b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public Client() throws Exception {
		
		Socket socket = new Socket("127.0.0.1",2020);
		System.out.println("Successful connection to the server.");
		
		// I/O streams
		BufferedReader in_socket = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter (new OutputStreamWriter (socket.getOutputStream()), true);
		Scanner keyboard = new Scanner (System.in);

		// SOLUTION
		String message; // messages from the server
		String user; // stores user's name
		String user_number; // this string will store user's guesses
		
		// 1st step: server is asking for the name of the user
		message = in_socket.readLine();
		System.out.println("Server: " + message);
		user = keyboard.nextLine();
		out_socket.println(user); // send the name to the server
		
		// This is how server's message will begin if this user gets the secret number:
		String winning_message = "User " + user;
		
		// 2nd step: guessing the number
		while(true) { // infinite loop we'll use break when the number is guessed
			message = in_socket.readLine();
			
			if(message.startsWith("Guess")) { // if the server sends "Guess a number [1-20]:", that means the number has not been guessed yet
				System.out.println("Server: Guess a number [1-20]: ");
				user_number = keyboard.nextLine(); // users enters their guess
				out_socket.println(user_number); // sending the guess to the server
			}
			else if (message.startsWith(winning_message)) { // we defined this in line 34
				System.out.println("You got it!!!");
				socket.close();
				System.out.println("Socket closed.");
				break;
			}
			else {
				System.out.println(message); // someone else got the number
				socket.close();
				System.out.println("Socket closed.");
				break;
			}
		}
		// SOLUTION
		
		
	}
	
	public static void main(String[] args) {
		try {
			new Client();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
