// Iterator Method, Visitor Design Pattern
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ClassProductList extends ArrayList<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassProductList() {}
	
	public void initProductList() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("database\\ProductInfo.txt"));
			String row;
			while((row = bufferedReader.readLine()) != null) {
				String[] parts = row.split(":");
				Product p = null;
				if (parts[0].equals("Meat")) {
					p = new Product(parts[1], 0);
				} else {
					p = new Product(parts[1], 1);
				}
				this.add(p);
			}
			bufferedReader.close();
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}