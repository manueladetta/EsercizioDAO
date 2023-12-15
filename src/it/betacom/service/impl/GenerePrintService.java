package it.betacom.service.impl;

import java.time.LocalDate;
import java.util.List;

import it.betacom.dao.GenereDAO;
import it.betacom.dao.impl.GenereDAOImpl;
import it.betacom.model.Genere;
import it.betacom.service.PrintService;
import it.betacom.utilities.LeggiValori;
import it.betacom.utilities.MyUtility;

public class GenerePrintService implements PrintService<Genere> {

	private GenereDAO genereDAO;
	
	private String path = LeggiValori.leggiValoriConfig("pathFolder") + LocalDate.now();

	private MyUtility myUtility = new MyUtility(path);

	private String[] header = {"Codice", "Descrizione"};

	@Override
	public void saveListAsPdf() {
		genereDAO = new GenereDAOImpl();
		
		List<Genere> generi = genereDAO.getAll();
		
		myUtility.stampaPDF("Lista Generi", generi, "ListaGeneri.pdf", new GenerePdfEntityPrinter());
		
		System.out.println("La stampa della lista di generi in pdf e' stata creata");		
		
		((GenereDAOImpl) genereDAO).closeConnection();	
	}

	@Override
	public void saveListAsCsv() {
		genereDAO = new GenereDAOImpl();
		
		List<Genere> generi = genereDAO.getAll();
		
        myUtility.stampaCSV("Lista Generi", generi, "ListaGenerii.csv", new GenereCsvEntityPrinter(), header);
		
		System.out.println("La stampa della lista di generi in csv e' stata creata");	
		
		((GenereDAOImpl) genereDAO).closeConnection();
	}

	@Override
	public void saveListAsTxt() {
		genereDAO = new GenereDAOImpl();
		
		List<Genere> generi = genereDAO.getAll();
		
        myUtility.stampaTXT("Lista Generi", generi, "ListaGeneri.txt", new GenereTxtEntityPrinter());
		
		System.out.println("La stampa della lista di generi in txt e' stata creata");	
		
		((GenereDAOImpl) genereDAO).closeConnection();		
	}

	@Override
	public void saveAsPdf(Genere genere) {
		genereDAO = new GenereDAOImpl();
		
		myUtility.stampaPDF("Singolo Genere", genere, genere.getDescrizione() + ".pdf", new GenerePdfEntityPrinter());
		
		System.out.println("La stampa del singolo genere in pdf e' stata creata");		
		
		((GenereDAOImpl) genereDAO).closeConnection();
	}

	@Override
	public void saveAsCsv(Genere genere) {
		genereDAO = new GenereDAOImpl();
		
        myUtility.stampaCSV("Singolo Genere", genere, genere.getDescrizione() + ".csv", new GenereCsvEntityPrinter(), header);
		
		System.out.println("La stampa del singolo genere in csv e' stata creata");	
		
		((GenereDAOImpl) genereDAO).closeConnection();
	}

	@Override
	public void saveAsTxt(Genere genere) {
		genereDAO = new GenereDAOImpl();
		
		myUtility.stampaTXT("Singolo Genere", genere, genere.getDescrizione() + ".txt", new GenereTxtEntityPrinter());
		
		System.out.println("La stampa del singolo genere in txt e' stata creata");		
		
		((GenereDAOImpl) genereDAO).closeConnection();		
	}
	
	
	public class GenerePdfEntityPrinter implements MyUtility.EntityPrinter<Genere> {

	    @Override
	    public String printEntity(Genere genere) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("Codice: ").append(genere.getCodice()).append("\n");
	        stringBuilder.append("Descrizione: ").append(genere.getDescrizione()).append("\n");
	        stringBuilder.append("-----------------------------------------------------------\n");
	        
	        return stringBuilder.toString();
	    }
	}
	
	public class GenereCsvEntityPrinter implements MyUtility.EntityPrinter<Genere> {

	    @Override
	    public String printEntity(Genere genere) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(genere.getCodice()).append(";")
	                     .append(genere.getDescrizione()).append("\n");
	        return stringBuilder.toString();
	    }
	}

	public class GenereTxtEntityPrinter implements MyUtility.EntityPrinter<Genere> {

	    @Override
	    public String printEntity(Genere genere) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("Codice: ").append(genere.getCodice()).append("\n")
	                     .append("Descrizione: ").append(genere.getDescrizione()).append("\n")
	                     .append("-----------------------------------------------------------\n");
	        return stringBuilder.toString();
	    }
	}
	
}
