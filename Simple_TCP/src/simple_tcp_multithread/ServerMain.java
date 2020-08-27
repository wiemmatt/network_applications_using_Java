package simple_tcp_multithread;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public ServerMain() throws Exception{
		
		ServerSocket server_socket= new ServerSocket(2020);
		System.out.println("Port 2020 is now open");
		
		//infinite while loop: wait for new connection
		while(true){   // the point of this loop is to create a new thread for every new client that shows up
		Socket socket= server_socket.accept();	           // create a socket for every new client, here we create the socket object
		ServerThread server_thread= new ServerThread(socket, this);   // create a ServerThread object, we use a constructor from the ServerThread class 
		Thread thread= new Thread(server_thread);      // Java has a Thread class
		thread.start(); // Whenever we run into this line, we are going to call a run() method in the ServerThread class
		}
		
		
		}
	
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
