package ChainingHashTable;

public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	ImplementHashing hm=new ImplementHashing();
	hm.put(1,"harsha");
	hm.put(2,"hari");
	System.out.println(hm);
	hm.remove(2);
	System.out.println(hm.get(2));
	}

}
