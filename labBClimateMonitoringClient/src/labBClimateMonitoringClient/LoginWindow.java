package labBClimateMonitoringClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
	private JTextField txtInfoDb;

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
	public LoginWindow() {
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrInserireDatiPer = new JTextArea();
		txtrInserireDatiPer.setText("Oppure inserire dati per il log in:");
		txtrInserireDatiPer.setBounds(101, 122, 291, 30);
		contentPane.add(txtrInserireDatiPer);
		
		JButton btnNewButton = new JButton("Premere qui per accesso senza log in");
		btnNewButton.setBounds(130, 23, 242, 36);
		contentPane.add(btnNewButton);
		
		txtNomeUtente = new JTextField();
		txtNomeUtente.setText("nome utente");
		txtNomeUtente.setBounds(101, 178, 291, 19);
		contentPane.add(txtNomeUtente);
		txtNomeUtente.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(101, 207, 291, 19);
		contentPane.add(txtPassword);
		
		txtInfoDb = new JTextField();
		txtInfoDb.setText("host DB");
		txtInfoDb.setColumns(10);
		txtInfoDb.setBounds(101, 235, 291, 19);
		contentPane.add(txtInfoDb);
		
		JButton btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.setBounds(200, 278, 85, 21);
		contentPane.add(btnNewButton_1);
	}
}
