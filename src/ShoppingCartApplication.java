// Kacie Anderson
// ITP 368, Fall 2017
// Assignment 07
// kqanders@usc.edu
// 10/06/2017

/** This is the class that contains the main method. Main creates an instance of 
 * ShoppingCartController.java, and calls it’s “go()” method.
 * 
 * @author Kacie Anderson
 * @author Shandira Ferguson
 */

public class ShoppingCartApplication {
	public static void main(String[] args) {
		ShoppingCartGui gui = new ShoppingCartGui();
		gui.go();
		System.exit(0);
	}
}
