package AirlineJPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the podacileta database table.
 * 
 */
@Entity
@NamedQuery(name="Podacileta.findAll", query="SELECT p FROM Podacileta p")
public class Podacileta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPodaciLeta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumDolaska;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumPolaska;

	//bi-directional many-to-one association to Let
	@OneToMany(mappedBy="podacileta")
	private List<Let> lets;

	//bi-directional many-to-one association to Aerodrom
	@ManyToOne
	@JoinColumn(name="PolazniAerodrom_idAerodrom")
	private Aerodrom aerodrom1;

	//bi-directional many-to-one association to Aerodrom
	@ManyToOne
	@JoinColumn(name="DolazniAerodrom_idAerodrom1")
	private Aerodrom aerodrom2;

	public Podacileta() {
	}

	public int getIdPodaciLeta() {
		return this.idPodaciLeta;
	}

	public void setIdPodaciLeta(int idPodaciLeta) {
		this.idPodaciLeta = idPodaciLeta;
	}

	public Date getDatumDolaska() {
		return this.datumDolaska;
	}

	public void setDatumDolaska(Date datumDolaska) {
		this.datumDolaska = datumDolaska;
	}

	public Date getDatumPolaska() {
		return this.datumPolaska;
	}

	public void setDatumPolaska(Date datumPolaska) {
		this.datumPolaska = datumPolaska;
	}

	public List<Let> getLets() {
		return this.lets;
	}

	public void setLets(List<Let> lets) {
		this.lets = lets;
	}

	public Let addLet(Let let) {
		getLets().add(let);
		let.setPodacileta(this);

		return let;
	}

	public Let removeLet(Let let) {
		getLets().remove(let);
		let.setPodacileta(null);

		return let;
	}

	public Aerodrom getAerodrom1() {
		return this.aerodrom1;
	}

	public void setAerodrom1(Aerodrom aerodrom1) {
		this.aerodrom1 = aerodrom1;
	}

	public Aerodrom getAerodrom2() {
		return this.aerodrom2;
	}

	public void setAerodrom2(Aerodrom aerodrom2) {
		this.aerodrom2 = aerodrom2;
	}

}