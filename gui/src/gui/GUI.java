package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI {
	
	public static void main(String[]args) {
		
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(10, 60, 80, 25);
		panel.add(passLabel);
		
		JTextField userText = new JTextField();
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		
		JPasswordField passText = new JPasswordField();
		passText.setBounds(100,60,165,25);
		panel.add(passText);
		
		JButton button = new JButton("Log-in");
		button.setBounds(185, 100, 80, 25);
		panel.add(button);
		
		JLabel success = new JLabel("");
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		frame.setVisible(true);
		
		
	}
}
