// Visitor Design Pattern
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReminderVisitor extends NodeVisitor {

	private Reminder m_Reminder;

	public ReminderVisitor(Reminder reminder) {
		m_Reminder = reminder;
	}

	public void visitProduct(Product product) {
		
	}

	public void visitTrading(Trading trading, int i) {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int ntoday = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(trading.dueDate);
		int nDueDate = calendar.get(Calendar.DAY_OF_YEAR);
		if (i == 0) {
			m_Reminder.listUpcoming.add(trading.tradeName + " Due Date is " + trading.getDueDateString());
		} else {
			m_Reminder.listOverdue.add(trading.tradeName + " Due Date is " + trading.getDueDateString());
		}
	}

	public void visitFacade(Facade facade) {
		if(PTBS.mainFacade.buyerPendingOffer.get(PTBS.mainFacade.thePerson.getUserName()) != null) {
			for (Trading trade : PTBS.mainFacade.buyerPendingOffer.get(PTBS.mainFacade.thePerson.getUserName())) {
				trade.accept(this, 0);
			}
		}
		if(PTBS.mainFacade.buyerAcceptedOffer.get(PTBS.mainFacade.thePerson.getUserName()) != null) {
			for (Trading trade : PTBS.mainFacade.buyerAcceptedOffer.get(PTBS.mainFacade.thePerson.getUserName())) {
				trade.accept(this, 1);
			}
		}
	}

}
