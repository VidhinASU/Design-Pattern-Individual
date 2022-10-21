//Facade design pattern

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Facade {

	int UserType;
	Product theSelectedProduct;
	int nProductCategory;
	ClassProductList productList;
	Person thePerson;
	HashSet<String> buyers = new HashSet<>();
	HashSet<String> sellers = new HashSet<>();
	HashMap<String, HashSet<String>> sellerOfProduct = new HashMap<>();
	HashMap<String, HashSet<Trading>> sellerPendingOffer = new HashMap<>();
	HashMap<String, HashSet<Trading>> sellerAcceptedOffer = new HashMap<>();
	HashMap<String, HashSet<Trading>> buyerAcceptedOffer = new HashMap<>();
	HashMap<String, HashSet<Trading>> buyerPendingOffer = new HashMap<>();
	
	public boolean login(UserInfoItem userInfoItem) {
		Login loginPanel = new Login();
		loginPanel.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				loginPanel.isLoggedOut = true;
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				loginPanel.isLoggedOut = true;
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		loginPanel.setModal(true);
		loginPanel.setVisible(true);
		userInfoItem.userName = loginPanel.getUserName();
		userInfoItem.userType = loginPanel.getUserType();
		return loginPanel.getIsLoggedOut();
	}
	
	public void acceptTrade(Product product, Trading trade, String seller) {
		sellerPendingOffer.get(seller).remove(trade);
		Trading findSellerTrade = null;
		for(Trading i : buyerPendingOffer.get(trade.buyer)) {
			if(i.seller.equals(seller)) {
				findSellerTrade = i;
			}
		}
		if(findSellerTrade != null)
			buyerPendingOffer.get(trade.buyer).remove(findSellerTrade);
		
		if(!sellerAcceptedOffer.containsKey(seller)) {
			sellerAcceptedOffer.put(seller, new HashSet<>());
		}
		sellerAcceptedOffer.get(seller).add(trade);
		if(!buyerAcceptedOffer.containsKey(trade.buyer)) {
			buyerAcceptedOffer.put(trade.buyer, new HashSet<>());
		}
		buyerAcceptedOffer.get(trade.buyer).add(trade);
	}

	public void addTrading(Product product, Date dueDate, String seller, int price, String buyer) {
		if(!sellerPendingOffer.containsKey(seller)) {
			sellerPendingOffer.put(seller, new HashSet<>());
		}
		sellerPendingOffer.get(seller).add(new Trading(product.getProductName(), dueDate, price, buyer, seller));
		if(!buyerPendingOffer.containsKey(buyer)) {
			buyerPendingOffer.put(buyer, new HashSet<>());
		}
		buyerPendingOffer.get(buyer).add(new Trading(product.getProductName(), dueDate, price, buyer, seller));
	}

	public void viewTrading(Trading trading) {
		TradingMenu TradingMenuUI = new TradingMenu();
		TradingMenuUI.showMenu(trading, thePerson);
	}

	public void decideBidding() {

	}

	public void discussBidding() {

	}

	public void submitBidding() {

	}

	public void remind() {
		Reminder reminder = new Reminder();
		reminder.showReminder(thePerson.getProductList());
	}

	public void createUser(UserInfoItem userInfo) {
		if(userInfo.userType == UserInfoItem.USER_TYPE.Buyer) {
			thePerson = new Buyer();
		} else {
			thePerson = new Seller();
		}
		thePerson.setUserName(userInfo.userName);
	}

	public void createProductList() {
		productList = new ClassProductList();
		productList.initProductList();
	}

	public void AttachProductToUser() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("database\\BuyerInfo.txt"));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(":");
				buyers.add(parts[0]);
			}
			bufferedReader.close();
			bufferedReader = new BufferedReader(new FileReader("database\\SellerInfo.txt"));
			while((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(":");
				sellers.add(parts[0]);
			}
			bufferedReader.close();
			bufferedReader = new BufferedReader(new FileReader("database\\UserProduct.txt"));
			while((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(":");
				
				if(sellers.contains(parts[0])) {
					if(!sellerOfProduct.containsKey(parts[1])) {
						sellerOfProduct.put(parts[1], new HashSet<>());
					}
					sellerOfProduct.get(parts[1]).add(parts[0]);
				}
				
				if(parts[0].equals(thePerson.userName)) {
					theSelectedProduct = findProductByName(parts[1]);
					if(theSelectedProduct != null) {
						thePerson.addProduct(theSelectedProduct);
					}
				}
			}
			bufferedReader.close();
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	private Product findProductByName(String pName) {
		ProductIterator iteraor = new ProductIterator(productList);
		return (Product) iteraor.find(pName);
	}

	public boolean SelectProduct() {
		ProductSelectionUI productUI = new ProductSelectionUI();
		theSelectedProduct = productUI.getSelectedProduct(thePerson.productList);
		thePerson.currentProduct = theSelectedProduct;
		nProductCategory = productUI.productCategory;
		return productUI.isLoggedOut();
	}

	public boolean productOperation() {
		thePerson.CreateProductMenu(theSelectedProduct, nProductCategory);
		return thePerson.showMenu();
	}
}
