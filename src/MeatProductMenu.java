import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class MeatProductMenu extends ProductMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Product product;
	protected boolean isLoggedOut = true;
	ProductMenu theProductMenu;
	Product currentProduct;
	
	JComboBox<String> tradingCombox = new JComboBox<>();
	JButton tradingAddButton = new JButton();
	private JButton logoutButton = new JButton();
	
	public void setProductMenu(ProductMenu productMenu) {
		theProductMenu = productMenu;
	}
	
	public MeatProductMenu(Product p) {
		
		currentProduct = p;
		try {
		    this.getContentPane().setLayout(null);
		    this.setTitle("");
		    logoutButton.setText("Logout");
		    logoutButton.setBounds(new Rectangle(120, 215, 210, 37));
		    logoutButton.addActionListener(this::buttonLogout_actionPerformed);
		    this.getContentPane().add(logoutButton, null);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    setModal(true);
	    setSize(523,300);
	}

	public boolean showMenu() {
		Iterator<Trading> tradingIterator = currentProduct.tradingList.iterator();
		theProductMenu.product = currentProduct;
		Trading trading;
		while (tradingIterator.hasNext()) {
			trading = (Trading) tradingIterator.next();
			theProductMenu.tradingCombox.addItem(trading);
		}
		showAddButton();
		showComboxes();
		showRadioButton();
		showLabels();
		setVisible(true);
		return isLoggedOut();
	}
	
	void TradingAddButton_actionPerformed() {
		String seller = (String) tradingCombox.getSelectedItem();
		PTBS.mainFacade.addTrading(currentProduct, new Date(2022-1900, 9, 10), seller, 20, PTBS.mainFacade.thePerson.getUserName());
		refresh();
	}
	void TradingViewButton_actionPerformed() {
		Trading offering=(Trading)tradingCombox.getSelectedItem();
		PTBS.mainFacade.viewTrading(offering);
	}
	void refresh() {
		tradingCombox.removeAllItems();
		if(PTBS.mainFacade.sellerOfProduct.get(currentProduct.getProductName()) != null) {
		    for(String seller : PTBS.mainFacade.sellerOfProduct.get(currentProduct.getProductName())) {
		    	tradingCombox.addItem(seller);
		    }
		}
	}

	@SuppressWarnings("unused")
	private void buttonChangeProduct_actionPerformed(ActionEvent e) {
		isLoggedOut=false;
	    dispose();
	}

	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut=true;
	    dispose();
	}
	boolean isLoggedOut() {
		return isLoggedOut;
	}

	public void showAddButton() {
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Make an offer to seller");
		tradingAddButton.setBounds(new Rectangle(240, 54, 210, 37));
		this.getContentPane().add(tradingAddButton, null);
	}

	public void showViewButton() {
		
	}

	public void showRadioButton() {
		
	}

	public void showLabels() {
	}

	public void showComboxes() {
		tradingCombox.setBounds(new Rectangle(20, 57, 210, 37));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}
}