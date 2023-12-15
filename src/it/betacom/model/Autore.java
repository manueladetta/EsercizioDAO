package it.betacom.model;

public class Autore {
	
	private int id, annoN, annoM = -1;
	private String nome, cognome, sesso, nazione;
	
	public Autore() {
		super();
	}

	public Autore(int id, int annoN, int annoM, String nome, String cognome, String sesso, String nazione) {
		super();
		this.id = id;
		this.annoN = annoN;
		this.annoM = annoM;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.nazione = nazione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnnoN() {
		return annoN;
	}

	public void setAnnoN(int annoN) {
		this.annoN = annoN;
	}

	public int getAnnoM() {
		return annoM;
	}

	public void setAnnoM(int annoM) {
		this.annoM = annoM;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	@Override
	public String toString() {
		String ris = "Autore: ID = " + id + ", Nome=" + nome + ", Cognome=" + cognome
				+ ", Sesso=" + sesso + ", Nazione=" + nazione + ", Anno di nascita = " + annoN;
		if(annoM != -1) {
			ris = ris + ", Anno di morte=" + annoM;
		}
		
		return ris;
	}
	
}
