// Kacie Anderson
// ITP 368, Fall 2017
// Assignment 07
// kqanders@usc.edu
// 10/06/2017

public class GeneralItem implements Product {

	private String name;
	private Price price;

	public GeneralItem(String aName, Price aPrice) {
		this.name = aName;
		this.price = aPrice;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Price getPrice() {
		return price;
	}

	@Override
	public void setName(String aName) {
		name = aName;
	}

	@Override
	public void setPrice(int aPrice) {
		price.price = aPrice;
	}

	@Override
	public String toString() {
		return "General Item [name = " + name + ", price = $" + price.getPrice() + "]" + "\n";
	}

}
