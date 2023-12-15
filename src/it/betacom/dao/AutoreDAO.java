package it.betacom.dao;

import java.util.List;

import it.betacom.model.Autore;

public interface AutoreDAO {
	public List<Autore> getAll();
	public Autore getAutoreByID(int id_autore);
	public boolean insert(Autore autore);
	public boolean update(Autore autore);
    public boolean delete(Autore autore);
}
