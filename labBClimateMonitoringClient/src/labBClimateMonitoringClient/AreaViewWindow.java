package labBClimateMonitoringClient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
/**
 * Class that opens a new UI window for viewing all the climate parameters associated with a certain area
 * @author Roberto Alfonso Bernasconi
 * @author Andrea Magliocca
 */
public class AreaViewWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> list = null;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	/**
	 * Creates a new window
	 * Takes an ArrayList<ClimateParameters> as parameter to display in a scrollable list
	 * @param arrayList the list of ClimateParameters to be displayed
	 */
	public static void start(ArrayList<ClimateParameters> arrayList) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaViewWindow frame = new AreaViewWindow(arrayList);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private AreaViewWindow(ArrayList<ClimateParameters> arrayList) {
		
		if(arrayList.size()>0) {
			for (int i = 0; i<arrayList.size(); i++) {
				listModel.addElement(arrayList.get(i).toString());
			}
		} else {
			listModel.addElement("No parameters found");
		}
		
		
		list = new JList<String>(listModel);
	    list.setSelectedIndex(0);
	    		
		ActionListener listener = new ActionListener() {
			String cmd = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				cmd = e.getActionCommand();
				if(cmd == "Back") dispose();
				}
		};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(40, 30, 550, 280);
		contentPane.add(scrollPane);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(223, 350, 167, 65);
		btnNewButton_1.addActionListener(listener);
		btnNewButton_1.setActionCommand("Back");
		contentPane.add(btnNewButton_1);
	}
}

