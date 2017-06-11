package view;




import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCuisine;
	private JTextField txtPreparationTime;
	private JTextField txtCookingTime;
	private JTextField txtServings;
	private JTextField txtCuisine_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtClickToAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddView frame = new AddView();
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
	public AddView() {
		setTitle("Add a new recipe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRecipe = new JLabel("recipe name:");
		lblRecipe.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 18));
		lblRecipe.setBounds(10, 10, 123, 20);
		contentPane.add(lblRecipe);
		
		JTextArea txtrAddARecipe = new JTextArea();
		txtrAddARecipe.setForeground(Color.LIGHT_GRAY);
		txtrAddARecipe.setText("Add a recipe");
		txtrAddARecipe.setBounds(118, 11, 233, 24);
		contentPane.add(txtrAddARecipe);
		
		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setForeground(Color.LIGHT_GRAY);
		txtrDescription.setText("description");
		txtrDescription.setBounds(20, 43, 357, 77);
		contentPane.add(txtrDescription);
		
		JLabel lblCatagories = new JLabel("catagories");
		lblCatagories.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblCatagories.setBounds(30, 130, 54, 15);
		contentPane.add(lblCatagories);
		
		JLabel lblNewLabel = new JLabel("cuisine");
		lblNewLabel.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblNewLabel.setBounds(30, 155, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPa = new JLabel("preparation time");
		lblPa.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblPa.setBounds(30, 180, 123, 15);
		contentPane.add(lblPa);
		
		JLabel lblCookingTime = new JLabel("cooking time ");
		lblCookingTime.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblCookingTime.setBounds(30, 205, 109, 15);
		contentPane.add(lblCookingTime);
		
		JLabel lblServings = new JLabel("servings");
		lblServings.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblServings.setBounds(30, 230, 54, 15);
		contentPane.add(lblServings);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(417, 0, 17, 259);
		contentPane.add(scrollBar);
		
		txtCuisine = new JTextField();
		txtCuisine.setForeground(Color.LIGHT_GRAY);
		txtCuisine.setText("cuisine");
		txtCuisine.setBounds(278, 320, 109, 21);
		contentPane.add(txtCuisine);
		txtCuisine.setColumns(10);
		
		txtPreparationTime = new JTextField();
		txtPreparationTime.setForeground(Color.LIGHT_GRAY);
		txtPreparationTime.setText("preparation time");
		txtPreparationTime.setColumns(10);
		txtPreparationTime.setBounds(122, 177, 256, 21);
		contentPane.add(txtPreparationTime);
		
		txtCookingTime = new JTextField();
		txtCookingTime.setForeground(Color.LIGHT_GRAY);
		txtCookingTime.setText("cooking time");
		txtCookingTime.setColumns(10);
		txtCookingTime.setBounds(122, 202, 256, 21);
		contentPane.add(txtCookingTime);
		
		txtServings = new JTextField();
		txtServings.setForeground(Color.LIGHT_GRAY);
		txtServings.setText("servings");
		txtServings.setColumns(10);
		txtServings.setBounds(122, 227, 256, 21);
		contentPane.add(txtServings);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(122, 127, 73, 21);
		contentPane.add(comboBox);
		
		JLabel lblIngredients = new JLabel("ingredients:");
		lblIngredients.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 18));
		lblIngredients.setBounds(10, 265, 123, 20);
		contentPane.add(lblIngredients);
		
		JLabel lblIngredientName = new JLabel("ingredient name");
		lblIngredientName.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblIngredientName.setBounds(30, 295, 90, 15);
		contentPane.add(lblIngredientName);
		
		JLabel lblQuality = new JLabel("quantity");
		lblQuality.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblQuality.setBounds(128, 295, 54, 15);
		contentPane.add(lblQuality);
		
		JLabel lblUnit = new JLabel("unit");
		lblUnit.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblUnit.setBounds(217, 295, 42, 15);
		contentPane.add(lblUnit);
		
		JLabel lblDescription = new JLabel("description");
		lblDescription.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblDescription.setBounds(298, 295, 90, 15);
		contentPane.add(lblDescription);
		
		txtCuisine_1 = new JTextField();
		txtCuisine_1.setForeground(Color.LIGHT_GRAY);
		txtCuisine_1.setText("cuisine");
		txtCuisine_1.setColumns(10);
		txtCuisine_1.setBounds(121, 150, 256, 21);
		contentPane.add(txtCuisine_1);
		
		textField = new JTextField();
		textField.setText("cuisine");
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setColumns(10);
		textField.setBounds(44, 320, 73, 21);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("cuisine");
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setColumns(10);
		textField_1.setBounds(122, 320, 73, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("cuisine");
		textField_2.setForeground(Color.LIGHT_GRAY);
		textField_2.setColumns(10);
		textField_2.setBounds(199, 320, 73, 21);
		contentPane.add(textField_2);
		
		JLabel lblPreparationSteps = new JLabel("preparation steps:");
		lblPreparationSteps.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 18));
		lblPreparationSteps.setBounds(10, 413, 214, 20);
		contentPane.add(lblPreparationSteps);
		
		JLabel lblStep = new JLabel("Step1:");
		lblStep.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 12));
		lblStep.setBounds(20, 443, 90, 15);
		contentPane.add(lblStep);
		
		txtClickToAdd = new JTextField();
		txtClickToAdd.setText("click to add step");
		txtClickToAdd.setForeground(Color.LIGHT_GRAY);
		txtClickToAdd.setColumns(10);
		txtClickToAdd.setBounds(40, 468, 347, 85);
		contentPane.add(txtClickToAdd);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\BF]GI]@PJNKGS40`8M554)0.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(109, 579, 86, 24);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\}(`KU332`ZM]F[`@DB}@V@0.png"));
		btnDelete.setBounds(230, 579, 86, 24);
		contentPane.add(btnDelete);
		
		JLabel label_3 = new JLabel("");
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(10, 623, 54, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(10, 743, 54, 15);
		contentPane.add(label_4);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\OLY{QZOGQ870P3I~2`%`Z}6.png"));
		btnNewButton_1.setBounds(393, 320, 20, 20);
		contentPane.add(btnNewButton_1);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\OLY{QZOGQ870P3I~2`%`Z}6.png"));
		button.setBounds(393, 498, 20, 20);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\OMHZKX@E9%)X3J{B@Q9P_~X.png"));
		button_1.setBounds(10, 498, 20, 20);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\OMHZKX@E9%)X3J{B@Q9P_~X.png"));
		button_2.setBounds(14, 319, 20, 20);
		contentPane.add(button_2);
	}
}
