package it.betacom.dao;

import java.util.List;

import it.betacom.model.Libro;

public interface LibroDAO {
	public List<Libro> getAll();
	public Libro getLibroByID(int id_libro);
	public boolean insert(Libro libro);
	public boolean update(Libro libro);
    public boolean delete(Libro libro);
}
