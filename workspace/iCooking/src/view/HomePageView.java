 package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.ButtonGroup;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import java.awt.Color;

public class HomePageView {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageView window = new HomePageView();
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
	public HomePageView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Main Dish");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(70, 128, 96, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSideDish = new JButton("Side Dish");
		btnSideDish.setBounds(176, 128, 96, 42);
		frame.getContentPane().add(btnSideDish);
		
		JButton btnSoup = new JButton("Soup");
		btnSoup.setBounds(282, 128, 96, 42);
		frame.getContentPane().add(btnSoup);
		
		JButton btnDessert = new JButton("Dessert");
		btnDessert.setBounds(388, 128, 96, 42);
		frame.getContentPane().add(btnDessert);
		
		textField = new JTextField();
		textField.setBounds(154, 41, 229, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnHome = new JButton("HOME");
		btnHome.setBounds(65, 40, 79, 32);
		frame.getContentPane().add(btnHome);
		
		JTextPane txtpnYouCanSearch = new JTextPane();
		txtpnYouCanSearch.setBackground(Color.WHITE);
		txtpnYouCanSearch.setForeground(Color.LIGHT_GRAY);
		txtpnYouCanSearch.setText("You can search recipes, add recipes or whatever you like in this application.");
		txtpnYouCanSearch.setBounds(48, 284, 468, 42);
		frame.getContentPane().add(txtpnYouCanSearch);
		
		JButton btnAddRecipe = new JButton("Add Recipe");
		btnAddRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddRecipe.setBounds(154, 219, 229, 32);
		frame.getContentPane().add(btnAddRecipe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 358, 584, -357);
		frame.getContentPane().add(scrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(567, 0, 17, 361);
		frame.getContentPane().add(scrollBar);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(393, 41, 96, 32);
		frame.getContentPane().add(btnSearch);
	}
}
