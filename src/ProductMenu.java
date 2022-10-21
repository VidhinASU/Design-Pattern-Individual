//Bridge Design Pattern, Factory Design Pattern
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JDialog;

public abstract class ProductMenu extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Product product;
	protected boolean isLoggedOut = true;
	
	JComboBox<Trading> tradingCombox = new JComboBox<>();
	public ProductMenu() {
		try {
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    setModal(true);
	    setSize(503,294);
	}
	
	void TradingAddButton_actionPerformed() {
		refresh();
	}
	void TradingViewButton_actionPerformed() {
		Trading theAss=(Trading)tradingCombox.getSelectedItem() ;
		PTBS.mainFacade.viewTrading(theAss);
	}
	void refresh() {
		tradingCombox.removeAllItems() ;
	    for (Trading assignment : product.tradingList) {
	      tradingCombox.addItem(assignment);
	    }
	}

	@SuppressWarnings("unused")
	private void buttonChangeProduct_actionPerformed(ActionEvent e) {
		isLoggedOut=false;
	    dispose();
	}

	@SuppressWarnings("unused")
	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut=true;
	    dispose();
	}
	boolean isLoggedOut() {
		return isLoggedOut;
	}
	


	public abstract boolean showMenu();

	public abstract void showAddButton();

	public abstract void showViewButton();

	public abstract void showRadioButton();

	public abstract void showLabels();

	public abstract void showComboxes();

	protected abstract void setProductMenu(ProductMenu theProductMenu);

}