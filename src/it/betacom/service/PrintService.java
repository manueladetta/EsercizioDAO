package it.betacom.service;

/**
 * <b>Interfaccia servizi di stampa</b>
 * 
 * @author Daniele
 *
 */
public interface PrintService<T> {
	
	/**
	 * Servizio di stampa in pdf
	 */
//	void saveListPdf();
	/**
	 * Servizio di stampa in csv
	 */
//	void saveListCsv();
	/**
	 * Servizio di stampa in txt
	 */
//	void saveListTxt();
	/**
	 * Servizio di stampa singolo libro in pdf
	 * @param book libro
	 */
//	void saveAsPdf(Book book);
	
	void saveListAsPdf();
	void saveListAsCsv();
	void saveListAsTxt();
	void saveAsPdf(T t);
	void saveAsCsv(T t);
	void saveAsTxt(T t);

}

/*
 * public interface PrintService<T> {
 * 
 * void saveListPdf(); void saveListCsv(); void saveListTxt(); void saveAsPdf(T
 * t);
 * 
 * }
 */

