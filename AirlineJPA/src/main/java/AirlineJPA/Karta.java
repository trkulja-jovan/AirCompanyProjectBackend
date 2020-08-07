package AirlineJPA;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the karta database table.
 * 
 */
@Entity
@NamedQuery(name="Karta.findAll", query="SELECT k FROM Karta k")
public class Karta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKarta;

	private String brojKarte;

	private double cena;

	private int sediste_idSediste;

	//bi-directional many-to-one association to Klasa
	@ManyToOne
	private Klasa klasa;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Let
	@ManyToOne
	private Let let;

	//bi-directional one-to-one association to Sediste
	@OneToOne
	@JoinColumn(name="idKarta")
	private Sediste sediste;

	public Karta() {
	}

	public int getIdKarta() {
		return this.idKarta;
	}

	public void setIdKarta(int idKarta) {
		this.idKarta = idKarta;
	}

	public String getBrojKarte() {
		return this.brojKarte;
	}

	public void setBrojKarte(String brojKarte) {
		this.brojKarte = brojKarte;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getSediste_idSediste() {
		return this.sediste_idSediste;
	}

	public void setSediste_idSediste(int sediste_idSediste) {
		this.sediste_idSediste = sediste_idSediste;
	}

	public Klasa getKlasa() {
		return this.klasa;
	}

	public void setKlasa(Klasa klasa) {
		this.klasa = klasa;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Let getLet() {
		return this.let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public Sediste getSediste() {
		return this.sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

}