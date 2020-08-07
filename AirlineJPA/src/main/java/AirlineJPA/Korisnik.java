package AirlineJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String brojTelefona;

	@Temporal(TemporalType.TIMESTAMP)
	private Date godinaRodjenja;

	private String ime;

	private String jmbg;

	private String prezime;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="korisnik")
	private List<Karta> kartas;

	//bi-directional one-to-one association to Logindata
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="idKorisnik")
	private Logindata logindata;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getBrojTelefona() {
		return this.brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public Date getGodinaRodjenja() {
		return this.godinaRodjenja;
	}

	public void setGodinaRodjenja(Date godinaRodjenja) {
		this.godinaRodjenja = godinaRodjenja;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setKorisnik(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setKorisnik(null);

		return karta;
	}

	public Logindata getLogindata() {
		return this.logindata;
	}

	public void setLogindata(Logindata logindata) {
		this.logindata = logindata;
	}

}