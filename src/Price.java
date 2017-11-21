// Kacie Anderson
// ITP 368, Fall 2017
// Assignment 07
// kqanders@usc.edu
// 10/06/2017

public enum Price {

	FIVE(5.00), TEN(10.00), FIFTEEN(15.00), TWENTY(20.00);

	public double price;

	Price(double price) {
		this.price = price;
	}

	Price(Price p){

		p.price = price;

	}

	/**
	 * @return the double value associated with the desired enum.
	 */
	public double getPrice() {

		switch (this) {
		case FIVE:
			return FIVE.price;
		case TEN:
			return TEN.price;
		case FIFTEEN:
			return FIFTEEN.price;
		case TWENTY:
			return TWENTY.price;
		default:
			return 0;
		}

	}
	
	public String getPriceString() {
		switch (this) {
		case FIVE:
			return "$5.00";
		case TEN:
			return "$10.00";
		case FIFTEEN:
			return "$15.00";
		case TWENTY:
			return "$20.00";
		default:
			return "";
		}
	}

}
