package it.betacom.main;

import java.util.List;

import it.betacom.dao.AutoreDAO;
import it.betacom.dao.impl.AutoreDAOImpl;
import it.betacom.model.Autore;

public class TestAutore {
	public static void main(String[] args) {
		// Creazione di un'istanza del DAO di Autore
        AutoreDAO autoreDAO = new AutoreDAOImpl();

        // a. Chiamiamo il metodo 4: stampiamo la lista degli autori presenti su Db
        System.out.println("Lista degli autori presenti:");
        printAutori(autoreDAO.getAll());

        // b. Chiamiamo il metodo 5: stampiamo i dati dell'autore richiesto
        int id_autore = 1; 
        Autore autoreById = autoreDAO.getAutoreByID(id_autore);
        if (autoreById != null) {
            System.out.println("\nDati dell'autore con id " + id_autore + ":");
            System.out.println(autoreById);
        } else {
            System.out.println("\nAutore con id " + id_autore + " non trovato");
        }

        // c. Chiamiamo il metodo 6: stampiamo l'esito dell'inserimento e la lista aggiornata
        Autore nuovoAutore = new Autore(10, 1900, 1950, "Nome", "Cognome", "m", "Italia"); 
        boolean isInserted = autoreDAO.insert(nuovoAutore);
        if (isInserted) {
            System.out.println("\nInserimento dell'autore riuscito:");
            printAutori(autoreDAO.getAll());
        } else {
            System.out.println("\nInserimento dell'autore non riuscito.");
        }

        // d. Chiamiamo il metodo 7: stampiamo l'esito dell'aggiornamento e la lista aggiornata
        List<Autore> autori = autoreDAO.getAll();
        Autore autoreToUpdate = autori.get(autori.size() - 1);
        autoreToUpdate.setNazione("USA");
        boolean isUpdated = autoreDAO.update(autoreToUpdate);
        if (isUpdated) {
            System.out.println("\nAggiornamento dell'autore riuscito:");
            printAutori(autoreDAO.getAll());
        } else {
            System.out.println("\nAggiornamento dell'autore non riuscito.");
        }

        // e. Chiamiamo il metodo 8: stampiamo l'esito della cancellazione e la lista aggiornata
        boolean isDeleted = autoreDAO.delete(autoreToUpdate);
        if (isDeleted) {
            System.out.println("\nCancellazione dell'autore riuscita:");
            printAutori(autoreDAO.getAll());
        } else {
            System.out.println("\nCancellazione dell'autore non riuscita.");
        }

        // Chiusura della connessione
        ((AutoreDAOImpl) autoreDAO).closeConnection();
        

	}
	
	// Metodo per stampare la lista di generi
    private static void printAutori(List<Autore> autori) {
        for (Autore autore : autori) {
            System.out.println(autore);
        }
    }
}
