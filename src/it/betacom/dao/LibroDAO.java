package it.betacom.dao;

import java.util.List;

import it.betacom.model.Libro;
import it.betacom.model.LibroGetAll;

public interface LibroDAO {
	public List<LibroGetAll> getAll();
	public LibroGetAll getLibroByID(int id_libro);
	public boolean insert(Libro libro);
	public boolean update(Libro libro);
    public boolean delete(Libro libro);
}
