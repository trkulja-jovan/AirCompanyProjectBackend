package AirlineJPA;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the logindata database table.
 * 
 */
@Entity
@NamedQuery(name="Logindata.findAll", query="SELECT l FROM Logindata l")
public class Logindata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLoginData;

	private String password;

	private String username;

	//bi-directional one-to-one association to Korisnik
	@OneToOne(mappedBy="logindata", cascade = {CascadeType.ALL})
	private Korisnik korisnik;

	public Logindata() {
	}

	public int getIdLoginData() {
		return this.idLoginData;
	}

	public void setIdLoginData(int idLoginData) {
		this.idLoginData = idLoginData;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}