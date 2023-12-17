package labBClimateMonitoringClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class UnregisteredMenuWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnregisteredMenuWindow frame = new UnregisteredMenuWindow();
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
	private UnregisteredMenuWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ActionListener listener = new ActionListener() {
			String cmd = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				cmd = e.getActionCommand();
				switch (cmd) {
				case "View Area": //viewArea(dati)
					AreaViewWindow.main(null);
					break;
				case "Register":
					RegistrationWindow.main(null);
					break;
				case "Search for Area":
					textField_3.setEnabled(true);
					textField_3.setText("testo da mostrare"); //TODO mostrare risultato dal DB
					break;
				case "Back":
					LoginWindow.main(null);
					dispose();
					break;
						
					}	
				}	
			};
		
		JButton btnNewButton = new JButton("View Area");
		btnNewButton.addActionListener(listener);
		btnNewButton.setActionCommand("View Area");
		btnNewButton.setBounds(120, 192, 137, 66);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(listener);
		btnNewButton_1.setBounds(120, 369, 137, 21);
		btnNewButton_1.setActionCommand("Register");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search for Area");
		btnNewButton_2.addActionListener(listener);
		btnNewButton_2.setBounds(120, 74, 137, 66);
		btnNewButton_2.setActionCommand("Search for Area");
		contentPane.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(313, 80, 217, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Latitude");
		textField_1.setBounds(313, 121, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Longitude");
		textField_2.setBounds(434, 121, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setToolTipText("Result");
		textField_3.setBounds(76, 10, 217, 34);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Insert Area Name:");
		lblNewLabel.setBounds(313, 67, 127, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Or Insert Latitude and Longitude:");
		lblNewLabel_1.setBounds(313, 109, 217, 13);
		contentPane.add(lblNewLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(302, 239, 228, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Insert Full Name of Area:");
		lblNewLabel_2.setBounds(302, 222, 217, 21);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_3 = new JButton("Back to log in");
		btnNewButton_3.setBounds(392, 369, 127, 21);
		btnNewButton_3.addActionListener(listener);
		btnNewButton_3.setActionCommand("Back");
		contentPane.add(btnNewButton_3);
	}
}
