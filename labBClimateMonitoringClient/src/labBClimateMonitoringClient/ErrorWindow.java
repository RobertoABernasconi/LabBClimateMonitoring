package labBClimateMonitoringClient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
/**
 * Class that opens a new UI window to show errors found during execution
 * @author Roberto Alfonso Bernasconi
 * @author Andrea Magliocca
 */
public class ErrorWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
/**
 * Creates the error UI window, showing an error message
 * @param error The error to be shown
 */
	public static void start(String error) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorWindow frame = new ErrorWindow(error);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private ErrorWindow(String error) {
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(123, 129, 85, 21);
		btnNewButton.addActionListener(listener);
		contentPane.add(btnNewButton);
		
		textField = new JTextField(error);
		textField.setEditable(false);
		textField.setBounds(40, 10, 239, 109);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}