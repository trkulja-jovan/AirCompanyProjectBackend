package AirlineJPA;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;


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

	//bi-directional many-to-one association to Let
	@JsonIgnore
	@ManyToOne
	@JsonBackReference
	private Let let;

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
	
	@JsonIgnore
	public Let getLet() {
		return this.let;
	}

	@JsonIgnore
	public void setLet(Let let) {
		this.let = let;
	}
	
	@Override
	public String toString() {
		return this.naziv;
	}
	
	public String getJson() {
		return "{"
				+ " \"idKlasa\":" + this.idKlasa + ","
				+ "\"naziv\":" + "\"" + this.naziv + "\""
				+ "}";
				
	}

}