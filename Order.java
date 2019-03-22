import java.util.Date;

public class Order {
	private String orderNum;
	private int numItems;
	private int status;
	private Date date;
	
	public Order(String orderNum, int numItems, int status, Date date) {
		this.orderNum = orderNum;
		this.numItems = numItems;
		this.status = status;
		this.date = date;
	}
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean equals(Order o) {
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
		else if(this.getStatus() > o.getStatus()){
			return 1;
		}
		else if(this.getStatus() < o.getStatus()){
			return -1;
		}
		else {
			if(date.after(o.getDate())) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}
