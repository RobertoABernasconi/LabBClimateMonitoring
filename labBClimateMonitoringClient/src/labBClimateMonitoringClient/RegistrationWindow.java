package labBClimateMonitoringClient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
/**
 * Class that opens a new UI window for registering users. A registered user has access to more application functions
 */
public class RegistrationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

/**
 * Creates the window
 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationWindow frame = new RegistrationWindow();
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
	private RegistrationWindow() {
		
		ActionListener listener = new ActionListener() {
			String cmd = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				cmd = e.getActionCommand();
				switch (cmd) {
					case "Register":
						if(!ConnectionManager.getInstance().registerUser(registeredObject())) {
							ErrorWindow.start("Error: could not register user");
						}
						break;
					case "Cancel":
						dispose();
						break;
						
				}
			}	
		};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(31, 10, 60, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surname");
		lblNewLabel_1.setBounds(31, 33, 60, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SSN");
		lblNewLabel_2.setBounds(31, 56, 60, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mail");
		lblNewLabel_3.setBounds(31, 79, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("User Name");
		lblNewLabel_4.setBounds(31, 102, 70, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setBounds(31, 125, 90, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Centre Name");
		lblNewLabel_6.setBounds(31, 148, 90, 13);
		contentPane.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(196, 7, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(196, 30, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(196, 53, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(196, 76, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(196, 99, 96, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(196, 122, 96, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(196, 145, 96, 19);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(132, 338, 85, 21);
		btnNewButton.addActionListener(listener);
		btnNewButton.setActionCommand("Register");
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(364, 338, 85, 21);
		btnNewButton_1.addActionListener(listener);
		btnNewButton_1.setActionCommand("Cancel");
		contentPane.add(btnNewButton_1);
	}
	
	private Registered registeredObject() {
		Registered result = null;
		result = new Registered(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(), textField_6.getText());
		return result;
	}
}