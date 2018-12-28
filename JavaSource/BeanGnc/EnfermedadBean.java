package BeanGnc;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Controlador.EnfermedadBeanRemote;
import Controlador.EnfermedadTerneraBeanRemote;
import entidades.Enfermedad;
import enumerados.NombreEnfermedad;
import excepciones.TerneraEnfermaException;



@ManagedBean(name="Enfermedad")
@SessionScoped
public class EnfermedadBean {
	
	/*
	@EJB
	private DAOEnfermedadesBean daoEnfermedad;
	
	@EJB
	private DAOEnfermedadTernerasBean daoEnfermedadTernera;
	*/
	
	@EJB
	private EnfermedadBeanRemote enfermedadBeanRemote;
	
	@EJB
	private EnfermedadTerneraBeanRemote enfermedadTerneraBeanRemote;
	
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
		
		//boolean existe = daoEnfermedad.existeEnfermedad(enferm);
		boolean existe;
		try {
			existe = enfermedadBeanRemote.existeEnfermedad(enferm);
			if (existe) {
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"La Enfermedad ya se encuentra registrada.",
					"La enfermedad ya Existe!"));
			return "NuevaEnfermedad";
		}
			
		} catch (TerneraEnfermaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

			
    	try {
    		
    		//daoEnfermedad.crearEnfermedad(enferm);
    		enfermedadBeanRemote.ingresarNuevaEnfermedad(enferm);
    		
    		
    		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"La  Enfermedad se ha sido registrado con Exito.",
					"Enfermedad Registrada!"));
    		limpiarDatos();
    		return "/enfermedades.xhtml?faces-redirect=true";
	
		} catch (Exception e) {
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Hubo un error al almacenar. Intente nuevamente m�s tarde",
			"Error al registrar!"));
			return "nuevaEnfermedad";
		}
    }	
	public String abrirEliminarEnfermedad() throws TerneraEnfermaException  {
		
	      FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params =  fc.getExternalContext().getRequestParameterMap();
	      long enfermedadId = Long.parseLong(params.get("enfermedadId")); 
	      //enfermedad = daoEnfermedad.obtenerEnfermedadId(enfermedadId);

			enfermedad = enfermedadBeanRemote.findEnfermedadPorId(enfermedadId);
			 

	    return "/eliminarEnfermedad.xhtml?faces-redirect=true";
		
	     
	}
	public String cancelaEliminar(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Ha cancelado la eliminaci�n de la enfermedad",
				"Cancelo la eliminacion!"));
		return "/enfermedades.xhtml?faces-redirect=true";
	}
	
	public String eliminarEnfermedad() throws TerneraEnfermaException  {
		FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params =  fc.getExternalContext().getRequestParameterMap();
	      long enfermedadId = Long.parseLong(params.get("enfermedadId")); 
		
		//Enfermedad enfermedad = daoEnfermedad.obtenerEnfermedadId(enfermedadId);
		Enfermedad enfermedad;
	
		enfermedad = enfermedadBeanRemote.findEnfermedadPorId(enfermedadId);
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		//boolean existe = daoEnfermedadTernera.obtenerTerneraEnfermaExiste(enfermedad.getIdEnfermedad());
		
		boolean existe;
		
			existe = enfermedadTerneraBeanRemote.existeEnfermedadEnTernaraEnfermedad(enfermedad.getIdEnfermedad());
			
			if(existe){
				context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "La Enfermedad tiene registros Asociados,imposible Eliminar",
						"Error al Eliminar!"));
						return null;
			}
			else{
				try {
					
					//daoEnfermedad.eliminarEnfermedad(enfermedad);
					
					enfermedadBeanRemote.eliminarEnfermedad(enfermedad);
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "La  Enfermedad se ha sido Eliminado con exito.",
										"Enfermedad Elimada!"));
					limpiarDatos();
							return "/enfermedades.xhtml?faces-redirect=true";
					
					
				}catch (Exception e) {
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Hubo un error al almacenar. Intente nuevamente mas tarde",
										"Error al registrar!"));
						return null;
				}
				
				
				
			}
			
		
		
		
	
		
		}
	
		private void limpiarDatos(){
			idEnfermedad = 0;
			gradoGravedad = 0;
			nombre = null;
			enfermedad = null;
		}

}
