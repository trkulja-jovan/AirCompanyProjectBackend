package AirlineJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;


/**
 * The persistent class for the aviokompanija database table.
 * 
 */
@Entity
@NamedQuery(name="Aviokompanija.findAll", query="SELECT a FROM Aviokompanija a")
public class Aviokompanija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAviokompanija;

	private String nazivKompanije;

	private String tipKompanije;

	//bi-directional many-to-many association to Aerodrom
	@ManyToMany(mappedBy="aviokompanijas")
	@JsonIgnore
	private List<Aerodrom> aerodroms;

	//bi-directional many-to-one association to Avion
	@OneToMany(mappedBy="aviokompanija")
	@JsonManagedReference
	private List<Avion> avions;

	//bi-directional many-to-one association to Let
	@OneToMany(mappedBy="aviokompanija")
	@JsonManagedReference
	private List<Let> lets;

	public Aviokompanija() {
	}

	public int getIdAviokompanija() {
		return this.idAviokompanija;
	}

	public void setIdAviokompanija(int idAviokompanija) {
		this.idAviokompanija = idAviokompanija;
	}

	public String getNazivKompanije() {
		return this.nazivKompanije;
	}

	public void setNazivKompanije(String nazivKompanije) {
		this.nazivKompanije = nazivKompanije;
	}

	public String getTipKompanije() {
		return this.tipKompanije;
	}

	public void setTipKompanije(String tipKompanije) {
		this.tipKompanije = tipKompanije;
	}

	public List<Aerodrom> getAerodroms() {
		return this.aerodroms;
	}

	public void setAerodroms(List<Aerodrom> aerodroms) {
		this.aerodroms = aerodroms;
	}

	public List<Avion> getAvions() {
		return this.avions;
	}

	public void setAvions(List<Avion> avions) {
		this.avions = avions;
	}

	public Avion addAvion(Avion avion) {
		getAvions().add(avion);
		avion.setAviokompanija(this);

		return avion;
	}

	public Avion removeAvion(Avion avion) {
		getAvions().remove(avion);
		avion.setAviokompanija(null);

		return avion;
	}

	public List<Let> getLets() {
		return this.lets;
	}

	public void setLets(List<Let> lets) {
		this.lets = lets;
	}

	public Let addLet(Let let) {
		getLets().add(let);
		let.setAviokompanija(this);

		return let;
	}

	public Let removeLet(Let let) {
		getLets().remove(let);
		let.setAviokompanija(null);

		return let;
	}
	
	public String getJson() {
		return "{"
				+ "\"idAviokompanija\":" + this.idAviokompanija + ","
				+ "\"nazivKompanije\":" + "\"" + this.nazivKompanije + "\""
			 + "}";
				
	}

}