import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean isLoggedOut = false;
	private JLabel userNameJLabel = new JLabel();
	private JLabel passwordJLabel = new JLabel();
	private JButton loginButton = new JButton();
	private JButton exitButton = new JButton();
	private JTextField userName = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JRadioButton buyerRadio = new JRadioButton();
	private JRadioButton sellerRadio = new 	JRadioButton();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private String userNameInput = null;
	private UserInfoItem.USER_TYPE UserType = UserInfoItem.USER_TYPE.Buyer;
	
	Login() {
		try {
			this.getContentPane().setLayout(null);
			userNameJLabel.setText("UserName");
			userNameJLabel.setBounds(new Rectangle(26, 52, 80, 18));
			passwordJLabel.setText("Password");
			passwordJLabel.setBounds(new Rectangle(23, 119, 80, 18));
			loginButton.setText("Login");
			loginButton.setBounds(new Rectangle(31, 212, 85, 28));
			loginButton.addActionListener(this::loginButton_actionPerformed);
			exitButton.setText("Exit");
			exitButton.setBounds(new Rectangle(180, 211, 97, 28));
			exitButton.addActionListener(e -> buttonExit_actionPerformed());
			userName.setBounds(new Rectangle(119, 52, 144, 22));
			password.setBounds(new Rectangle(118, 119, 147, 22));
			buyerRadio.setSelected(true);
			buyerRadio.setText("Buyer");
			buyerRadio.setBounds(new Rectangle(37, 164, 103, 26));
			sellerRadio.setText("Seller");
			sellerRadio.setBounds(new Rectangle(177, 162, 103, 26));
			this.getContentPane().add(userNameJLabel, null);
			this.getContentPane().add(passwordJLabel, null);
			this.getContentPane().add(loginButton, null);
			this.getContentPane().add(exitButton, null);
			this.getContentPane().add(userName, null);
			this.getContentPane().add(password, null);
			this.getContentPane().add(buyerRadio, null);
			this.getContentPane().add(sellerRadio, null);
			buttonGroup.add(buyerRadio);
			buttonGroup.add(sellerRadio);
			setSize(300, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loginButton_actionPerformed(ActionEvent e) {
		BufferedReader file;
		isLoggedOut = false;
		try {
			if (buyerRadio.isSelected())
			{
				UserType = UserInfoItem.USER_TYPE.Buyer;
				file = new BufferedReader(new FileReader("database\\BuyerInfo.txt"));
			} else
			{
				UserType = UserInfoItem.USER_TYPE.Seller;
				file = new BufferedReader(new FileReader("database\\SellerInfo.txt"));
			}
			userNameInput = userName.getText();
			String passwordInput = new String(password.getPassword());
			String LoginName = null;
			String row, UserName, Password;
			while ((row = file.readLine()) != null) {
				UserName = getUserName(row);
				Password = getPassword(row);
				if (UserName.compareTo(userNameInput) == 0 && Password.compareTo(passwordInput) == 0) {
					LoginName = UserName;
				}
			}
			if (LoginName != null) {
				this.dispose();
			}
		} catch (Exception ignored) {
		}
	}
	
	private String getUserName(String aline) {
		int seperator = aline.lastIndexOf(':');
		return aline.substring(0, seperator);
	}
	
	private String getPassword(String aline) {
		int sepearator = aline.lastIndexOf(':');
		return aline.substring(sepearator + 1);
	}
	
	String getUserName() {
		return userNameInput;
	}
	
	UserInfoItem.USER_TYPE getUserType() {
		return UserType;
	}
	
	boolean getIsLoggedOut() {
		return isLoggedOut;
	}
	
	private void buttonExit_actionPerformed() {
		isLoggedOut = true;
		dispose();
	}

}