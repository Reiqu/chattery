package chatteryClient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.text.BadLocationException;

import shared.Message;

public class Client extends Thread{	
	
	private Socket socket;
	private WriteThread write;
	private ReadThread read;
	
	private Window window;
	private Thread windowThread;
	
	private int channel;
	private String username;
	
	public static void main(String[] args) {
		new Client().start();
	}
	
	
	public void run() {
		try {
			createWindow();
		
		} finally {
			try {
				//closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void createWindow() {
		// TODO Auto-generated method stub
		this.window = new Window(this);
		this.windowThread = new Thread(this.window);
		windowThread.start();
		
	}
	
	public void openSession() {
		try {
			createSocket();
			createThreads();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMessageToServer(Message message) {
		System.out.println(write.isAlive());
		System.out.println(message);
		write.setOutgoingMessage(message);
		try {
			write.sendMessageIfExists();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addChatToWindow(Message message) {
		try {
			window.addChatContent(message);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createSocket() throws UnknownHostException, IOException {
			socket = new Socket("localhost", 8080);
		
	}
	
	private void createThreads() {
		read = new ReadThread(socket, this);
		read.setName("ReadThread");
		read.start();
		write = new WriteThread(socket, this);
		write.setName("WriteThread");
		write.start();
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the channel
	 */
	public int getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	
//	private void closeConnection() {
//		try {
//			socket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	

}
