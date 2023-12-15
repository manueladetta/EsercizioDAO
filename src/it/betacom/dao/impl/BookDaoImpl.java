package it.betacom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import it.betacom.dao.BookDao;
import it.betacom.model.Book;

/**
 * Implementazione della interfaccia per Book Dao
 * @author Daniele
 *
 */

public class BookDaoImpl implements BookDao {
	
	
	//Utilizzo un ArrayList come oggetto contenente i dati
	/**
	 * Lista di appoggio
	 */
	private List<Book> books;
	
	
   /**
    * Costruttore che carica i dati di appoggio
    */
	public BookDaoImpl() {
		super();
		books = new ArrayList<Book>();
		books.add(new Book(1,"Ode"));
		books.add(new Book(2,"Saggio"));
		books.add(new Book(3,"Romanzo"));
	}

	/**
	 * Implementazione del metodo
	 */
	@Override
	public List<Book> getAllBooks() {
		// implementazione connessione + interrogazione per avere la lista dei libri sul db
		return books;
	}

	/**
	 * Implementazione del metodo
	 */
	@Override
	public Book getBookById(int id) {
		// implementazione connessione + interrogazione per avere la il libro con id specificato sul db
		return books.get(id);
	}
	/**
	 * Implementazione del metodo
	 */
	@Override
	public void insertBook(Book book) {
		// implementazione connessione + interrogazione per inserimento del libro  specificato sul db
		books.add(book);
	}
	/**
	 * Implementazione del metodo
	 */
	@Override
	public void deleteBook(Book book) {
		// implementazione connessione + interrogazione per cancellare il libro   specificato dal db
		//da modificare
		//books.remove(book.getIsbn()-1);
		boolean result = books.removeIf(i->i.getIsbn() == book.getIsbn()&& i.getBookName().equalsIgnoreCase(book.getBookName()));
		if(result)System.out.println("Libro con codice isbn = " + book.getIsbn()+ " cancellato");
	}
	/**
	 * Implementazione del metodo
	 */
	@Override
	public void updateBook(Book book) {
		// implementazione connessione + interrogazione per aggiornare il libro   specificato sul db
       
	}

}
