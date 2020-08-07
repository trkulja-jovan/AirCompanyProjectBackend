package AirlineJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the klasa database table.
 * 
 */
@Entity
@NamedQuery(name="Klasa.findAll", query="SELECT k FROM Klasa k")
public class Klasa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKlasa;

	private String naziv;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="klasa")
	private List<Karta> kartas;

	public Klasa() {
	}

	public int getIdKlasa() {
		return this.idKlasa;
	}

	public void setIdKlasa(int idKlasa) {
		this.idKlasa = idKlasa;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setKlasa(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setKlasa(null);

		return karta;
	}

}