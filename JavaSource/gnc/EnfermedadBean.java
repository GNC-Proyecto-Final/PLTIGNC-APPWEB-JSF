package gnc;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.DAOEnfermedadesBean;
import entidades.Enfermedad;
import enumerados.NombreEnfermedad;



@ManagedBean(name="Enfermedad")
@SessionScoped
public class EnfermedadBean {
	
	@EJB
	private DAOEnfermedadesBean daoEnfermedad;
	
	private long idEnfermedad;
	private long gradoGravedad;
	private NombreEnfermedad nombre;
		
	public EnfermedadBean() {
		
		
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
	
	public NombreEnfermedad[] getNombreEnfermedades(){
		return NombreEnfermedad.values();
	}

	public String agregarTernera() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		

		Enfermedad enferm = new Enfermedad (this.gradoGravedad,this.nombre);
		
		boolean existe = daoEnfermedad.existeEnfermedad(enferm);
		if (existe) {
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"La Enfermedad ya se encuentra registrada.",
					"La enfermedad ya Existe!"));
			return "NuevaEnfermedad";
		}
			
    	try {
    		daoEnfermedad.crearEnfermedad(enferm);
    		
    		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"La  Enfermedad se ha sido registrado con Éxito.",
					"Enfermedad Registrada!"));
    		return "TerneraEnfermedades";
	
		} catch (Exception e) {
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Hubo un error al almacenar. Intente nuevamente más tarde",
			"Error al registrar!"));
			return "NuevaEnfermedad";
		}
    }	
}
