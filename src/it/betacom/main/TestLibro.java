package it.betacom.main;

import java.util.List;

import it.betacom.dao.LibroDAO;
import it.betacom.dao.impl.LibroDAOImpl;
import it.betacom.model.Libro;

public class TestLibro {
	public static void main(String[] args) {
		// Creazione di un'istanza del DAO di Autore
        LibroDAO libroDAO = new LibroDAOImpl();

        // a. Chiamiamo il metodo 4: stampiamo la lista degli autori presenti su Db
        System.out.println("Lista dei libri presenti:");
        printLibri(libroDAO.getAll());

        // b. Chiamiamo il metodo 5: stampiamo i dati del libro richiesto
        int id_libro = 1; 
        Libro libroById = libroDAO.getLibroByID(id_libro);
        if (libroById != null) {
            System.out.println("\nDati del libro con id " + id_libro + ":");
            System.out.println(libroById);
        } else {
            System.out.println("\nLibro con id " + id_libro + " non trovato");
        }

        // c. Chiamiamo il metodo 6: stampiamo l'esito dell'inserimento e la lista aggiornata
        Libro nuovoLibro = new Libro(20, 1900, 100, 1, 1, 1, "Prova"); 
        boolean isInserted = libroDAO.insert(nuovoLibro);
        if (isInserted) {
            System.out.println("\nInserimento del libro riuscito:");
            printLibri(libroDAO.getAll());
        } else {
            System.out.println("\nInserimento del libro non riuscito.");
        }

        // d. Chiamiamo il metodo 7: stampiamo l'esito dell'aggiornamento e la lista aggiornata
        List<Libro> libri = libroDAO.getAll();
        Libro libroToUpdate = libri.get(libri.size() - 1);
        libroToUpdate.setTitolo("Nuovo titolo");
        boolean isUpdated = libroDAO.update(libroToUpdate);
        if (isUpdated) {
            System.out.println("\nAggiornamento del libro riuscito:");
            printLibri(libroDAO.getAll());
        } else {
            System.out.println("\nAggiornamento del libro non riuscito.");
        }

        // e. Chiamiamo il metodo 8: stampiamo l'esito della cancellazione e la lista aggiornata
        boolean isDeleted = libroDAO.delete(libroToUpdate);
        if (isDeleted) {
            System.out.println("\nCancellazione del libro riuscita:");
            printLibri(libroDAO.getAll());
        } else {
            System.out.println("\nCancellazione del libro non riuscita.");
        }

        // Chiusura della connessione
        ((LibroDAOImpl) libroDAO).closeConnection();

	}
	
	// Metodo per stampare la lista di generi
    private static void printLibri(List<Libro> libri) {
        for (Libro libro : libri) {
            System.out.println(libro);
        }
    }
}
