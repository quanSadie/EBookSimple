package Model;

public class Book {
	private String title;
	private String imageUrl;
	private String price;
	private String description;
	private String isbn;
	private String readLink;

	public Book() {

	}

	public Book(String title, String imageUrl, String price, String description, String isbn, String readLink) {
		this.title = title;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
		this.isbn = isbn;
		this.readLink = readLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getReadLink() {
		return readLink;
	}

	public void setReadLink(String readLink) {
		this.readLink = readLink;
	}

	@Override
	public String toString() {
		return "Title: " + title + "\nDescription: " + description + "\nPrice: " + price + "\n----------------";
	}
}
