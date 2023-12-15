package it.betacom.model;

public class Libro {
	private int id_libro, anno, numPag, editore, genere, autore;
	private String titolo; // editore, genere, autore;
	
	public Libro() {
		super();
	}

//	public Libro(int id_libro, int anno, int numPag, String editore, String genere, String autore, String titolo) {
	public Libro(int id_libro, int anno, int numPag, int editore, int genere, int autore, String titolo) {
		super();
		this.id_libro = id_libro;
		this.anno = anno;
		this.numPag = numPag;
		this.editore = editore;
		this.genere = genere;
		this.autore = autore;
		this.titolo = titolo;
	}

	public int getId() {
		return id_libro;
	}

	public void setId(int id_libro) {
		this.id_libro = id_libro;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}

//	public String getEditore() {
	public int getEditore() {
		return editore;
	}

//	public void setEditore(String editore) {
	public void setEditore(int editore) {
		this.editore = editore;
	}

//	public String getGenere() {
	public int getGenere() {
		return genere;
	}

//	public void setGenere(String genere) {
	public void setGenere(int genere) {
		this.genere = genere;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

//	public String getAutore() {
	public int getAutore() {
		return autore;
	}

//	public void setAutore(String autore) {
	public void setAutore(int autore) {
		this.autore = autore;
	}

	@Override
	public String toString() {
		return "Libro: id = " + id_libro + ", anno di pubblicazione = " + anno + ", numero di pagine = " + numPag + ", editore = " + editore
				+ ", genere = " + genere + ", titolo = " + titolo + ", autore = " + autore;
	}
	
}
