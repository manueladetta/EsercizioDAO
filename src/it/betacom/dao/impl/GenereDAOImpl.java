package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.dao.GenereDAO;
import it.betacom.model.Genere;
import it.betacom.utilities.LeggiValori;

public class GenereDAOImpl implements GenereDAO {
	
	private Connection conn;
	
	// Connessione con il DB
	public GenereDAOImpl() {
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
	
	// Chiusura della connessione al termine dell'uso
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
	
	@Override
	public List<Genere> getAll() {
        List<Genere> generi = new ArrayList<>();
        String query = "SELECT * FROM genere";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Genere genere = new Genere();
                genere.setCodice(resultSet.getInt("codeiceG"));
                genere.setDescrizione(resultSet.getString("descrizione"));
                generi.add(genere);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return generi;
    }

	@Override
	public Genere getGenereByID(int codiceG) {
		String query = "SELECT * FROM genere WHERE codeiceG = ?";
        Genere genere = null;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, codiceG);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                genere = new Genere();
                genere.setCodice(resultSet.getInt("codeiceG"));
                genere.setDescrizione(resultSet.getString("descrizione"));
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return genere;
	}

	@Override
	public boolean insert(Genere genere) {
		String query = "INSERT INTO genere (descrizione) VALUES (?)";
        boolean inserted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, genere.getDescrizione());
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
	public boolean update(Genere genere) {
		String query = "UPDATE genere SET descrizione = ? WHERE codeiceG = ?";
        boolean updated = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, genere.getDescrizione());
            preparedStatement.setInt(2, genere.getCodice());
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
	public boolean delete(Genere genere) {
		String query = "DELETE FROM genere WHERE codeiceG = ?";
        boolean deleted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, genere.getCodice());
            int rowsAffected = preparedStatement.executeUpdate();
            deleted = (rowsAffected == 1);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return deleted;
	}

}
