package labBClimateMonitoringClient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeUtente;
	private JTextField txtPassword;

	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private LoginWindow() {
		ActionListener listener = new ActionListener() {
			String cmd = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				cmd = e.getActionCommand();
				switch (cmd) {
					case "Unregistered": {
						dispose();
						UnregisteredMenuWindow.start();
						break;
					}
					case "Registered": {
						verifyLogIn();
						dispose();
						break;
					}	
				}	
			}
		};
		
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Click here to start without logging in");
		btnNewButton.setBounds(176, 72, 277, 66);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(listener);
		btnNewButton.setActionCommand("Unregistered");
		
		txtNomeUtente = new JTextField();
		txtNomeUtente.setText("user name");
		txtNomeUtente.setBounds(176, 255, 291, 19);
		contentPane.add(txtNomeUtente);
		txtNomeUtente.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(176, 284, 291, 19);
		contentPane.add(txtPassword);
		
		JButton btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.setBounds(290, 362, 85, 34);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setActionCommand("Registered");
		btnNewButton_1.addActionListener(listener);
		
		JTextArea txtrOppureInserireDati = new JTextArea();
		txtrOppureInserireDati.setEditable(false);
		txtrOppureInserireDati.setWrapStyleWord(true);
		txtrOppureInserireDati.setText("Otherwise, insert log in info:");
		txtrOppureInserireDati.setBounds(176, 207, 277, 22);
		contentPane.add(txtrOppureInserireDati);
	}
	
	
	private void verifyLogIn() {
		if(ConnectionManager.getInstance().userLogin(txtNomeUtente.getText(), txtPassword.getText())) {
			RegisteredMenuWindow.start();	
		}
		else {
			ErrorWindow.start("Failed to log in, try again");
		}
	}
}