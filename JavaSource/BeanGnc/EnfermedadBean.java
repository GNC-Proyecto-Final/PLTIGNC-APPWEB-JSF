package BeanGnc;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.DAOEnfermedadesBean;
import DAO.DAOEnfermedadTernerasBean;
import entidades.Enfermedad;
import enumerados.NombreEnfermedad;



@ManagedBean(name="Enfermedad")
@SessionScoped
public class EnfermedadBean {
	
	@EJB
	private DAOEnfermedadesBean daoEnfermedad;
	
	
	
	@EJB
	private DAOEnfermedadTernerasBean daoEnfermedadTernera;
	private long idEnfermedad;
	private long gradoGravedad;
	private NombreEnfermedad nombre;
		
	private Enfermedad enfermedad;
	
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

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}
	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
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
    		
    		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"La  Enfermedad se ha sido registrado con Exito.",
					"Enfermedad Registrada!"));
    		return "/enfermedades.xhtml?faces-redirect=true";
	
		} catch (Exception e) {
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Hubo un error al almacenar. Intente nuevamente más tarde",
			"Error al registrar!"));
			return "nuevaEnfermedad";
		}
    }	
	public String abrirEliminarEnfermedad() {
		
	      FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params =  fc.getExternalContext().getRequestParameterMap();
	      long enfermedadId = Long.parseLong(params.get("enfermedadId")); 
	      enfermedad = daoEnfermedad.obtenerEnfermedadId(enfermedadId);
	      return "/eliminarEnfermedad.xhtml?faces-redirect=true";
	}
	public String cancelaEliminar(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Ha cancelado la eliminación de la enfermedad",
				"Cancelo la eliminación!"));
		return "/enfermedades.xhtml?faces-redirect=true";
	}
	
	public String eliminarEnfermedad() {
		FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params =  fc.getExternalContext().getRequestParameterMap();
	      long enfermedadId = Long.parseLong(params.get("enfermedadId")); 
		
		Enfermedad enfermedad = daoEnfermedad.obtenerEnfermedadId(enfermedadId);
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = daoEnfermedadTernera.obtenerTerneraEnfermaExiste(enfermedad.getIdEnfermedad());
		
		
		if(existe){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "La Enfermedad tiene registros Asociados,imposible Eliminar",
					"Error al Eliminar!"));
					return null;
		}
		else{
			try {
				daoEnfermedad.eliminarEnfermedad(enfermedad);
				context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "La  Enfermedad se ha sido Eliminado con exito.",
									"Enfermedad Elimada!"));
						return "/enfermedades.xhtml?faces-redirect=true";
				
				
			}catch (Exception e) {
				context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Hubo un error al almacenar. Intente nuevamente mas tarde",
									"Error al registrar!"));
					return null;
			}
			
			
			
		}
	
		
		}
}
