// Kacie Anderson
// ITP 368, Fall 2017
// Assignment 07
// kqanders@usc.edu
// 10/06/2017

public class PharmacyItem extends GeneralItem {

	public PharmacyItem(String aName, Price aPrice) {
		super(aName, aPrice);
	}

	@Override
	public String toString() {
		return "Pharmacy Item [name = " + super.getName()+ ", price = $" + super.getPrice().price + "]" + "\n";
	}


}
