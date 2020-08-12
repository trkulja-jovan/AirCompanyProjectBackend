package AirlineJPA;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the sediste database table.
 * 
 */
@Entity
@NamedQuery(name="Sediste.findAll", query="SELECT s FROM Sediste s")
public class Sediste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSediste;

	private int redniBroj;

	//bi-directional one-to-one association to Karta
	@OneToOne(mappedBy="sediste", cascade = {CascadeType.ALL})
	private Karta karta;

	//bi-directional many-to-one association to Let
	@ManyToOne
	@JsonBackReference
	private Let let;

	public Sediste() {
	}

	public int getIdSediste() {
		return this.idSediste;
	}

	public void setIdSediste(int idSediste) {
		this.idSediste = idSediste;
	}

	public int getRedniBroj() {
		return this.redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public Karta getKarta() {
		return this.karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public Let getLet() {
		return this.let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

}