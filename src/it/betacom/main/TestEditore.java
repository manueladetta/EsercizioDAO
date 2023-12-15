package it.betacom.main;

import java.util.List;

import it.betacom.dao.EditoreDAO;
import it.betacom.dao.impl.EditoreDAOImpl;
import it.betacom.model.Editore;

public class TestEditore {
	public static void main(String[] args) {
        EditoreDAO editoreDao = new EditoreDAOImpl();

        // Chiamata al metodo 4: stampiamo la lista degli Editori presenti su Db
        List<Editore> listaEditori = editoreDao.getAll();
        System.out.println("Lista Editori:");
        for (Editore editore : listaEditori) {
            System.out.println("Codice: " + editore.getCodice() + ", Nome: " + editore.getNome());
        }

        // Chiamata al metodo 5: stampiamo i dati del Editore richiesto
        int codiceEditore = 1; 
        Editore editoreByID = editoreDao.getEditoreByID(codiceEditore);
        if (editoreByID != null) {
            System.out.println("\nDati dell'Editore con codice " + codiceEditore + ":");
            System.out.println("Nome: " + editoreByID.getNome());
        } else {
            System.out.println("\nEditore non trovato con codice " + codiceEditore);
        }

        // Chiamata al metodo 6: stampiamo l'esito dell'inserimento e stampiamo la lista Editori
        Editore nuovoEditore1 = new Editore();
        nuovoEditore1.setNome("Laterza");
        boolean inserito = editoreDao.insert(nuovoEditore1);
        if (inserito) {
            System.out.println("\nNuovo Editore inserito con successo: " + nuovoEditore1.getNome());
            printEditori(editoreDao.getAll());
        } else {
            System.out.println("\nErrore durante l'inserimento del nuovo Editore");
        }

        listaEditori = editoreDao.getAll();
        // Chiamata al metodo 7: stampiamo l'esito dell'aggiornamento e stampiamo la lista Editori
        Editore editoreDaAggiornare = new Editore();
        editoreDaAggiornare.setCodice(listaEditori.get(listaEditori.size() - 1).getCodice()); 
        editoreDaAggiornare.setNome("Nuovo Nome Editore");
        boolean aggiornato = editoreDao.update(editoreDaAggiornare);
        if (aggiornato) {
            System.out.println("\nEditore aggiornato con successo: " + editoreDaAggiornare.getNome());
            printEditori(editoreDao.getAll());
        } else {
            System.out.println("\nErrore durante l'aggiornamento dell'Editore");
        }

        // Chiamata al metodo 8: stampiamo l'esito del delete Editore e stampiamo la lista Editori
        Editore editoreDaEliminare = new Editore();
        editoreDaEliminare.setCodice(listaEditori.get(listaEditori.size() - 1).getCodice()); 
        boolean eliminato = editoreDao.delete(editoreDaEliminare);
        if (eliminato) {
            System.out.println("\nEditore eliminato con successo");
            printEditori(editoreDao.getAll());
        } else {
            System.out.println("\nErrore durante l'eliminazione dell'Editore");
        }

        ((EditoreDAOImpl)editoreDao).closeConnection();
    }
	
	// Metodo per stampare la lista di editori
    private static void printEditori(List<Editore> editori) {
        for (Editore editore : editori) {
            System.out.println(editore);
        }
    }
}
