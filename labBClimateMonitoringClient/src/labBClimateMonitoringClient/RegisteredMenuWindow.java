package labBClimateMonitoringClient;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
/**
 * Class that opens a new UI window for registered users.
 * @author Roberto Alfonso Bernasconi
 * @author Andrea Magliocca
 */
public class RegisteredMenuWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
/**
 * Creates the window
 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisteredMenuWindow frame = new RegisteredMenuWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private RegisteredMenuWindow() {
		
		ActionListener listener = new ActionListener() {
			String cmd = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				cmd = e.getActionCommand();
				switch (cmd) {
				case "View Area":
					AreaViewWindow.start(ConnectionManager.getInstance().viewArea(textField_4.getText()));
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
						listModel.addElement(iaList.get(i).getName());
					}
					if (iaList.size() == 0) {
						listModel = new DefaultListModel<String>();
						listModel.addElement("No parameters found");
					}
					
					break;
				case "Insert Parameters":
					ParameterAddingWindow.start();
					break;
				case "Create New Monitoring Centre":
					MonitoringCentreCreationWindow.start();
					break;
				case "Create New Area":
					AreaCreationWindow.start();
					break;
				}
			}	
		};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Insert Parameters");
		btnNewButton_3.setBounds(199, 227, 252, 62);
		btnNewButton_3.addActionListener(listener);
		btnNewButton_3.setActionCommand("Insert Parameters");
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Create New Area");
		btnNewButton_2.setBounds(199, 371, 252, 62);
		btnNewButton_2.addActionListener(listener);
		btnNewButton_2.setActionCommand("Create New Area");
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Create New Monitoring Centre");
		btnNewButton_1.setBounds(199, 299, 252, 62);
		btnNewButton_1.addActionListener(listener);
		btnNewButton_1.setActionCommand("Create New Monitoring Centre");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("View Area");
		btnNewButton.setBounds(22, 155, 252, 62);
		btnNewButton.addActionListener(listener);
		btnNewButton.setActionCommand("View Area");
		contentPane.add(btnNewButton);
		
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
		lblNewLabel.setBounds(313, 67, 85, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Or Insert Latitude and Longitude:");
		lblNewLabel_1.setBounds(313, 109, 156, 13);
		contentPane.add(lblNewLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(312, 177, 228, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Insert Full Name of Area to View:");
		lblNewLabel_2.setBounds(313, 159, 156, 21);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_4 = new JButton("Search for Area");
		btnNewButton_4.setBounds(22, 78, 252, 62);
		btnNewButton_4.addActionListener(listener);
		btnNewButton_4.setActionCommand("Search for Area");
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(94, 10, 462, 47);
		contentPane.add(scrollPane);
		
		JList<String> listView = new JList<String>(listModel);
		scrollPane.setViewportView(listView);
	}
}
