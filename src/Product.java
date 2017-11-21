// Kacie Anderson
// ITP 368, Fall 2017
// Assignment 07
// kqanders@usc.edu
// 10/06/2017

/**
 * This is our interface class. It is the blueprint for ShoppingCartItem, GroceryItem, and PharmacyItem.
 * 
 * @author Kacie Anderson
 * @author Shandira Ferguson
 */

interface Product {

	/**
	 * @return the String-value name of the item in the user’s cart.
	 */
	public String getName();

	/**
	 * @return the Price enum associated with the item in the user’s cart.
	 */
	public Price getPrice();

	/**
	 * Sets the String-value name of the item in the user’s cart to be equal to aName.
	 */
	public void setName(String aName);

	/** 
	 * Sets the value of the price to be equal to aPrice.
	 */
	public void setPrice(int aPrice);

	/**
	 * An overridden toString method that is used to differentiate ShoppingCartItem, 
	 * GroceryItem, and PharmacyItem from one another.
	 */
	@Override
	public String toString();

}
