package it.betacom.dao;

import java.util.List;

import it.betacom.model.Editore;

public interface EditoreDAO {
	List<Editore> getAll();
    Editore getEditoreByID(int codiceE);
    boolean insert(Editore editore);
    boolean update(Editore editore);
    boolean delete(Editore editore);
}
