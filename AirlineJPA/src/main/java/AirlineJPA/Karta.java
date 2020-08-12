package AirlineJPA;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonBackReference;


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

	//bi-directional many-to-one association to Let
	@JsonBackReference
	@ManyToOne
	private Let let;
	
	@ManyToOne
	@JsonBackReference
	private Korisnik korisnik;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="idKarta")
	private Sediste sediste;

	public Karta() {
	}
	
	public Sediste getSediste() {
		return this.sediste;
	}
	
	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}
	
	public Korisnik getKorisnik() {
		return this.korisnik;
	}
	
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
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

	public Let getLet() {
		return this.let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

}