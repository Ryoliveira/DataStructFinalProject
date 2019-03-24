import java.util.Calendar;

public class HeapTest {
	public static void main(String[] args) {
		Heap heap = new Heap(100);
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2019, 12, 19);
		Order o1 = new Order(1,5,2,cal1);
		String s = o1.toString();
		System.out.println(s);
		heap.insert(o1);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2019, 12, 20);
		Order o2 = new Order(2,4,1,cal2);
		heap.insert(o2);
		Calendar cal3 = Calendar.getInstance();
		cal3.set(2019, 12, 21);
		Order o3 = new Order(3,7,3,cal3);
		heap.insert(o3);
		Calendar cal4 = Calendar.getInstance();
		cal4.set(2019, 12, 22);
		Order o4 = new Order(4,3,1,cal4);
		heap.insert(o4);
		heap.displayArray();
		heap.remove(1);
		heap.displayArray();
	}
}
