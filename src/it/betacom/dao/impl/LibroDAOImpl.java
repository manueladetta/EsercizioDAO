package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.dao.LibroDAO;
import it.betacom.model.Libro;
import it.betacom.model.LibroGetAll;
import it.betacom.utilities.LeggiValori;

public class LibroDAOImpl implements LibroDAO {
	
	private Connection conn;
	
	public LibroDAOImpl() {
		try {
			Class.forName(LeggiValori.leggiValoriConfig("db.driverclass"));
	        String url = LeggiValori.leggiValoriConfig("db.url");
	        String schema = LeggiValori.leggiValoriConfig("db.schema");
	        String username = LeggiValori.leggiValoriConfig("db.user");
	        String password = LeggiValori.leggiValoriConfig("db.password");
	        conn = DriverManager.getConnection(url + "/" + schema, username, password);
       } catch (ClassNotFoundException | SQLException e) {
           	System.out.println(e.getMessage());
           	e.printStackTrace();
       }
    }
	
	public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

	/*
	 *  String query = "SELECT l.Titolo, l.numPag, l.Anno, e.nome, g.descrizione, a.NomeA, a.CognomeA " +
        			   "FROM libri l JOIN editore e ON l.Editore = e.codiceE " + 
        			   "JOIN autori a ON l.Autore = a.id_autore " + 
        			   "JOIN genere g ON g.codeiceG = l.Genere";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Libro libro = new Libro();
                libro.setId(resultSet.getInt("l.id_libro"));
                libro.setTitolo(resultSet.getString("l.Titolo"));
                libro.setNumPag(resultSet.getInt("l.numPag"));
                libro.setAnno(resultSet.getInt("l.Anno"));
                libro.setEditore(resultSet.getString("e.nome"));
                libro.setGenere(resultSet.getString("g.descrizione"));
                libro.setAutore(resultSet.getString("a.NomeA" + "a.CognomeA"));
                libri.add(libro);
            }
    */
	
	@Override
	public List<LibroGetAll> getAll() {
		List<LibroGetAll> libri = new ArrayList<>();
		String query = "SELECT l.id_libro, l.Titolo, l.numPag, l.Anno, e.nome, g.descrizione, a.NomeA, a.CognomeA " +
 			   "FROM libri l JOIN editore e ON l.Editore = e.codiceE " + 
 			   "JOIN autori a ON l.Autore = a.id_autore " + 
 			   "JOIN genere g ON g.codeiceG = l.Genere";

		 try (Statement statement = conn.createStatement();
		      ResultSet resultSet = statement.executeQuery(query)) {
		
		     while (resultSet.next()) {
		         LibroGetAll libro = new LibroGetAll();
		         libro.setId(resultSet.getInt("l.id_libro"));
		         libro.setTitolo(resultSet.getString("l.Titolo"));
		         libro.setNumPag(resultSet.getInt("l.numPag"));
		         libro.setAnno(resultSet.getInt("l.Anno"));
		         libro.setEditore(resultSet.getString("e.nome"));
		         libro.setGenere(resultSet.getString("g.descrizione"));
		         libro.setNomeAutore(resultSet.getString("a.NomeA"));
		         libro.setCognomeAutore(resultSet.getString("a.CognomeA"));
		         libri.add(libro);
		     }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return libri;
	}

	@Override
	public LibroGetAll getLibroByID(int id_libro) {
		String query = "SELECT l.id_libro, l.Titolo as Titolo, l.numPag, l.Anno, e.nome, g.descrizione, a.NomeA, a.CognomeA " +
	 			   "FROM libri l JOIN editore e ON l.Editore = e.codiceE " + 
	 			   "JOIN autori a ON l.Autore = a.id_autore " + 
	 			   "JOIN genere g ON g.codeiceG = l.Genere "+
	 			   "WHERE id_libro = ?";
        LibroGetAll libro = null;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id_libro);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	libro = new LibroGetAll();
            	libro.setId(resultSet.getInt("l.id_libro"));
		         libro.setTitolo(resultSet.getString("l.Titolo"));
		         libro.setNumPag(resultSet.getInt("l.numPag"));
		         libro.setAnno(resultSet.getInt("l.Anno"));
		         libro.setEditore(resultSet.getString("e.nome"));
		         libro.setGenere(resultSet.getString("g.descrizione"));
		         libro.setNomeAutore(resultSet.getString("a.NomeA"));
		         libro.setCognomeAutore(resultSet.getString("a.CognomeA"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return libro;
	}

	@Override
	public boolean insert(Libro libro) {
		String query = "INSERT INTO libri (Titolo, Anno, numPag, Editore, Autore, Genere) VALUES (?, ?, ?, ?, ?, ?)";
        boolean inserted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, libro.getTitolo());
            preparedStatement.setInt(2, libro.getAnno());
            preparedStatement.setInt(3, libro.getNumPag());
            preparedStatement.setInt(4, libro.getEditore());
            preparedStatement.setInt(5, libro.getAutore());
            preparedStatement.setInt(6, libro.getGenere());
            int rowsAffected = preparedStatement.executeUpdate();
            inserted = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return inserted;
	}

	@Override
	public boolean update(Libro libro) {
		String query = "UPDATE libri SET Titolo = ?, Anno = ?, numPag = ?, Editore = ?, Autore = ?, Genere = ? WHERE id_libro = ?";
        boolean updated = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
        	preparedStatement.setString(1, libro.getTitolo());
            preparedStatement.setInt(2, libro.getAnno());
            preparedStatement.setInt(3, libro.getNumPag());
            preparedStatement.setInt(4, libro.getEditore());
            preparedStatement.setInt(5, libro.getAutore());
            preparedStatement.setInt(6, libro.getGenere());
            preparedStatement.setInt(7, libro.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            updated = (rowsAffected == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return updated;
	}

	@Override
	public boolean delete(Libro libro) {
		String query = "DELETE FROM libri WHERE id_libro = ?";
        boolean deleted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, libro.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            deleted = (rowsAffected == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return deleted;
	}
	
	public Libro convertToLibro(LibroGetAll libro) {
		Libro l = null;
		
		String query = "SELECT * FROM libri " +
	 			   "WHERE id_libro = ?";

     try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
         preparedStatement.setInt(1, libro.getId());
         ResultSet resultSet = preparedStatement.executeQuery();

         if (resultSet.next()) {
         	l = new Libro();
         	l.setId(resultSet.getInt("id_libro"));
	         l.setTitolo(resultSet.getString("Titolo"));
	         l.setNumPag(resultSet.getInt("numPag"));
	         l.setAnno(resultSet.getInt("Anno"));
	         l.setEditore(resultSet.getInt("Editore"));
	         l.setGenere(resultSet.getInt("Genere"));
	         l.setAutore(resultSet.getInt("Autore"));
         }
     } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
         this.closeConnection();
     }
		
		return l;
	}

}
