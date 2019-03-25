package Objects;

import java.util.Calendar;

public class Order implements Comparable<Order>{
	private int orderNum;
	private int numItems;
	private int priority;
	private Calendar cal;
	private String status;

	public Order() {
		orderNum = 1000;
		numItems = 1000;
		priority = 1000;
		cal = null;
		status = null;
	}
	
	public Order(int orderNum, int numItems, int priority, Calendar cal, String status) {
		this.orderNum = orderNum;
		this.numItems = numItems;
		this.priority = priority;
		this.cal = cal;
		this.status = status;
	}
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}
	
	public boolean equals(Object o) {
        if(o == this) {
        	return true;
        }
        else if(!(o instanceof Order)) {
        	return false;
        }
        else {
        	Order order = (Order) o;
        	if(this.getOrderNum() == order.getOrderNum()) {
        		return true;
        	}
        	return false;
        }
    }
	
	public int compareTo(Order o) {
		if(this.equals(o)) {
			return 0;
		}
		else if(this.getPriority() < o.getPriority()){
			return 1;
		}
		else if(this.getPriority() > o.getPriority()){
			return -1;
		}
		else {
			if(this.cal.compareTo(o.cal)<0) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	public String toString() {
		String result = "";
		result = result + "Order Number: " + orderNum + "\n";
		if(priority == 1) {
			result = result + "Priority: Overnight Shipping"+ "\n";
		}
		else if(priority == 2) {
			result = result + "Priority: Rush Shipping"+ "\n";
		}
		else{
			result = result + "Priority: Standard Shipping"+ "\n";
		}
		result = result + "Number of Items in the Order: " + numItems + "\n";
		return result;
	}
}
