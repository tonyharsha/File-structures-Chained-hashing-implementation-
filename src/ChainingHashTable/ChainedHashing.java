package ChainingHashTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class ChainedHashing {

	ImplementHashing table = new ImplementHashing();
	Scanner sc = new Scanner(System.in);
	public static void main(String args[]) throws IOException
	{
		
		ChainedHashing obj=new ChainedHashing();
		obj.hashTableFill();
		int choice;
		while(true)
		{
			System.out.println("**********************");
			System.out.println("1.Pack()");
			System.out.println("2.Unpack()");
			System.out.println("3.Search()");
			System.out.println("4.delete()");
			System.out.println("5.Update()");
			System.out.println("6.Exit");		
			System.out.println("**********************");
			System.out.println("Please enter your choice:");
			choice = obj.sc.nextInt();
			obj.sc.nextLine();
			switch(choice)
			{
			case 1:
				obj.pack();
				break;
			case 2:
				obj.unpack();
				break;
			case 3:
				obj.search();
				break;
			case 4:
				obj.delete();
				break;
			case 5:
				obj.update();
				break;
			case 6:
				System.out.println("You chose exit!");
				System.exit(0);
			default:
				System.out.println("Invalid Option");	
			}
		}
	}
	public void pack()throws FileNotFoundException {
		System.out.println("Enter key");
		int key=sc.nextInt();
		
		PrintWriter pw=new PrintWriter(new FileOutputStream(new File("BookData"),true));
		if(table.lookUp(key)) {
			System.out.println("The Key is Already exited");
			return;
		}
		else {
			System.out.println("Enter book name");
			String book_name=sc.next();
			System.out.println("Enter author name");
			String author_name=sc.next();
			System.out.println("Enter distributor name");
			String distributor_name=sc.next();
			System.out.println("Enter Noc");
			String Noc=sc.next();
			String value= book_name+"|"+author_name+"|"+distributor_name+"|"+Noc+"|";
			table.put(key, value);
			pw.println(key+"->"+value);
			pw.flush();
			pw.close();	
//			System.out.println(table);
		}
	}
	
	public void unpack() throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("BookData"));
		String s="";
		while((s=br.readLine())!=null) {
			System.out.println(s);
		}
		br.close();
	}
	
	public void search() {
		System.out.println("Enter the key to be searched");
		int key=sc.nextInt();
		String val=table.get(key);
		if(val!=null) {
			String[]s=val.split("\\|");
			System.out.println("The key value is "+ key);
			System.out.println("BookName :"+s[0]);
			System.out.println("AuthorName :"+s[1]);
			System.out.println("DistributorName :"+s[2]);
			System.out.println("No of copies :"+s[3]);
		}else {
			System.out.println("Key not found");
		}
	}
	
	public void delete()throws FileNotFoundException,IOException,NullPointerException {
		System.out.println("Enter the key to be deleted");
		int key=sc.nextInt();
		if(table.remove(key)){
			File file = new File("BookData");
			BufferedReader br = new BufferedReader(new FileReader(file));
			File temp = new File("temp");
			PrintWriter pw = new PrintWriter(temp);
			String s="";
			while((s=br.readLine())!=null) {
				int key1=Integer.valueOf(s.substring(0,1));
				if(key1!=key) {
					pw.println(s);
				}
			}
			pw.flush();
			pw.close();
			br.close();
			file.delete();
			temp.renameTo(file);
			System.out.println("deleted key "+ key);
		}
		else {
			System.out.println("the Key is not found");
		}
	}
	
	public void update() throws FileNotFoundException,IOException,NullPointerException {
		System.out.println("Enter the key to update data");
		int key=sc.nextInt();
		if(table.lookUp(key)) {
		System.out.println("Enter book name");
		String book_name=sc.next();
		System.out.println("Enter author name");
		String author_name=sc.next();
		System.out.println("Enter distributor name");
		String distributor_name=sc.next();
		System.out.println("Enter Noc");
		String Noc=sc.next();
		String value= book_name+"|"+author_name+"|"+distributor_name+"|"+Noc+"|";
		table.put(key, value);
		
		File file = new File("BookData");
		BufferedReader br = new BufferedReader(new FileReader(file));
		File temp = new File("temp");
		PrintWriter pw = new PrintWriter(temp);
		String s="";
		while((s=br.readLine())!=null) {
			int key1=Integer.valueOf(s.substring(0,1));
			if(key1!=key) {
				pw.println(s);
			}else {
				pw.println(key+"->"+value);
			}
		}
		pw.flush();
		pw.close();
		br.close();
		file.delete();
		temp.renameTo(file);
		System.out.println("File Modified");
		}
		else {
			System.out.println("the key value does not exists");
		}
	}
	public void hashTableFill()throws IOException{
		int key;
		String value="",s="";
		BufferedReader br=new BufferedReader(new FileReader("BookData"));
		while((s=br.readLine())!=null) {
			key=Integer.valueOf(s.substring(0,1));
			value=s.substring(3);
			table.put(key,value);
		}
//		System.out.println(table);
		br.close();
		
	}
	
	

}
