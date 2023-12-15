package it.betacom.model;


/**
 * Classe  per rappresentare un libro
 * @author Daniele
 *
 */
public class Book {

	private int isbn;
	private String bookName;
    
	/**
	 * Costruttore di default
	 */
	public Book() {
		super();

	}
 
	/**
	 * Costruttore con parametri
	 * @param isbn isbn del libro
	 * @param bookName nome del libro
	 */
	public Book(int isbn, String bookName) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
	}

	public int getIsbn() {
		return isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	
}
