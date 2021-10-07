package chatteryClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.Message;
import shared.MessageType;

public class WriteThread extends Thread {

	private Socket socket;
	private Client client;
	private ObjectOutputStream output;

	private Message outgoingMessage;

	public WriteThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;

	}

	public void run() {

		try {
			createOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setOutgoingMessage(new Message(client.getUsername(), null, MessageType.INITIAL, client.getChannel()));
		try {
			sendMessageIfExists();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessageIfExists() throws IOException {
		if (getOutgoingMessage() != null) {

			System.out.println("New outgoing message: " + "\n Name: " + getOutgoingMessage().getName()
					+ "\n Nachricht: " + getOutgoingMessage().getText() + "\n Channel: "
					+ getOutgoingMessage().getChannel() + "\n Type: " + getOutgoingMessage().getType());

			output.writeObject(getOutgoingMessage());
			setOutgoingMessage(null);
		} else {
			System.err.println("No message found. Cannot send anything");
		}
	}

	private void createOutput() throws IOException {
		// Erstelle Outputstream, um ausgehende Message-Objekte zu senden
		output = new ObjectOutputStream(socket.getOutputStream());
	}

	/**
	 * @return the outgoingMessage
	 */
	public Message getOutgoingMessage() {
		return outgoingMessage;
	}

	/**
	 * @param outgoingMessage the outgoingMessage to set
	 */
	public void setOutgoingMessage(Message outgoingMessage) {
		this.outgoingMessage = outgoingMessage;
	}
}
