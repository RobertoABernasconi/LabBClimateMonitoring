package labBClimateMonitoringClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Component;

public class UnregisteredMenuWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();

	public static void start() {
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
				case "View Area":
					AreaViewWindow.start(ConnectionManager.getInstance().viewArea(textField_4.getText()));
					break;
				case "Register":
					RegistrationWindow.start();
					break;
				case "Search for Area":
					ArrayList<InterestedArea> iaList = null;
					if (textField.getText().isBlank()) {
						if(!(textField_1.getText().isBlank() || textField_2.getText().isBlank())) {
							iaList = ConnectionManager.getInstance().searchArea(Double.parseDouble(textField_1.getText()),Double.parseDouble(textField_2.getText()));
						}
					} else {
						iaList = ConnectionManager.getInstance().searchArea(textField.getText());
					}
					for (int i = 0; i < iaList.size(); i++) {
						listModel.addElement(iaList.get(i).toString());
					}
					if (iaList.size() == 0) {
						listModel = new DefaultListModel<String>();
						listModel.addElement("No parameters found");
					}
					
					break;
				case "Back":
					LoginWindow.start();
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
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(92, 10, 462, 47);
		contentPane.add(scrollPane);
		
		JList<String> listView = new JList<String>(listModel);
		scrollPane.setViewportView(listView);
	}
}
