package it.betacom.dao;

import java.util.List;

import it.betacom.model.Book;

/**
 * 
 * <i>Interfaccia per operazioni dao sulla classe Book</i>
 * 
 * @see <a href="https://it.wikipedia.org/wiki/Libro" target="_blank" >collegamento</a>
 *  
 * @author Daniele
 * 
 *
 */

public interface BookDao {
	
	/**
	 * Metodo per ottenere lista dei libri
	 * @return lista libri
	 */
	List<Book> getAllBooks();
	
	/**
	 * Metodo per ottenere il libro specificato
	 * @param id del libro
	 * @return libro
	 */
	Book getBookById(int id);
	
	/**
	 * Metodo inserimento libro
	 * @param book libro
	 */
	void insertBook(Book book);
	
	/**
	 * Metodo per la cancellazione del libro
	 * @param book libro
	 */
	void deleteBook(Book book);
	
	/**
	 * Metodo per l'aggiornamento del libro
	 * @param book libro
	 */
	void updateBook(Book book);
	
	
	

}
