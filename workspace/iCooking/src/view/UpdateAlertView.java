package view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class UpdateAlertView {

	private JFrame frame;
	private JTextField txtDoYouWant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAlertView window = new UpdateAlertView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateAlertView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350,250);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(65, 180, 70, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("No");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(210, 180, 70, 20);
		frame.getContentPane().add(btnNewButton_1);
		
		txtDoYouWant = new JTextField();
		txtDoYouWant.setForeground(Color.BLACK);
		txtDoYouWant.setColumns(10);
		txtDoYouWant.setHorizontalAlignment(SwingConstants.CENTER);
		txtDoYouWant.setBackground(Color.LIGHT_GRAY);
		txtDoYouWant.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
		txtDoYouWant.setText("Do you want to update this recipe");
		txtDoYouWant.setBounds(10, 25, 317, 73);
		frame.getContentPane().add(txtDoYouWant);
	}
}

