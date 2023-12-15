package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.dao.AutoreDAO;
import it.betacom.model.Autore;
import it.betacom.utilities.LeggiValori;

public class AutoreDAOImpl implements AutoreDAO {
	
	private Connection conn;

	public AutoreDAOImpl() {
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

	@Override
	public List<Autore> getAll() {
		List<Autore> editori = new ArrayList<>();
        String query = "SELECT * FROM autori";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Autore autore = new Autore();
                autore.setId(resultSet.getInt("id_autore"));
                autore.setNome(resultSet.getString("NomeA"));
                autore.setCognome(resultSet.getString("CognomeA"));
                autore.setAnnoN(resultSet.getInt("AnnoN"));
                autore.setAnnoM(resultSet.getInt("AnnoM"));
                autore.setSesso(resultSet.getString("Sesso"));
                autore.setNazione(resultSet.getString("Nazione"));
                editori.add(autore);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return editori;
	}

	@Override
	public Autore getAutoreByID(int id_autore) {
		String query = "SELECT * FROM autori WHERE id_autore = ?";
        Autore autore = null;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id_autore);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	autore = new Autore();
                autore.setId(resultSet.getInt("id_autore"));
                autore.setNome(resultSet.getString("NomeA"));
                autore.setCognome(resultSet.getString("CognomeA"));
                autore.setAnnoN(resultSet.getInt("AnnoN"));
                autore.setAnnoM(resultSet.getInt("AnnoM"));
                autore.setSesso(resultSet.getString("Sesso"));
                autore.setNazione(resultSet.getString("Nazione"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return autore;
	}

	@Override
	public boolean insert(Autore autore) {
		String query = "INSERT INTO autori (NomeA, CognomeA, AnnoN, AnnoM, Sesso, Nazione) VALUES (?, ?, ?, ?, ?, ?)";
        boolean inserted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, autore.getNome());
            preparedStatement.setString(2, autore.getCognome());
            preparedStatement.setInt(3, autore.getAnnoN());
            preparedStatement.setInt(4, autore.getAnnoM());
            preparedStatement.setString(5, autore.getSesso());
            preparedStatement.setString(6, autore.getNazione());
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
	public boolean update(Autore autore) {
		String query = "UPDATE autori SET NomeA = ?, CognomeA = ?, AnnoN = ?, AnnoM = ?, Sesso = ?, Nazione = ? WHERE id_autore = ?";
        boolean updated = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
        	preparedStatement.setString(1, autore.getNome());
        	preparedStatement.setString(2, autore.getCognome());
        	preparedStatement.setInt(3, autore.getAnnoN());
        	preparedStatement.setInt(4, autore.getAnnoM());
        	preparedStatement.setString(5, autore.getSesso());
            preparedStatement.setString(6, autore.getNazione());
            preparedStatement.setInt(7, autore.getId());
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
	public boolean delete(Autore autore) {
		String query = "DELETE FROM autori WHERE id_autore = ?";
        boolean deleted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, autore.getId());
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
