package chatteryClient;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import shared.Message;
import shared.MessageType;

import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;


public class Window extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField messageField;
	private Client client;
	private JTextPane chat;
	JButton btnSenden;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField usernameField;
	private JButton btnSaveUsername;
	private JTextField channelField;
	private JButton btnSaveChannel;
	private JButton btnStartSession;
	
	public Window(Client client) {
		this.client = client;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		}

		catch (ClassNotFoundException e) {
		}

		catch (InstantiationException e) {
		}

		catch (IllegalAccessException e) {

		}
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	// Füge die Nachrichten dem Chat hinzu und stelle den Nutzernamen in Fett dar
	public void addChatContent(Message message) throws BadLocationException {

		SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		StyleConstants.setBold(attributeSet, true);

		chat.setCharacterAttributes(attributeSet, true);
		Document doc = chat.getStyledDocument();

		doc.insertString(doc.getLength(), message.getName() + ": ", attributeSet);
		attributeSet = new SimpleAttributeSet();
		doc.insertString(doc.getLength(), message.getText() + "\n", attributeSet);
		
	}

	@Override
	public void run() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        JPanel main_content = new JPanel();
        getContentPane().add(main_content);
        main_content.setLayout(new CardLayout(0, 0));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        main_content.add(tabbedPane, "name_4910006454300");
        JPanel main_window_panel = new JPanel();
        main_window_panel.setBorder(new EmptyBorder(8, 8, 8, 8));
        tabbedPane.addTab("Nachrichten", null, main_window_panel, null);
        tabbedPane.setEnabledAt(0, false);
        main_window_panel.setLayout(new BoxLayout(main_window_panel, BoxLayout.Y_AXIS));
        JSplitPane chat_splitpane = new JSplitPane();
        chat_splitpane.setEnabled(false);
        chat_splitpane.setResizeWeight(0.95);
        chat_splitpane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        main_window_panel.add(chat_splitpane);
        JScrollPane scrollPane = new JScrollPane();
        chat_splitpane.setLeftComponent(scrollPane);
        JPanel panel_1 = new JPanel();
        scrollPane.setViewportView(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
        chat = new JTextPane();
        chat.setEditable(false);
        DefaultCaret caret = (DefaultCaret)chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel_1.add(chat);
        JPanel panel_unten = new JPanel();
        panel_unten.setBorder(new EmptyBorder(8, 8, 8, 8));
        chat_splitpane.setRightComponent(panel_unten);
        panel_unten.setLayout(new BoxLayout(panel_unten, BoxLayout.X_AXIS));
        messageField = new JTextField();
        messageField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()) {
                case 10: {
                    btnSenden.doClick();
                }
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        panel_unten.add(messageField);
        messageField.setColumns(10);
        btnSenden = new JButton("Senden");
        // Sende die Nachricht an den Server
        btnSenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message outgoing_Message = new Message(client.getUsername(), messageField.getText() , MessageType.Message, client.getChannel());
                messageField.setText("");
                client.sendMessageToServer(outgoing_Message);
            }
        });
        btnSenden.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_unten.add(btnSenden);
        /**
         *  Layout Design für die Einstellungen
         * 
         * 
         */
        
        JPanel settings_panel = new JPanel();
		tabbedPane.addTab("Einstellungen", null, settings_panel, null);
		settings_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tabbedPane.setSelectedIndex(1);

		Box horizontalBox = Box.createHorizontalBox();
		settings_panel.add(horizontalBox);
		
		Box verticalBox = Box.createVerticalBox();
		horizontalBox.add(verticalBox);
		
		JLabel lblTitle = new JLabel("Einstellungen");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

		verticalBox.add(lblTitle);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		JLabel lblSetName = new JLabel("Nutzername");
        lblSetName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		verticalBox.add(lblSetName);
		
		usernameField = new JTextField();
		verticalBox.add(usernameField);
		usernameField.setColumns(10);

		Component verticalStrut_01 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_01);

		
		btnSaveUsername = new JButton("Setze Nutzernamen");
        btnSaveUsername.setVisible(false);
        btnSaveUsername.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message outgoing_Message = new Message(usernameField.getText(), null , MessageType.SET_NAME, client.getChannel());
                client.sendMessageToServer(outgoing_Message);
                client.setUsername(usernameField.getText());;
            }
        });
		verticalBox.add(btnSaveUsername);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);
		
		JLabel lblSetChannel = new JLabel("Channel");
        lblSetChannel.setBackground(Color.WHITE);
        lblSetChannel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		verticalBox.add(lblSetChannel);
		
		Component verticalStrut_111 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_111);
		
		
		channelField = new JTextField();
        channelField.setHorizontalAlignment(SwingConstants.CENTER);
        channelField.setBackground(Color.WHITE);
		verticalBox.add(channelField);

		Component verticalStrut_1111 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1111);
		
		btnSaveChannel = new JButton("Setze Channel");
        btnSaveChannel.setVisible(false);
        btnSaveChannel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message outgoing_Message = new Message(null, null , MessageType.CHANGE_CHANNEL, Integer.parseInt(channelField.getText()));
                client.sendMessageToServer(outgoing_Message);
                client.setChannel(Integer.parseInt(channelField.getText()));
            }
        });
		verticalBox.add(btnSaveChannel);
		
		
		btnStartSession = new JButton("Starte Chat");
        // Wenn Nutzername und Channel eingegeben wurden, wird der Chat sichtbar gemacht und der Starte Chat Knopf verschwindet
        btnStartSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean usernameExists = false;
                boolean channelExists = false;
                // Abfrage, ob Nutzername und Channel eingegeben wurden
                if (usernameField.getText().length() > 0) {
                    usernameExists = true;
                }
                if (channelField.getText().length() > 0) {
                    channelExists = true;
                }
                if (channelExists && usernameExists == true) {
                    client.setChannel(Integer.parseInt(channelField.getText()));
                    client.setUsername(usernameField.getText());
                    // Mach Chat sichtbar
                    tabbedPane.setEnabledAt(0, true);
                    tabbedPane.setSelectedIndex(0);
                    btnStartSession.setEnabled(false);
                    btnStartSession.setVisible(false);
                    btnSaveChannel.setVisible(true);
                    btnSaveUsername.setVisible(true);
                    // Starte Verbindung zum Server
                    client.openSession();
                }
            }
        });
		
		verticalBox.add(btnStartSession);
		
		tabbedPane.setEnabledAt(1, true);
		
		tabbedPane.setSelectedIndex(1);
		
		setVisible(true);
		
        
        
        Component verticalStrut_2 = Box.createVerticalStrut(190);
        verticalBox.add(verticalStrut_2);
        
        setVisible(true);
	}

	private void init() {
		setSize(800, 600);
		setTitle("Chattery");
	}
}

