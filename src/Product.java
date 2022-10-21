import java.util.ArrayList;

public class Product {
	@SuppressWarnings("unused")
	private ClassProductList classProductList;
	@SuppressWarnings("unused")
	private Trading trading;

	private String productName;
	private int productType;
	
	public Product() {}
	
	public Product(String productName, int productType) {
		super();
		this.productName = productName;
		this.productType = productType;
	}

	public String toString() {
		return productName + " " + productType;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}
	
	ArrayList<Trading> tradingList = new ArrayList<>();
	
	public void addTrading(Trading newTrading) {
		tradingList.add(newTrading);
	}
	
	public void accept(NodeVisitor visitor) {
		visitor.visitProduct(this);
	}
}
