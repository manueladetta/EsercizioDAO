package it.betacom.model;

public class Editore {
	private int codice;
    private String nome;

    public Editore() {
		super();
    }

	public Editore(int codice, String nome) {
		super();
		this.codice = codice;
		this.nome = nome;
	}

	public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	@Override
	public String toString() {
		return "Editore: codice = " + codice + ", nome = " + nome;
	}
	
}
