//Bridge Design Pattern, Factory Design Pattern
class Seller extends Person {

	public Seller() {}

	public boolean showMenu() {
		return theProductMenu.showMenu();
	}

	@Override
	public ProductMenu CreateProductMenu(Product product, int productType) {
		productType = 1;
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