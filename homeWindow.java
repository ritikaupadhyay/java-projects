import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class homeWindow extends JFrame implements ActionListener{

	//allows the user to chose its role (User/Admin) or "Exit" the program
	private JLabel roleTitle;
	
	private JPanel homePanel1;
	private JPanel homePanel2;
	private JButton userButton;
	private JButton adminButton;
	private JButton exitButton;
	
	
	void initLabel() {
		roleTitle = new JLabel("Please select a role: \n");
	}
	
	
	void initButton() {
		userButton = new JButton("User");
		adminButton = new JButton("Administrator");
		exitButton = new JButton("Exit Program");
		
		this.userButton.addActionListener(this);
		this.adminButton.addActionListener(this);
		this.exitButton.addActionListener(this);
	}
	
	
	void initPanel () {
		homePanel1 = new JPanel();
		homePanel2 = new JPanel();
		
		//add components to this panel
		homePanel1.add(roleTitle);
		homePanel1.add(userButton);
		homePanel1.add(adminButton);
		
		homePanel2.add(exitButton);
	}
	
	
	void initAll() {
		this.setLayout(new GridLayout(2, 1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Home");
		this.setSize(4000,4000);
		
		initLabel();
		initButton();
		initPanel();
		this.add(homePanel1);
		this.add(homePanel2);
		this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==userButton) 
			new userWindow();
		
		else if (e.getSource()==adminButton)
			new adminWindow();
		
		else if(e.getSource()==exitButton)
			homeWindow.this.dispose();
		
	}

}
