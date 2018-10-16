package gnc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import DAO.DAOTernerasBean;
import entidades.Ternera;
import enumerados.Raza;
import enumerados.TipoParto;

@ManagedBean(name="ternera")
@SessionScoped
public class TerneraBean {
	
	@EJB
	private DAOTernerasBean daoTernera;
	
	private long idTernera;
	private String causaBaja;
	private String causaMuerte;
	private Date fechaBaja;
	private Date fechaMuerte;
	private Date fechaNacimiento;
	private long nroCaravana;
	private TipoParto parto;
	private BigDecimal pesoNacimiento;
	private Raza raza;
	
	public TerneraBean (){
		
	}
	
	public TerneraBean(long idTernera, String causaBaja, String causaMuerte, Date fechaBaja, Date fechaMuerte,
			Date fechaNacimiento, long nroCaravana, TipoParto parto, BigDecimal pesoNacimiento, Raza raza) {
		super();
		this.idTernera = idTernera;
		this.causaBaja = causaBaja;
		this.causaMuerte = causaMuerte;
		this.fechaBaja = fechaBaja;
		this.fechaMuerte = fechaMuerte;
		this.fechaNacimiento = fechaNacimiento;
		this.nroCaravana = nroCaravana;
		this.parto = parto;
		this.pesoNacimiento = pesoNacimiento;
		this.raza = raza;
	}

	public long getIdTernera() {
		return idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}
	public String getCausaBaja() {
		return causaBaja;
	}
	public void setCausaBaja(String causaBaja) {
		this.causaBaja = causaBaja;
	}
	public String getCausaMuerte() {
		return causaMuerte;
	}
	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Date getFechaMuerte() {
		return fechaMuerte;
	}
	public void setFechaMuerte(Date fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public long getNroCaravana() {
		return nroCaravana;
	}
	public void setNroCaravana(long nroCaravana) {
		this.nroCaravana = nroCaravana;
	}
	public TipoParto getParto() {
		return parto;
	}
	public void setParto(TipoParto parto) {
		this.parto = parto;
	}
	public BigDecimal getPesoNacimiento() {
		return pesoNacimiento;
	}
	public void setPesoNacimiento(BigDecimal pesoNacimiento) {
		this.pesoNacimiento = pesoNacimiento;
	}
	public Raza getRaza() {
		return raza;
	}
	public void setRaza(Raza raza) {
		this.raza = raza;
	}
	public List<Ternera> obtenerTerneras(){
    	List<Ternera> enf = null;
    	enf =   daoTernera.obtenerTodasTerneras();
		return enf;
	}
}
