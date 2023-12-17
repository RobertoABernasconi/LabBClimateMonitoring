package labBClimateMonitoringClient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class AreaViewWindow extends JFrame {

	private JPanel contentPane;
	private JList<String> list = null;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	
	//TODO Funzione prende un ClimateParameters e prepara i campi per mostrarli

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaViewWindow frame = new AreaViewWindow();
					
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
	private AreaViewWindow() {
		
		//eliminare questo, test
		for (int i = 0; i<100; i++) {
			listModel.addElement(Integer.toString(((int) (Math.random()*10)) %10));
		}
		//fine test
		
		list = new JList<String>(listModel);
	    list.setSelectedIndex(0);
	    
		
		
		ActionListener listener = new ActionListener() {
			String cmd = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				cmd = e.getActionCommand();
				System.out.println("click");
				if(cmd == "Add") {
					showAreaData();
					System.out.println("Add"); //test, riga da eliminare
				}
				else if (cmd == "Cancel") {
					dispose();
				}
			}
		};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(42, 30, 548, 201);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("New element");
		btnNewButton.setBounds(90, 290, 167, 65);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(listener);
		btnNewButton.setActionCommand("Add"); //test
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(325, 290, 167, 65);
		btnNewButton_1.addActionListener(listener);
		btnNewButton_1.setActionCommand("Cancel");
		contentPane.add(btnNewButton_1);
	}
	
	private void showAreaData() {
		listModel.clear();
		listModel.addElement(Integer.toString(((int) (Math.random()*10)) %10)); //TODO inserire qui risultati della query come String
	    return;
	}
}

