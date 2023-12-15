package it.betacom.service.impl;

import java.time.LocalDate;
import java.util.List;

import it.betacom.dao.EditoreDAO;
import it.betacom.dao.impl.EditoreDAOImpl;
import it.betacom.model.Editore;
import it.betacom.service.PrintService;
import it.betacom.utilities.LeggiValori;
import it.betacom.utilities.MyUtility;

public class EditorePrintService implements PrintService<Editore> {

	private EditoreDAO editoreDAO;
	
	private String path = LeggiValori.leggiValoriConfig("pathFolder") + LocalDate.now();
	
	private MyUtility myUtility = new MyUtility(path);
	
	private String[] header = {"Codice", "Nome"};

	@Override
	public void saveListAsPdf() {
		editoreDAO = new EditoreDAOImpl();
		
		List<Editore> editori = editoreDAO.getAll();
		
		myUtility.stampaPDF("Lista Editori", editori, "ListaEditori.pdf", new EditorePdfEntityPrinter());
		
		System.out.println("La stampa della lista di editori in pdf e' stata creata");
		
		((EditoreDAOImpl) editoreDAO).closeConnection();		
	}

	@Override
	public void saveListAsCsv() {
		editoreDAO = new EditoreDAOImpl();
		
		List<Editore> editori = editoreDAO.getAll();
		
        myUtility.stampaCSV("Lista Editori", editori, "ListaEditori.csv", new EditoreCsvEntityPrinter(), header);
		
		System.out.println("La stampa della lista di editori in pdf e' stata creata");	
		
		((EditoreDAOImpl) editoreDAO).closeConnection();	
	}

	@Override
	public void saveListAsTxt() {
		editoreDAO = new EditoreDAOImpl();
		
		List<Editore> editori = editoreDAO.getAll();
		
        myUtility.stampaTXT("Lista Editori", editori, "ListaEditori.txt", new EditoreTxtEntityPrinter());

		System.out.println("La stampa della lista di editori in txt e' stata creata");
		
		((EditoreDAOImpl) editoreDAO).closeConnection();		
	}

	@Override
	public void saveAsPdf(Editore editore) {
		editoreDAO = new EditoreDAOImpl();
				
		myUtility.stampaPDF("Singolo Editore", editore, editore.getNome() + ".pdf", new EditorePdfEntityPrinter());

		System.out.println("La stampa del singolo editore in pdf e' stata creata");		
		
		((EditoreDAOImpl) editoreDAO).closeConnection();	
	}

	@Override
	public void saveAsCsv(Editore editore) {
		editoreDAO = new EditoreDAOImpl();
		
        myUtility.stampaCSV("Singolo Editore", editore, editore.getNome() + ".csv", new EditoreCsvEntityPrinter(), header);

		System.out.println("La stampa del singolo editore in pdf e' stata creata");		
		
		((EditoreDAOImpl) editoreDAO).closeConnection();	
	}

	@Override
	public void saveAsTxt(Editore editore) {
		editoreDAO = new EditoreDAOImpl();
		
        myUtility.stampaTXT("Singolo Editore", editore, editore.getNome() + ".txt", new EditoreTxtEntityPrinter());

		System.out.println("La stampa del singolo editore in txt e' stata creata");		
		
		((EditoreDAOImpl) editoreDAO).closeConnection();		
	}
	
	
	public class EditorePdfEntityPrinter implements MyUtility.EntityPrinter<Editore> {

	    @Override
	    public String printEntity(Editore editore) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("Codice: ").append(editore.getCodice()).append("\n");
	        stringBuilder.append("Nome: ").append(editore.getNome()).append("\n");
	        stringBuilder.append("-----------------------------------------------------------\n");
	        
	        return stringBuilder.toString();
	    }
	}
	
	public class EditoreCsvEntityPrinter implements MyUtility.EntityPrinter<Editore> {

	    @Override
	    public String printEntity(Editore editore) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(editore.getCodice()).append(";")
	                     .append(editore.getNome()).append("\n");
	        return stringBuilder.toString();
	    }
	}

	public class EditoreTxtEntityPrinter implements MyUtility.EntityPrinter<Editore> {

	    @Override
	    public String printEntity(Editore editore) {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("Codice: ").append(editore.getCodice()).append("\n")
	                     .append("Nome: ").append(editore.getNome()).append("\n")
	                     .append("-----------------------------------------------------------\n");
	        return stringBuilder.toString();
	    }
	}

}
