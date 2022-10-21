//Bridge Design Pattern, Factory Design Pattern
public class Buyer extends Person {
	
	public Buyer() {}

	public boolean showMenu() {
		return theProductMenu.showMenu();
	}

	@Override
	public ProductMenu CreateProductMenu(Product product, int productType) {
		productType = 0;
		if(productType == 0) {
			theProductMenu = new MeatProductMenu(product);
			theProductMenu.setProductMenu(theProductMenu);
		} else {
			theProductMenu = new ProduceProductMenu(product);
			theProductMenu.setProductMenu(theProductMenu);
		}
		return theProductMenu;
	}
}