package AirlineJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the usluga database table.
 * 
 */
@Entity
@NamedQuery(name="Usluga.findAll", query="SELECT u FROM Usluga u")
public class Usluga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsluga;

	private double cena;

	private String nazivUsluge;

	//bi-directional many-to-many association to Let
	@ManyToMany(mappedBy="uslugas")
	private List<Let> lets;

	public Usluga() {
	}

	public int getIdUsluga() {
		return this.idUsluga;
	}

	public void setIdUsluga(int idUsluga) {
		this.idUsluga = idUsluga;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getNazivUsluge() {
		return this.nazivUsluge;
	}

	public void setNazivUsluge(String nazivUsluge) {
		this.nazivUsluge = nazivUsluge;
	}

	public List<Let> getLets() {
		return this.lets;
	}

	public void setLets(List<Let> lets) {
		this.lets = lets;
	}
	
	public String getJson() {
		return "{"
				+ "\"idUsluga\":" + this.idUsluga + ","
				+ "\"cena\":" + "\"" + this.cena + "\","
				+ "\"nazivUsluge\":" + "\"" + this.nazivUsluge + "\""
				+ "}";
				
	}

}