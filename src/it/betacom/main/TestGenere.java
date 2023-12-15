package it.betacom.main;

import java.util.List;

import it.betacom.dao.GenereDAO;
import it.betacom.dao.impl.GenereDAOImpl;
import it.betacom.model.Genere;

public class TestGenere {

	public static void main(String[] args) {
		// Creazione di un'istanza del DAO di Genere
        GenereDAO genereDao = new GenereDAOImpl();

        // a. Chiamiamo il metodo 4: stampiamo la lista dei generi presenti su Db
        System.out.println("Lista dei generi presenti:");
        printGeneri(genereDao.getAll());

        // b. Chiamiamo il metodo 5: stampiamo i dati del genere richiesto
        int codiceGenere = 1; 
        Genere genereById = genereDao.getGenereByID(codiceGenere);
        if (genereById != null) {
            System.out.println("\nDati del genere con codice " + codiceGenere + ":");
            System.out.println(genereById);
        } else {
            System.out.println("\nGenere non trovato per il codice " + codiceGenere);
        }

        // c. Chiamiamo il metodo 6: stampiamo l'esito dell'inserimento e la lista aggiornata
        Genere nuovoGenere = new Genere(5, "Fantasy"); 
        boolean isInserted = genereDao.insert(nuovoGenere);
        if (isInserted) {
            System.out.println("\nInserimento del genere riuscito:");
            printGeneri(genereDao.getAll());
        } else {
            System.out.println("\nInserimento del genere non riuscito.");
        }

        // d. Chiamiamo il metodo 7: stampiamo l'esito dell'aggiornamento e la lista aggiornata
        List<Genere> generi = genereDao.getAll();
        Genere genereToUpdate = new Genere(generi.get(generi.size() - 1).getCodice(), "Romanzo"); 
        boolean isUpdated = genereDao.update(genereToUpdate);
        if (isUpdated) {
            System.out.println("\nAggiornamento del genere riuscito:");
            printGeneri(genereDao.getAll());
        } else {
            System.out.println("\nAggiornamento del genere non riuscito.");
        }

        // e. Chiamiamo il metodo 8: stampiamo l'esito della cancellazione e la lista aggiornata
        boolean isDeleted = genereDao.delete(genereToUpdate);
        if (isDeleted) {
            System.out.println("\nCancellazione del genere riuscita:");
            printGeneri(genereDao.getAll());
        } else {
            System.out.println("\nCancellazione del genere non riuscita.");
        }

        // Chiusura della connessione
        ((GenereDAOImpl) genereDao).closeConnection();

	}
	
	// Metodo per stampare la lista di generi
    private static void printGeneri(List<Genere> generi) {
        for (Genere genere : generi) {
            System.out.println(genere);
        }
    }

}
