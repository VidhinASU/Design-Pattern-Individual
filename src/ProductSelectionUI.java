import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ProductSelectionUI extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product selectedProduct;
	int productCategory = 0;
	private boolean isLoggedOut = false;
	private JComboBox<Product> productNameCombo = new JComboBox<>();
	private JLabel jLabel1 = new JLabel();
	private JButton OKButton = new JButton();
	@SuppressWarnings("unused")
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JButton buttonLogout = new JButton();
	
	public ProductSelectionUI() {
		try {
			this.getContentPane().setLayout(null);
			productNameCombo.setBounds(new Rectangle(155, 41, 203, 22));
			jLabel1.setText("ProductName");
			jLabel1.setBounds(new Rectangle(39, 44, 85, 18));
			OKButton.setText("OK");
			OKButton.setBounds(new Rectangle(78, 139, 79, 29));
			OKButton.addActionListener(this::OKButton_actionPerformed);
			buttonLogout.setText("Logout");
			buttonLogout.setBounds(new Rectangle(224, 140, 73, 31));
			buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
			this.getContentPane().add(productNameCombo, null);
			this.getContentPane().add(jLabel1, null);
			this.getContentPane().add(OKButton, null);
			this.getContentPane().add(buttonLogout, null);
			setSize(420, 238);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void OKButton_actionPerformed(ActionEvent e) {
		selectedProduct = (Product) productNameCombo.getSelectedItem();
		dispose();
	}
	
	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut = true;
		dispose();
	}
	
	boolean isLoggedOut() {
		return isLoggedOut;
	}
	
	Product getSelectedProduct(ClassProductList productList) {
		ProductIterator it = new ProductIterator(productList);
		@SuppressWarnings("unused")
		Product product;
		while (it.hasNext()) {
			productNameCombo.addItem((Product)it.Next());
		}
		setVisible(true);
		return selectedProduct;
	}
}