package esiea_bid;

public class Product {

	private static int idProduct;
	private static int currentId = 0;
	private String description;
	private Object productState;
	
	public Product (String description){
		this.idProduct = currentId;
		currentId++;
		this.description = description;
		this.productState = ProductState.CREATED;
	}

	public static int getIdProduct() {
		return idProduct;
	}

	public static void setIdProduct(int idProduct) {
		Product.idProduct = idProduct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
