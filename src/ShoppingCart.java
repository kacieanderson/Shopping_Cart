// Kacie Anderson
// ITP 368, Fall 2017
// Assignment 07
// kqanders@usc.edu
// 10/06/2017

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

public class ShoppingCart {
	LinkedList<Product> list;
	ListIterator<Product> iterator;
	Map<Product, Price> map;
	GeneralItem item;
	Price price;

	/** This is the “model” part of the MVC design. It has one constructor that
	 *  initializes global variables “LinkedList<Product> list,” “ListIterator<Product> iterator,” 
	 *  and “Map<Product,Price> map.” 
	 *  
	 *  @author Kacie Anderson
	 *  @author Shandira Ferguson
	 */

	public ShoppingCart() {
		list = new LinkedList<>();
		map = new LinkedHashMap<>();
		iterator = list.listIterator();
	}

	/**
	 * Takes in the product name and price from ShoppingCartController and uses them to create 
	 * a new ShoppingCartItem. Then, it adds that ShoppingCartItem to the list and map.
	 * 
	 * @param aName - the product name
	 * @param price2 - the product price
	 */
	public void addGeneralItem(String aName, Price price2) {
		price = price2;
		item = new GeneralItem(aName, price2);
		list.add(item);
		map.put(item, price);
	}

	public void addGroceryItem(String aName, Price price2) {
		price = price2;
		item = new GroceryItem(aName, price);
		list.add(item);
		map.put(item, price);
	}

	public void addPharmacyItem(String aName, Price price2) {
		price = price2;
		item = new PharmacyItem(aName, price);
		list.add(item);
		map.put(item, price);
	}

	/**
	 * Removes all items from the list and map.
	 */
	public void empty() {
		while (!list.isEmpty()) {
			list.removeFirst();
			map.clear();
		}
	}

	/**
	 * @return the entire list.
	 */
	public LinkedList<Product> getList() {
		return list;
	}

	/**
	 * @return the integer value of the number of items in the user’s shopping cart.
	 */
	public int listSize() {
		int size = list.size();
		return size;
	}

	/**
	 * Gets the value of the Price for each item in the user’s cart, then adds them together 
	 * and returns the sum.
	 */
	public String calculateTotalCost(){
		double sum = 0; 
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getPrice().price;
		}
		return Double.toString(sum) + "0";
	}

	/**
	 * Takes in an already-instantiated linked list and sets the current list to be equal to it. 
	 */
	public void setList(LinkedList<Product> aList) {
		list = aList;
	}

	/**
	 * @return all the contents of the list.
	 */
	@Override
	public String toString() {
		String a = "";
		Product temp;
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			a += temp.toString() + " ";
		}
		return a;
	}

}
