package AirlineJPA;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the avion database table.
 * 
 */
@Entity
@NamedQuery(name="Avion.findAll", query="SELECT a FROM Avion a")
public class Avion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAvion;

	private int maxBrojSedista;

	private String serijskiBroj;

	private String tipAviona;

	//bi-directional many-to-one association to Aviokompanija
	@ManyToOne
	@JsonBackReference
	private Aviokompanija aviokompanija;

	public Avion() {
	}

	public int getIdAvion() {
		return this.idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public int getMaxBrojSedista() {
		return this.maxBrojSedista;
	}

	public void setMaxBrojSedista(int maxBrojSedista) {
		this.maxBrojSedista = maxBrojSedista;
	}

	public String getSerijskiBroj() {
		return this.serijskiBroj;
	}

	public void setSerijskiBroj(String serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}

	public String getTipAviona() {
		return this.tipAviona;
	}

	public void setTipAviona(String tipAviona) {
		this.tipAviona = tipAviona;
	}

	public Aviokompanija getAviokompanija() {
		return this.aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}
	
	@Override
	public String toString() {
		return this.serijskiBroj + " | " + this.tipAviona;
	}

}