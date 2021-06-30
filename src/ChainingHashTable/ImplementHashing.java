package ChainingHashTable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class ImplementHashing {
   public class Entry{
	   int key;
	   String value;
	   public Entry(int key,String value) {
		   this.key=key;
		   this.value=value;
	   }
	   @Override
	   public String toString() {
		   return value;
	   }
   }
   LinkedList<Entry>[]entries=new LinkedList[5];
   
   public void put(int key,String value) {
	   int index=hash(key);
	   if(entries[index]==null) {
		   entries[index]=new LinkedList<>();
	   }
	  var bucket=entries[index];
	  for(var entry:bucket) {
		  if(entry.key==key) {
			  entry.value=value;
			  return;
		  }
	  }
	  bucket.addLast(new Entry(key,value));
   }
   
   public String get(int key) {
	   int index=hash(key);
	   var bucket=entries[index];
	   if(bucket!=null) {
		   for(var entry:bucket) {
			   if(entry.key==key) {
				   return entry.value;
			   }
		   }  
	   }
	   return null;
   }
   
   public boolean remove(int key) {
	   int index=hash(key);
	   var bucket=entries[index];
	   if(bucket==null) {
		   return false;
	   }
	   for(var entry:bucket) {
		   if(entry.key==key) {
			   bucket.remove(entry);
			   return true;
		   }
	   }
	   return false;
   }
   public int hash(int key) {
	   return key%entries.length;
   }
   
   public boolean lookUp(int key) {
	   int index=hash(key);
	   if(entries[index]!=null) {
		   for(var en:entries[index]) {
			   if(en!=null) {
				   if(en.key==key) {
					   return true;
				   }
			   }
		   } 
	   }
	   return false;
   }
   Scanner scan = new Scanner(System.in);
   @Override
   public String toString() {
	   return Arrays.toString(entries);
   }
   public void print() {
	   for(var ent:entries) {
		   if(ent!=null) {
		   for(var r:ent) {
			   System.out.println(r.key+"   ->  "+r.value);
		   }
		   }
	   }
   }
}