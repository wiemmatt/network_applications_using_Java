package bonus_example_2a;


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
			
			String message;
	        int secret_number = (int)(Math.random()*20+1); //return a number between 1 and 10
			
			do {
				out_socket.println("Guess a number [1-20]: ");
				message = in_socket.readLine();
			} while (!(Integer.parseInt(message)==secret_number));
			
			out_socket.println("You got it!!!");
			System.out.println("Secret number is out. Exiting the app.");
			
			socket.close();
			System.out.println("Client " + client_number + ". has disconnected.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
