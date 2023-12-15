package it.betacom.main;

import java.util.List;

import it.betacom.dao.AutoreDAO;
import it.betacom.dao.EditoreDAO;
import it.betacom.dao.GenereDAO;
import it.betacom.dao.LibroDAO;
import it.betacom.dao.impl.AutoreDAOImpl;
import it.betacom.dao.impl.EditoreDAOImpl;
import it.betacom.dao.impl.GenereDAOImpl;
import it.betacom.dao.impl.LibroDAOImpl;
import it.betacom.model.Autore;
import it.betacom.model.Editore;
import it.betacom.model.Genere;
import it.betacom.model.LibroGetAll;
import it.betacom.service.PrintService;
import it.betacom.service.impl.AutorePrintService;
import it.betacom.service.impl.EditorePrintService;
import it.betacom.service.impl.GenerePrintService;
import it.betacom.service.impl.LibroPrintService;

public class TestPrintService {

	public static void main(String[] args) {
		
		PrintService<Autore> autorePS = new AutorePrintService();
		autorePS.saveListAsPdf();
		autorePS.saveListAsCsv();
		autorePS.saveListAsTxt();
		AutoreDAO autoreDAO = new AutoreDAOImpl();
		List<Autore> autori = autoreDAO.getAll();
		autorePS.saveAsPdf(autori.get(0));
		autorePS.saveAsCsv(autori.get(0));
		autorePS.saveAsTxt(autori.get(0));
		
		PrintService<Editore> editorePS = new EditorePrintService();
		editorePS.saveListAsPdf();
		editorePS.saveListAsCsv();
		editorePS.saveListAsTxt();
		EditoreDAO editoreDAO = new EditoreDAOImpl();
		List<Editore> editori = editoreDAO.getAll();
		editorePS.saveAsPdf(editori.get(0));
		editorePS.saveAsCsv(editori.get(0));
		editorePS.saveAsTxt(editori.get(0));
		
		PrintService<Genere> generePS = new GenerePrintService();
		generePS.saveListAsPdf();
		generePS.saveListAsCsv();
		generePS.saveListAsTxt();
		GenereDAO genereDAO = new GenereDAOImpl();
		List<Genere> generi = genereDAO.getAll();
		generePS.saveAsPdf(generi.get(0));
		generePS.saveAsCsv(generi.get(0));
		generePS.saveAsTxt(generi.get(0));
		
		PrintService<LibroGetAll> libroPS = new LibroPrintService();
		libroPS.saveListAsPdf();
		libroPS.saveListAsCsv();
		libroPS.saveListAsTxt();
		LibroDAO libroDAO = new LibroDAOImpl();
		List<LibroGetAll> libri = libroDAO.getAll();
		libroPS.saveAsPdf(libri.get(0));
		libroPS.saveAsCsv(libri.get(0));
		libroPS.saveAsTxt(libri.get(0));
		
	}

}
