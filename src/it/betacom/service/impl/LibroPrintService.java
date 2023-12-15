package it.betacom.service.impl;

import java.time.LocalDate;
import java.util.List;

import it.betacom.dao.LibroDAO;
import it.betacom.dao.impl.LibroDAOImpl;
import it.betacom.model.LibroGetAll;
import it.betacom.service.PrintService;
import it.betacom.utilities.LeggiValori;
import it.betacom.utilities.MyUtility;

public class LibroPrintService implements PrintService<LibroGetAll> {
	
	private LibroDAO libroDAO;
	
	private String path = LeggiValori.leggiValoriConfig("pathFolder") + LocalDate.now();
	
	private MyUtility myUtility = new MyUtility(path);

	private	String[] header = {"ID", "Titolo", "Numero di pagine", "Anno", "Id autore", "Id genere", "Id Editore"};

	@Override
	public void saveListAsPdf() {
		libroDAO = new LibroDAOImpl();
		
		List<LibroGetAll> libri = libroDAO.getAll();
		
		myUtility.stampaPDF("Lista Libri", libri, "ListaLibri.pdf", new LibroPdfEntityPrinter());
		
		System.out.println("La stampa della lista di libri in pdf e' stata creata");		
		
		((LibroDAOImpl) libroDAO).closeConnection();
	}

	@Override
	public void saveListAsCsv() {
		libroDAO = new LibroDAOImpl();
		
		List<LibroGetAll> libri = libroDAO.getAll();
		
        myUtility.stampaCSV("Lista Libri", libri, "ListaLibri.csv", new LibroCsvEntityPrinter(), header);
		
		System.out.println("La stampa della lista di libri in csv e' stata creata");
		
		((LibroDAOImpl) libroDAO).closeConnection();
	}

	@Override
	public void saveListAsTxt() {
		libroDAO = new LibroDAOImpl();
		
		List<LibroGetAll> libri = libroDAO.getAll();
		
        myUtility.stampaTXT("Lista Libri", libri, "ListaLibri.txt", new LibroTxtEntityPrinter());
		
		System.out.println("La stampa della lista di libri in txt e' stata creata");		
		
		((LibroDAOImpl) libroDAO).closeConnection();		
	}

	@Override
	public void saveAsPdf(LibroGetAll libro) {
		libroDAO = new LibroDAOImpl();
				
		myUtility.stampaPDF("Singolo Libro", libro, libro.getTitolo() + "_" + libro.getNomeAutore() + "_" + libro.getCognomeAutore()  + ".pdf", new LibroPdfEntityPrinter());
		
		System.out.println("La stampa del singolo libro in pdf e' stata creata");		
		
		((LibroDAOImpl) libroDAO).closeConnection();
	}

	@Override
	public void saveAsCsv(LibroGetAll libro) {
		libroDAO = new LibroDAOImpl();
		
		myUtility.stampaCSV("Singolo Libro", libro, libro.getTitolo() + "_" + libro.getNomeAutore() + "_" + libro.getCognomeAutore()  + ".csv", new LibroCsvEntityPrinter(), header);
		
		System.out.println("La stampa del singolo libro in csv e' stata creata");	
		
		((LibroDAOImpl) libroDAO).closeConnection();
	}

	@Override
	public void saveAsTxt(LibroGetAll libro) {
		libroDAO = new LibroDAOImpl();
		
		myUtility.stampaTXT("Singolo Libro", libro, libro.getTitolo() + "_" + libro.getNomeAutore() + "_" + libro.getCognomeAutore() + ".txt", new LibroTxtEntityPrinter());
		
		System.out.println("La stampa del singolo libro in txt e' stata creata");	
		
		((LibroDAOImpl) libroDAO).closeConnection();
	}
	
	
	public class LibroPdfEntityPrinter implements MyUtility.EntityPrinter<LibroGetAll> {

	    @Override
	    public String printEntity(LibroGetAll libro) {
	    	StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("ID: ").append(libro.getId()).append("\n")
	                     .append("Titolo: ").append(libro.getTitolo()).append("\n")
	                     .append("Anno: ").append(libro.getAnno()).append("\n")
	                     .append("Numero di pagine: ").append(libro.getNumPag()).append("\n")
	                     .append("Autore: ").append(libro.getNomeAutore() + " " + libro.getCognomeAutore()).append("\n")
	                     .append("Genere: ").append(libro.getGenere()).append("\n")
	                     .append("Editore: ").append(libro.getEditore()).append("\n")
	                     .append("-----------------------------------------------------------\n");
	        return stringBuilder.toString();
	    }
	}
	
	public class LibroCsvEntityPrinter implements MyUtility.EntityPrinter<LibroGetAll> {

	    @Override
	    public String printEntity(LibroGetAll libro) {	        
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(libro.getId()).append(";")
	                     .append(libro.getTitolo()).append(";")
	                     .append(libro.getAnno()).append(";")
	                     .append(libro.getNumPag()).append(";")
	                     .append(libro.getNomeAutore() + " " + libro.getCognomeAutore()).append(";")
	                     .append(libro.getGenere()).append(";")
	                     .append(libro.getEditore()).append("\n");
	        return stringBuilder.toString();
	    }
	}

	public class LibroTxtEntityPrinter implements MyUtility.EntityPrinter<LibroGetAll> {

	    @Override
	    public String printEntity(LibroGetAll libro) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("ID: ").append(libro.getId()).append("\n")
	                     .append("Titolo: ").append(libro.getTitolo()).append("\n")
	                     .append("Anno: ").append(libro.getAnno()).append("\n")
	                     .append("Numero di pagine: ").append(libro.getNumPag()).append("\n")
	                     .append("Autore: ").append(libro.getNomeAutore() + " " + libro.getCognomeAutore()).append("\n")
	                     .append("Genere: ").append(libro.getGenere()).append("\n")
	                     .append("Editore: ").append(libro.getEditore()).append("\n")
	                     .append("-----------------------------------------------------------\n");
	        return stringBuilder.toString();
	    }
	}
	
}
