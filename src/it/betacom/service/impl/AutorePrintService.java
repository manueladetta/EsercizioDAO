package it.betacom.service.impl;

import java.time.LocalDate;
import java.util.List;

import it.betacom.dao.AutoreDAO;
import it.betacom.dao.impl.AutoreDAOImpl;
import it.betacom.model.Autore;
import it.betacom.service.PrintService;
import it.betacom.utilities.LeggiValori;
import it.betacom.utilities.MyUtility;

public class AutorePrintService implements PrintService<Autore> {
		
	private AutoreDAO autoreDAO;
	
	private String path = LeggiValori.leggiValoriConfig("pathFolder") + LocalDate.now();

	private MyUtility myUtility = new MyUtility(path);
	
	private String[] header = {"ID", "Nome", "Cognome", "Nazionalita", "Sesso", "AnnoNascita", "AnnoMorte"};

	@Override
	public void saveListAsPdf() {
		autoreDAO = new AutoreDAOImpl();
		
		List<Autore> autori = autoreDAO.getAll();
				
		myUtility.stampaPDF("Lista Autori", autori, "ListaAutori.pdf", new AutorePdfEntityPrinter());
		
		System.out.println("La stampa della lista di autori in pdf e' stata creata");

		((AutoreDAOImpl) autoreDAO).closeConnection();		
	}

	@Override
	public void saveListAsCsv() {
		autoreDAO = new AutoreDAOImpl();
		
		List<Autore> autori = autoreDAO.getAll();
				
        myUtility.stampaCSV("Lista Autori", autori, "ListaAutori.csv", new AutoreCsvEntityPrinter(), header);
		
		System.out.println("La stampa della lista di autori in csv e' stata creata");
		
		((AutoreDAOImpl) autoreDAO).closeConnection();		
	}

	@Override
	public void saveListAsTxt() {
		autoreDAO = new AutoreDAOImpl();
		
		List<Autore> autori = autoreDAO.getAll();
				
        myUtility.stampaTXT("Lista Autori", autori, "ListaAutori.txt", new AutoreTxtEntityPrinter());
		
		System.out.println("La stampa della lista di autori in txt e' stata creata");

		((AutoreDAOImpl) autoreDAO).closeConnection();				
	}

	@Override
	public void saveAsPdf(Autore autore) {
		autoreDAO = new AutoreDAOImpl();
						
		myUtility.stampaPDF("Singolo Autore", autore, autore.getNome() + "_" + autore.getCognome() + ".pdf", new AutorePdfEntityPrinter());
		
		System.out.println("La stampa del singolo autore in pdf e' stata creata");	
		
		((AutoreDAOImpl) autoreDAO).closeConnection();		
	}

	@Override
	public void saveAsCsv(Autore autore) {
		autoreDAO = new AutoreDAOImpl();
				
        myUtility.stampaCSV("Singolo Autore", autore, autore.getNome() + "_" + autore.getCognome() + ".csv", new AutoreCsvEntityPrinter(), header);
		
		System.out.println("La stampa del singolo autore in csv e' stata creata");
		
		((AutoreDAOImpl) autoreDAO).closeConnection();		
	}

	@Override
	public void saveAsTxt(Autore autore) {
		autoreDAO = new AutoreDAOImpl();
				
        myUtility.stampaTXT("Singolo Autore", autore, autore.getNome() + "_" + autore.getCognome() + ".txt", new AutoreTxtEntityPrinter());
		
		System.out.println("La stampa del singolo autore in txt e' stata creata");
		
		((AutoreDAOImpl) autoreDAO).closeConnection();		
	}
	
	
	public class AutorePdfEntityPrinter implements MyUtility.EntityPrinter<Autore> {

	    @Override
	    public String printEntity(Autore autore) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("ID: ").append(autore.getId()).append("\n");
	        stringBuilder.append("Nome: ").append(autore.getNome()).append("\n");
	        stringBuilder.append("Cognome: ").append(autore.getCognome()).append("\n");
	        stringBuilder.append("Nazionalità: ").append(autore.getNazione()).append("\n");
	        stringBuilder.append("Sesso: ").append(autore.getSesso()).append("\n");
	        stringBuilder.append("Anno di nascita: ").append(autore.getAnnoN()).append("\n");
	        stringBuilder.append("Anno di morte: ").append(autore.getAnnoM()).append("\n");
	        stringBuilder.append("-----------------------------------------------------------\n");
	        
	        return stringBuilder.toString();
	    }
	}
	
	public class AutoreCsvEntityPrinter implements MyUtility.EntityPrinter<Autore> {

	    @Override
	    public String printEntity(Autore autore) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(autore.getId()).append(";")
	                     .append(autore.getNome()).append(";")
	                     .append(autore.getCognome()).append(";")
	                     .append(autore.getNazione()).append(";")
	                     .append(autore.getSesso()).append(";")
	                     .append(autore.getAnnoN()).append(";")
	                     .append(autore.getAnnoM()).append("\n");
	        return stringBuilder.toString();
	    }
	}

	public class AutoreTxtEntityPrinter implements MyUtility.EntityPrinter<Autore> {

	    @Override
	    public String printEntity(Autore autore) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("ID: ").append(autore.getId()).append("\n")
	                     .append("Nome: ").append(autore.getNome()).append("\n")
	                     .append("Cognome: ").append(autore.getCognome()).append("\n")
	                     .append("Nazionalità: ").append(autore.getNazione()).append("\n")
	                     .append("Sesso: ").append(autore.getSesso()).append("\n")
	                     .append("Anno di nascita: ").append(autore.getAnnoN()).append("\n")
	                     .append("Anno di morte: ").append(autore.getAnnoM()).append("\n")
	                     .append("-----------------------------------------------------------\n");
	        return stringBuilder.toString();
	    }
	}

}
