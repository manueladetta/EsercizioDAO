package it.betacom.main;

import it.betacom.dao.BookDao;
import it.betacom.dao.impl.BookDaoImpl;
import it.betacom.model.Book;
import it.betacom.service.PrintService;
import it.betacom.service.impl.BookPrintService;

/**
 * Classe main dil lancio del modello dao e servizi correlati
 * 
 * @author Daniele 
 *
 */
public class MainBook {
	
	/**
	 * Metodo Main per il lancio dell'applicazione
	 * 
	 * @param args parametri in ingresso
	 */

	public static void main(String[] args) {
		System.out.println("---------------------------");
		System.out.println("Lista dei libri");
		BookDao bookDao = new BookDaoImpl();
		for(Book book: bookDao.getAllBooks())
		{
			System.out.println("Book isbn:"+ book.getIsbn()+"||Book name:"+ book.getBookName());
		}
			
		System.out.println("---------------------------");
		Book book = new Book(2,"Saggio");
		bookDao.deleteBook(book);
		System.out.println("Lista dei libri aggiornata");
		for(Book book2: bookDao.getAllBooks())
		{
			System.out.println("Book isbn:"+ book2.getIsbn()+"||Book name:"+ book2.getBookName());
		}
		System.out.println("---------------------------");
		
		PrintService bps = new BookPrintService();
//		bps.saveListPdf();
		System.out.println("---------------------------");
	}

}
