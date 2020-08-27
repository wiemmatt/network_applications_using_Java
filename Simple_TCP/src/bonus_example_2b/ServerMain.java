package bonus_example_2b;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public ServerMain() throws Exception{
		
		ServerSocket server_socket = new ServerSocket(2020);
		System.out.println("Port 2020 is now open.");
		
		while(true) {
			Socket socket = server_socket.accept();
			ServerThread server_thread = new ServerThread(socket, this);
			Thread thread = new Thread(server_thread);
			thread.start();
		}
		
	}
	
	// SOLUTION
	
	private int secret_number = (int)(Math.random()*20+1); // generating the secret number
	
	public int getSecretNumber() { // return the secret number to ServerThread
		return secret_number;
	}
	
	private boolean guessed = false; // track whether the number has been guessed yet; at the beginning, it's not guessed so it's "false"
	
	public void setGuessed() { // as soon as a user guesses the number, call this method to set "guessed" as "true"
		guessed = true;
	}
	
	public boolean getGuessed() { // used by ServerThread to check if the number has already been guessed
		return guessed;
	}
	
	private String user; // stores the user that guessed the number
	
	public void setWhoGuessedIt(String user) { // once a user guesses the number, memorize who was it
		this.user = user;
	}
	
	public String getWhoGuessedIt() { // used to tell other users who had guessed the number
		return user;
	}
	
	// SOLUTION
	
	private int clientnumber = 1;
	
	public int getClientNumber() {
		return clientnumber++;
	}
	
	public static void main(String[] args) {
		try {
			new ServerMain();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
