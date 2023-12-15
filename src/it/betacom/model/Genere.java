package it.betacom.model;

public class Genere {
	private int codice;
	private String descrizione;
	
	public Genere() {
		super();
	}

	public Genere(int codice, String nome) {
		super();
		this.codice = codice;
		this.descrizione = nome;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Genere: codice = " + codice + ", descrizione = " + descrizione;
	}
	
	
}
