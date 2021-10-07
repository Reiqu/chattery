package chatteryClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import shared.Message;

public class ReadThread extends Thread {

	private Socket socket;
	private Client client;

	private ObjectInputStream input;

	public ReadThread(Socket socket, Client client) {

		this.socket = socket;
		this.client = client;

	}

	public void run() {
		try {
			// Erstelle InputStream, um eingehenden Message-Objekte zu lesen
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while (true) {
				listen();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void listen() throws IOException, ClassNotFoundException {
		Message newMessage = (Message) input.readObject();
		client.addChatToWindow(newMessage);
		newMessage = null;
	}
}
