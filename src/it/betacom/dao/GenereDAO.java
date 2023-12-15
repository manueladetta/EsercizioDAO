package it.betacom.dao;

import java.util.List;

import it.betacom.model.Genere;

public interface GenereDAO {
	public List<Genere> getAll();
	public Genere getGenereByID(int codiceG);
	public boolean insert(Genere genere);
	public boolean update(Genere genere);
    public boolean delete(Genere genere);
}
