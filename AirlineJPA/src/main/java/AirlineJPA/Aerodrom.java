package AirlineJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aerodrom database table.
 * 
 */
@Entity
@NamedQuery(name="Aerodrom.findAll", query="SELECT a FROM Aerodrom a")
public class Aerodrom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAerodrom;

	private String code;

	private String drzava;

	private String grad;

	//bi-directional many-to-many association to Aviokompanija
	@ManyToMany
	@JoinTable(
		name="aviokompanija_has_aerodrom"
		, joinColumns={
			@JoinColumn(name="Aerodrom_idAerodrom")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Aviokompanija_idAviokompanija")
			}
		)
	private List<Aviokompanija> aviokompanijas;

	//bi-directional many-to-one association to Podacileta
	@OneToMany(mappedBy="aerodrom1")
	private List<Podacileta> podaciletas1;

	//bi-directional many-to-one association to Podacileta
	@OneToMany(mappedBy="aerodrom2")
	private List<Podacileta> podaciletas2;

	public Aerodrom() {
	}

	public int getIdAerodrom() {
		return this.idAerodrom;
	}

	public void setIdAerodrom(int idAerodrom) {
		this.idAerodrom = idAerodrom;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDrzava() {
		return this.drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return this.grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public List<Aviokompanija> getAviokompanijas() {
		return this.aviokompanijas;
	}

	public void setAviokompanijas(List<Aviokompanija> aviokompanijas) {
		this.aviokompanijas = aviokompanijas;
	}

	public List<Podacileta> getPodaciletas1() {
		return this.podaciletas1;
	}

	public void setPodaciletas1(List<Podacileta> podaciletas1) {
		this.podaciletas1 = podaciletas1;
	}

	public Podacileta addPodaciletas1(Podacileta podaciletas1) {
		getPodaciletas1().add(podaciletas1);
		podaciletas1.setAerodrom1(this);

		return podaciletas1;
	}

	public Podacileta removePodaciletas1(Podacileta podaciletas1) {
		getPodaciletas1().remove(podaciletas1);
		podaciletas1.setAerodrom1(null);

		return podaciletas1;
	}

	public List<Podacileta> getPodaciletas2() {
		return this.podaciletas2;
	}

	public void setPodaciletas2(List<Podacileta> podaciletas2) {
		this.podaciletas2 = podaciletas2;
	}

	public Podacileta addPodaciletas2(Podacileta podaciletas2) {
		getPodaciletas2().add(podaciletas2);
		podaciletas2.setAerodrom2(this);

		return podaciletas2;
	}

	public Podacileta removePodaciletas2(Podacileta podaciletas2) {
		getPodaciletas2().remove(podaciletas2);
		podaciletas2.setAerodrom2(null);

		return podaciletas2;
	}

}