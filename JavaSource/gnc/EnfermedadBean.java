package gnc;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.DAOEnfermedadesBean;
import enumerados.NombreEnfermedad;



@ManagedBean(name="Enfermedad")
@SessionScoped
public class EnfermedadBean {
	
	@EJB
	private DAOEnfermedadesBean DAOEnfermedadesBean;
	
	private long idEnfermedad;
	private long gradoGravedad;
	private NombreEnfermedad nombre;
	public EnfermedadBean() {
		
	}
	public EnfermedadBean(long idEnfermedad, long gradoGravedad, NombreEnfermedad nombre) {
		this.idEnfermedad = idEnfermedad;
		this.gradoGravedad = gradoGravedad;
		this.nombre = nombre;
	}
	public DAOEnfermedadesBean getDAOEnfermedadesBean() {
		return DAOEnfermedadesBean;
	}
	public void setDAOEnfermedadesBean(DAOEnfermedadesBean dAOEnfermedadesBean) {
		DAOEnfermedadesBean = dAOEnfermedadesBean;
	}
	public long getIdEnfermedad() {
		return idEnfermedad;
	}
	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}
	public long getGradoGravedad() {
		return gradoGravedad;
	}
	public void setGradoGravedad(long gradoGravedad) {
		this.gradoGravedad = gradoGravedad;
	}
	public NombreEnfermedad getNombre() {
		return nombre;
	}
	public void setNombre(NombreEnfermedad nombre) {
		this.nombre = nombre;
	}
	
	
	
}
