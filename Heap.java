import java.util.ArrayList;

public class Heap {

    private int heap_size;
    private ArrayList<Order> heap;

    private void heapify(int i) {
    	int l = getLeft(i);
    	int r = getRight(i);
    	int indexMax = i;
    	if(l <= heap_size && (heap.get(l).compareTo(heap.get(i))>0)) {
    		indexMax = l;
    	}
    	if(r <= heap_size && heap.get(r).compareTo(heap.get(indexMax))>0) {
    		indexMax = r;
    	}
    	if(i != indexMax) {
    		swap(i,indexMax);
    		heapify(indexMax);
    	}
    } 

    private void heap_increase_key(int i, Order order){
    	if(order.compareTo(heap.get(i))>0) {
    		heap.set(i, order);
    		while(i>1 && heap.get(getParent(i)).compareTo(heap.get(i))<0){
    			swap(i,getParent(i));
    			i = getParent(i);
    		}
    	}
    }

    /**Constructors*/

    public Heap(int length){
    	heap_size = 0;
    	heap = new ArrayList<Order>(length);
    	for(int i = 0; i<length; i++) {
    		Order o = new Order();
    		heap.add(i, o);
    	}
    }    

    /**Mutators*/

    public void build_heap(){
    	int n = heap_size;
    	for(int i = (int)Math.floor(n/2); i>=1; i--) {
    		heapify(i);
    	}
    }    

    public void insert(Order order){
    	heap_size++;
    	Order o = new Order();
    	heap.set(heap_size, o);
    	heap_increase_key(heap_size, order);
    }   

    public void remove(int index){
    	swap(index,heap_size);
    	heap_size--;
    	heapify(index);
    }    

    public ArrayList<Order> sort() {
    	int n = heap_size;
    	for(int i = n; i>1; i--) {
    		swap(i,1);
    		heap_size--;
    		heapify(1);
    	}
    	return heap;
    }
    
    public void swap(int i, int j) {
    	Order temp = heap.get(i);
    	heap.set(i, heap.get(j));
    	heap.set(j, temp);
    }
    
    /**Accessors*/

    public Order getMax(){
    	build_heap();
    	return heap.get(1);
    }
    
    public int getParent(int i) {
    	return (int)Math.floor(i/2);
    }

    public int getLeft(int i) {
    	return 2*i;
    }

    public int getRight(int i) {
    	return 2*i+1;
    }  
  
    public int getSize() {
    	return heap_size;
    }

    public Order getElement(int i) {
    	return heap.get(i);
    }

    /**Additional Operations*/
    
    public String toString(){
		String result = "";
		for(int i = 1; i<=heap_size; i++) {
			result = result + heap.get(i).getOrderNum();
		}
		return result;
    }  

    public void displayArray(){
    	for(int i = 1; i<=heap_size; i++) {
			System.out.println("Order Number: " + heap.get(i).getOrderNum());
			if(heap.get(i).getStatus() == 1) {
				System.out.println("Order Status: Overnight Shipping");
			}
			else if(heap.get(i).getStatus() == 2) {
				System.out.println("Order Status: Rush Shipping");
			}
			else{
				System.out.println("Order Status: Standard Shipping");
			}
			System.out.println("Number of Items in the Order: " + heap.get(i).getNumItems() + "\n");
		}
    }  
}