// Kacie Anderson
// ITP 368, Fall 2017
// Assignment 07
// kqanders@usc.edu
// 10/06/2017


import java.util.Arrays;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ShoppingCartGui extends Application {
	private BorderPane borderPane;
	private GridPane newItemPane, radioButtonPane, leftButtonsGrid;
	private ObservableList<String> cartItems;
	private VBox topBox, leftBox, bottomBox, rightBox, finishBox;
	private Button checkOutButton, emptyCartButton, addButton, newCartButton;
	private Label thankYouLabel, headerLabel, listViewHeader, currentTotalLabel, 
		currentTotalString, newItemHeader, itemPriceLabel, errorLabel;
	private ListView<String> listView;
	private CheckBox addFiveCheck;
	private ToggleGroup priceGroup;
	private ComboBox<String> itemType;
	private TextField itemNameField;
	private Stream<Price> priceStream;
	private int columnCounter, listNumber;
	private Price price;
	private ShoppingCart cart;
	
	public ShoppingCartGui() {		
		// Default constructor
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("5/10/15/20 Store");
		cart = new ShoppingCart();
		
		// Initiate all VBoxes/GridPanes that will be added to the borderPane
		topBox = new VBox();
		leftBox = new VBox();
		bottomBox = new VBox();
		rightBox = new VBox();
		finishBox = new VBox();
		newItemPane = new GridPane();
		radioButtonPane = new GridPane();
		leftButtonsGrid = new GridPane();

		makeUI();
		manageLayout();
		addButtonListeners();
		addUIListeners();

		// Create main border pane & add associated VBoxes
		borderPane = new BorderPane();
		borderPane.setTop(topBox);
		borderPane.setLeft(leftBox);
		borderPane.setRight(rightBox);
		borderPane.setBottom(bottomBox);
		
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.show();
	}	
	
	/**
	 * Initiate the UI components for the list of cart items, the group of "Item Price" radio
	 * buttons, the check box for adding 5 items, the combo box for item types, and the text
	 * field for item description.
	 */
	public void makeUI() {
		
		cartItems = FXCollections.observableArrayList();
		listView = new ListView<String>(cartItems); // List of cart items
		listView.setFocusTraversable(false);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listNumber = 1;
		
		priceGroup = new ToggleGroup(); // Radio buttons for choosing a price
		
		addFiveCheck = new CheckBox("Add 5 quantities of this item to my cart");
		addFiveCheck.setDisable(true);
		
		itemType = new ComboBox(); // Combo box for choosing item type
		itemType.setValue("Type of Item");
		itemType.getItems().addAll("General", "Grocery", "Pharmacy");
		
		itemNameField = new TextField(); // User-entered item name/description
		itemNameField.setDisable(true);
		
		priceStream = Arrays.stream(Price.values());
		
		makeLabelsAndButtons();
		makeUIAesthetic();
		
	}
	
	/**
	 * Initiate all labels and buttons in the UI
	 * 
	 * BINDING - currentTotalLabel is binded to currentTotalString, which is never
	 * visible but updates with the current total price after the user adds a new
	 * item to the cart.
	 */
	public void makeLabelsAndButtons() {
		checkOutButton = new Button("Check Out");
		addButton = new Button("Add to cart");
		emptyCartButton = new Button("Empty Cart");
		newCartButton = new Button("Create a new cart");
		
		addButton.setDisable(true); // Enabled after user has entered new item info
		checkOutButton.setDisable(true); // Enabled when there are items in cart
		emptyCartButton.setDisable(true); // Enabled when there are items in cart
		
		errorLabel = new Label(" "); // Text updates if user enters bad input
		thankYouLabel = new Label(" "); // Updates/appears at checkout

		// Header labels
		headerLabel = new Label("Welcome to The 5/10/15/20 Store!");
		listViewHeader = new Label("Items in your cart");
		newItemHeader = new Label("New Item");
		itemPriceLabel = new Label("Item Price:");
		
		// BINDING
		currentTotalString = new Label("Current Total: $0.00");
		currentTotalLabel = new Label();
		currentTotalLabel.textProperty().bind(currentTotalString.textProperty());
		currentTotalLabel.setFont(Font.font(18));
		currentTotalLabel.setPadding(new Insets(20,0,10,0));
		
	}
	
	/**
	 * Set padding, font, style, width/height, etc. For all components of the UI.
	 */
	public void makeUIAesthetic() {
		
		// ----------- LABELS --------------
		errorLabel.setPadding(new Insets(20));
		errorLabel.setFont(Font.font(28));
		errorLabel.setTextFill(Color.RED);
		thankYouLabel.setPadding(new Insets(0,0,20,0));
		thankYouLabel.setFont(Font.font(28));
		thankYouLabel.setStyle("-fx-text-alignment: center");
		headerLabel.setPadding(new Insets(20));
		headerLabel.setFont(Font.font(28));
		listViewHeader.setPadding(new Insets(5, 0, 5, 0));
		listViewHeader.setFont(Font.font(14));
		newItemHeader.setFont(Font.font(18));
		
		addFiveCheck.setPadding(new Insets(0,0,25,0)); // Spacing for check box
		addFiveCheck.setAlignment(Pos.CENTER_LEFT);
		
		listView.setMaxHeight(130); // Height/width of list of cart items
		listView.setMinWidth(300);
		itemType.setMinWidth(100); 
		
		itemNameField.setMinWidth(250); 							 // Textfield for item name/description:
		itemNameField.setStyle("-fx-prompt-text-fill: "			 // Make sure prompt text is visible even
				+ "derive(-fx-control-inner-background, -30%);"); // if the text field is selected/in focus
		itemNameField.setPromptText("Item name/description");
		
		// -------- BUTTONS -----------
		checkOutButton.setMinWidth(145);
		checkOutButton.setMinHeight(35);
		emptyCartButton.setMinWidth(145);
		emptyCartButton.setMinHeight(35);
		addButton.setMinWidth(200);
		addButton.setMinHeight(35);
		newCartButton.setMinWidth(145);
		newCartButton.setMinHeight(35);
		addButtonDropShadows(checkOutButton);
		addButtonDropShadows(addButton);
		addButtonDropShadows(emptyCartButton);
		addButtonDropShadows(newCartButton);
		
	}
	
	/**
	 * Add all nodes to appropriate grids/VBoxes 
	 * (except radio buttons - see makeRadioButtons() method)
	 */
	public void manageLayout() {
		
		makeRadioButtons();
		makeLayoutAesthetic();
		
		leftButtonsGrid.add(checkOutButton, 0,0);
		leftButtonsGrid.add(emptyCartButton, 1, 0);
		
		newItemPane.add(itemType, 0, 1); 
		newItemPane.add(itemNameField, 1, 1); 
		
		topBox.getChildren().add(headerLabel);
		bottomBox.getChildren().addAll(errorLabel);
		
		leftBox.getChildren().addAll(listViewHeader, listView, 
				currentTotalLabel, leftButtonsGrid);	
		
		rightBox.getChildren().addAll( newItemHeader, newItemPane, 
				radioButtonPane, addFiveCheck, addButton );
		
		finishBox.getChildren().addAll(thankYouLabel, newCartButton);
	
	}
	
	/**
	 * STREAM() METHOD - for each Price enum, make a new radio button & add it to the
	 * priceGroup toggle group. Add all in a row in a new grid pane that will be under newItemPane.
	 */
	public void makeRadioButtons() {
		radioButtonPane.add(itemPriceLabel, 0, 0);
		columnCounter = 1;
		priceStream.forEach(price -> {
			
			RadioButton b = new RadioButton(price.getPriceString());
			b.setUserData(price);
			b.setDisable(true); // Enabled after user types in item name textfield
			radioButtonPane.add(b, columnCounter, 0);
			priceGroup.getToggles().add(b);
			columnCounter++;
			
			// Enable the add 5 checkbox & add item button once a radio button is selected
			b.setOnMouseClicked( e -> {
				addFiveCheck.setDisable(false);
				addButton.setDisable(false);
			});
			
		});
	}
	
	/**
	 * Padding/alignment/spacing of the VBoxes & GridPanes.
	 */
	public void makeLayoutAesthetic() {
		
		topBox.setAlignment(Pos.CENTER);
		topBox.setPadding(new Insets(20, 0, 0, 0));
		leftBox.setAlignment(Pos.TOP_CENTER);
		leftBox.setPadding(new Insets(0, 40, 0, 40)); 
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(20));
		rightBox.setPadding(new Insets(20, 40, 0, 20));
		rightBox.setAlignment(Pos.TOP_CENTER);
		finishBox.setPadding(new Insets(60, 70, 0, 20));
		finishBox.setAlignment(Pos.TOP_CENTER);
		
		newItemPane.setAlignment(Pos.TOP_LEFT);
		newItemPane.setPadding(new Insets(15, 0, 25, 0)); 
		newItemPane.setHgap(15); 
		newItemPane.setVgap(10);
		
		radioButtonPane.setAlignment(Pos.TOP_LEFT);
		radioButtonPane.setPadding(new Insets(5, 0, 25, 0)); 
		radioButtonPane.setHgap(15); 
		radioButtonPane.setVgap(10);
		
		leftButtonsGrid.setHgap(10);
	}
	
	/** ACTION EVENT HANDLERS for all buttons.
	 * checkOutButton: empty cart, replace rightBox (all new item controls) with finishBox 
	 * 					(thank you label & create a new cart button)
	 * emptyCartButton: remove all items from cart & viewable cart list
	 * addButton: call addItem() only if the user has entered a non-null item description
	 * newCartButton: set window back to original setting with New Item controls. */
	public void addButtonListeners() {

		checkOutButton.setOnAction( e -> {
			thankYouLabel.setText("Thank you for shopping!\n Your total is $" 
							+ cart.calculateTotalCost() + ".");
			emptyCartHelper();
			borderPane.setRight(finishBox);
		});
		
		emptyCartButton.setOnAction( e -> {
			emptyCartHelper();
		});
		
		addButton.setOnAction( e -> {
			String name = itemNameField.getText();
			
			if(!name.isEmpty() && !(name.indexOf(" ") == 0)) {
				addItem(name);
				if ( addFiveCheck.isSelected() ) { // add four more if check box is checked
					for ( int i = 0; i < 4; i++ ) {
						addItem(name);
					}
				}
				reset();
			} else {
				reset();
				errorLabel.setText("Please enter a valid item name.");
			}

			checkOutButton.setDisable(false);
			emptyCartButton.setDisable(false);
		});
		
		newCartButton.setOnAction( e -> {
			borderPane.setRight(rightBox);
		});
	}
	
	/**
	 * MOUSE EVENT HANDLER - add drop shadow to buttons when mouse hovers over.
	 * @param b: Button to apply drop shadow to
	 */
	private void addButtonDropShadows(Button b) {
		
		b.setOnMouseEntered( e -> {
			DropShadow d = new DropShadow(5, Color.DARKGRAY);
			b.setEffect(d);
		});
		
		b.setOnMouseExited( e -> {
			b.setEffect(null);
		});
		
	}
	
	/**
	 * Remove all items from cart & viewable cart item list; reset user controls
	 */
	public void emptyCartHelper() {
		cartItems.removeAll(cartItems);
		cart.empty();
		listNumber = 1;
		reset();
	}
	
	/**
	 * Reset all user controls to original disabled/selected/etc. settings
	 */
	public void reset() {
		checkOutButton.setDisable(true);
		emptyCartButton.setDisable(true);
		currentTotalString.setText("Current Total: $" + cart.calculateTotalCost());
		itemNameField.setText(null);
		itemNameField.setPromptText("Item name/description");
		itemNameField.setDisable(true);
		errorLabel.setText(" ");
		itemType.setValue("Type of Item");
		priceGroup.selectToggle(null);
		addButton.setDisable(true);
		addFiveCheck.setSelected(false);
		for (Toggle t : priceGroup.getToggles()) {
			if (t instanceof RadioButton) {
				((RadioButton) t).setDisable(true);
			}
		}
		addFiveCheck.setDisable(true);
	}
	
	/**
	 * Add a General, Grocery, or Pharmacy item to the ShoppingCart based on user
	 * selection from the itemType combobox.
	 * Add name and price of the new item to the viewable cartItems list.
	 * @param aName: user-entered text in the itemNameField textfield
	 */
	public void addItem(String aName) {
		price = (Price) priceGroup.getSelectedToggle().getUserData();
		String name = aName;
		
		if ( itemType.getValue().equals("General") ) {
			cart.addGeneralItem(name, price);
			cartItems.add(listNumber + ". " + cart.item.getName() + " (general) - " 
					+ cart.item.getPrice().getPriceString());
		} else if ( itemType.getValue().equals("Grocery") ) {
			cart.addGroceryItem(name, price);
			cartItems.add(listNumber + ". " + cart.item.getName() + " (grocery) - " 
					+ cart.item.getPrice().getPriceString());
		} else if ( itemType.getValue().equals("Pharmacy") ) {
			cart.addPharmacyItem(name, price);
			cartItems.add(listNumber + ". " + cart.item.getName() + " (pharmacy) - " 
					+ cart.item.getPrice().getPriceString());
		} else {
			cart.addGeneralItem(name, price);
			cartItems.add(listNumber + ". " + cart.item.getName() + " (general) - " 
					+ cart.item.getPrice().getPriceString());
		}
		
		listNumber++;
		
	}
	
	/**
	 * Event handlers for other UI components
	 * itemType: when user selects an option from the Item Type combo box, enable & set focus
	 * 			on the Item Description textfield.
	 * KEY EVENT HANDLER - itemNameField: when user types in Item Description textfield, enable all radio buttons.
	 */
	public void addUIListeners() {
		itemType.setOnAction( e -> {
			if (!itemType.getValue().equals("Type of Item")) {
				itemNameField.setDisable(false);
				itemNameField.requestFocus();
			}
		});
		
		itemNameField.setOnKeyTyped( k -> {
			for (Toggle t : priceGroup.getToggles()) {
				if (t instanceof RadioButton) {
					((RadioButton) t).setDisable(false);
				}
			}
		});
	}
	
	public void go() {
		launch();
	}
	
}