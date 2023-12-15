package it.betacom.service.impl;

import it.betacom.model.Book;
import it.betacom.service.PrintService;

/**
 * Classe di implementazione servizi stampa per libri
 * @author Daniele
 *
 */
public class BookPrintService implements PrintService<Book> {

	/**
	 * Implentazione servizio
	 */
//	@Override
//	public void saveListPdf() {
//		// TODO Auto-generated method stub
//		//stampa pdf
//		System.out.println("File pdf creato");
//	}
	
	/**
	 * Implentazione servizio
	 */
//	@Override
//	public void saveListCsv() {
//		// TODO Auto-generated method stub
//		
//	}
	/**
	 * Implentazione servizio
	 */
//	@Override
//	public void saveListTxt() {
//		// TODO Auto-generated method stub
//		
//	}
//	
    /**
     * Implementazione servizio
     */
	@Override
	public void saveAsPdf(Book book) {
		// TODO Auto-generated method stub
		//stampa info libro in pdf
		System.out.println("File del libro pdf creato");
		
	}

	@Override
	public void saveListAsPdf() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveListAsCsv() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveListAsTxt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAsCsv(Book t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAsTxt(Book t) {
		// TODO Auto-generated method stub
		
	}
	
}

//uso generics

/*
 * public class BookPrintService implements PrintService<Book> {
 * 
 * 
 * private BookDao bookDao;
 * 
 * @Override public void saveListPdf() { bookDao.getAllBooks(); //stampa pdf
 * System.out.println("File pdf creato");
 * 
 * }
 * 
 * @Override public void saveListCsv() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void saveListTxt() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void saveAsPdf(Book t) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * 
 * }
 */