package bonus_example_2b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

	private Socket socket;
	private ServerMain server_main;
	
	public ServerThread (Socket socket, ServerMain server_main) {
		this.socket = socket;
		this.server_main = server_main;
	}
	
	@Override
	public void run() {
		try {
			
			int client_number = server_main.getClientNumber();
			
			System.out.println("Client " + client_number + ". has connected.");
			
			// I/O buffers:
			BufferedReader in_socket = new BufferedReader(new InputStreamReader (socket.getInputStream()));
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter (socket.getOutputStream()), true);
			
			// SOLUTION
			String message; // this string will store client's messages
			int secret_number = server_main.getSecretNumber(); // get the secret number from the server
			
			out_socket.println("Type in your name: "); // asking the client to type in their name
			String user = in_socket.readLine(); // reading user's name from socket
			
			while(true) {
				out_socket.println("Guess a number [1-20]: "); // telling the user to guess the number
				message = in_socket.readLine(); // reading user's guess
				
				if((Integer.parseInt(message)==secret_number)&&(!server_main.getGuessed())) { // user guessed the number + it hasn't been guessed by anyone else yet
					server_main.setGuessed(); // set boolean guessed to true since the number is out
					server_main.setWhoGuessedIt(user); // memorize who has guessed the number
					out_socket.println("User " + server_main.getWhoGuessedIt() + " has guessed the number!"); // sending to the client that guessed it
					System.out.println("User \" + server_main.getWhoGuessedIt() + \" has guessed the number!"); // print out the same info in the console
					socket.close(); // close the socket
					System.out.println("Client " + client_number + ". has disconnected.");
					break; // get out of the infinite loop
				}
				else if ((Integer.parseInt(message)==secret_number)&&(server_main.getGuessed())) { // this user guessed the number but someone else got it first
					out_socket.println("User " + server_main.getWhoGuessedIt() + " has already guessed the number!"); // telling this user who is the user that got the number before them
					socket.close(); // close the socket
					System.out.println("Client " + client_number + ". has disconnected.");
					break; // get out of the infinite loop
				}
				else if (server_main.getGuessed()) { // regular check if the number has been guessed yet (snice we only get to this point if we haven't checked the number - so we check if someone else has -> if nobody guessed it yet, then continue with the loop
					out_socket.println("User " +server_main.getWhoGuessedIt() + " has already guessed the number!"); // telling this user who is the user that got the number before them
					socket.close(); // close the socket
					System.out.println("Client " + client_number + ". has disconnected.");
					break; // get out of the infinite loop
				}
			}
			
			// SOLUTION		
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
