package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.dao.EditoreDAO;
import it.betacom.model.Editore;
import it.betacom.utilities.LeggiValori;

public class EditoreDAOImpl implements EditoreDAO {
	
	private Connection conn;

	public EditoreDAOImpl() {
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
	public List<Editore> getAll() {
		List<Editore> editori = new ArrayList<>();
        String query = "SELECT * FROM editore";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Editore editore = new Editore();
                editore.setCodice(resultSet.getInt("codiceE"));
                editore.setNome(resultSet.getString("nome"));
                editori.add(editore);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return editori;
	}

	@Override
	public Editore getEditoreByID(int codiceE) {
		String query = "SELECT * FROM editore WHERE codiceE = ?";
        Editore editore = null;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, codiceE);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                editore = new Editore();
                editore.setCodice(resultSet.getInt("codiceE"));
                editore.setNome(resultSet.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.closeConnection();
        }

        return editore;
	}

	@Override
	public boolean insert(Editore editore) {
		String query = "INSERT INTO editore (nome) VALUES (?)";
        boolean inserted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, editore.getNome());
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
	public boolean update(Editore editore) {
		String query = "UPDATE editore SET nome = ? WHERE codiceE = ?";
        boolean updated = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, editore.getNome());
            preparedStatement.setInt(2, editore.getCodice());
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
	public boolean delete(Editore editore) {
		String query = "DELETE FROM editore WHERE codiceE = ?";
        boolean deleted = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, editore.getCodice());
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
