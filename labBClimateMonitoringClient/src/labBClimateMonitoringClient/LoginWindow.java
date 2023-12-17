package labBClimateMonitoringClient;

import java.awt.BorderLayout;
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

	private JPanel contentPane;
	private JTextField txtNomeUtente;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
						UnregisteredMenuWindow.main(null);
						break;
					}
					case "Registered": {
						//TODO: leggere testo e verificare log in
						verifyLogIn();
						dispose();
						RegisteredMenuWindow.main(null);
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
		//TODO lookup nel DB
		System.out.println(txtNomeUtente.getText()+txtPassword.getText());
	}
}