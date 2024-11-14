package fitness;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI implements ActionListener {
	
	private int count = 0;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	
	public GUI() {
		frame = new JFrame();
		
		JButton button = new JButton("Proceed");
		button.addActionListener(this);
		
		label = new JLabel("Pressed 0 times");
		
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Hello");
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public static void main (String[] args) {
		new GUI();
	}
	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Pressed "+count+" times");
	}
}