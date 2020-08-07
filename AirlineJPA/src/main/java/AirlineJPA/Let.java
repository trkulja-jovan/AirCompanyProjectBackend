package AirlineJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the let database table.
 * 
 */
@Entity
@NamedQuery(name="Let.findAll", query="SELECT l FROM Let l")
public class Let implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLet;

	private String oznakaLeta;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="let")
	private List<Karta> kartas;

	//bi-directional many-to-one association to Aviokompanija
	@ManyToOne
	private Aviokompanija aviokompanija;

	//bi-directional many-to-one association to Podacileta
	@ManyToOne
	private Podacileta podacileta;

	//bi-directional many-to-many association to Usluga
	@ManyToMany
	@JoinTable(
		name="let_has_usluga"
		, joinColumns={
			@JoinColumn(name="Let_idLet")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Usluga_idUsluga")
			}
		)
	private List<Usluga> uslugas;

	//bi-directional many-to-one association to Sediste
	@OneToMany(mappedBy="let")
	private List<Sediste> sedistes;

	public Let() {
	}

	public int getIdLet() {
		return this.idLet;
	}

	public void setIdLet(int idLet) {
		this.idLet = idLet;
	}

	public String getOznakaLeta() {
		return this.oznakaLeta;
	}

	public void setOznakaLeta(String oznakaLeta) {
		this.oznakaLeta = oznakaLeta;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setLet(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setLet(null);

		return karta;
	}

	public Aviokompanija getAviokompanija() {
		return this.aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	public Podacileta getPodacileta() {
		return this.podacileta;
	}

	public void setPodacileta(Podacileta podacileta) {
		this.podacileta = podacileta;
	}

	public List<Usluga> getUslugas() {
		return this.uslugas;
	}

	public void setUslugas(List<Usluga> uslugas) {
		this.uslugas = uslugas;
	}

	public List<Sediste> getSedistes() {
		return this.sedistes;
	}

	public void setSedistes(List<Sediste> sedistes) {
		this.sedistes = sedistes;
	}

	public Sediste addSediste(Sediste sediste) {
		getSedistes().add(sediste);
		sediste.setLet(this);

		return sediste;
	}

	public Sediste removeSediste(Sediste sediste) {
		getSedistes().remove(sediste);
		sediste.setLet(null);

		return sediste;
	}

}