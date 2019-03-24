import java.util.Calendar;

public class Order implements Comparable<Order>{
	private int orderNum;
	private int numItems;
	private int status;
	private Calendar cal;

	public Order() {
		orderNum = 1000;
		numItems = 1000;
		status = 1000;
		cal = null;
	}
	
	public Order(int orderNum, int numItems, int status, Calendar cal) {
		this.orderNum = orderNum;
		this.numItems = numItems;
		this.status = status;
		this.cal = cal;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		else if(this.getStatus() < o.getStatus()){
			return 1;
		}
		else if(this.getStatus() > o.getStatus()){
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
		if(status == 1) {
			result = result + "Order Status: Overnight Shipping"+ "\n";
		}
		else if(status == 2) {
			result = result + "Order Status: Rush Shipping"+ "\n";
		}
		else{
			result = result + "Order Status: Standard Shipping"+ "\n";
		}
		result = result + "Number of Items in the Order: " + numItems + "\n";
		return result;
	}
}
