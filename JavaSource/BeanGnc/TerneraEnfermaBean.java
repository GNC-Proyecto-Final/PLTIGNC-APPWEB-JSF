package BeanGnc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Controlador.EnfermedadBeanRemote;
import Controlador.EnfermedadTerneraBeanRemote;
import Controlador.TernerasBeanRemote;
import entidades.Enfermedad;
import entidades.EnfermedadTernera;
import entidades.EnfermedadTerneraPK;
import entidades.Ternera;
import excepciones.TerneraEnfermaException;




@ManagedBean(name="terneraEnferma")
@SessionScoped
public class TerneraEnfermaBean {
	/*
	@EJB
	private DAOEnfermedadTernerasBean daoTerneraEnferma;
	
	@EJB
	private DAOTernerasBean daoTernera;
	
	@EJB
	private DAOEnfermedadesBean daoEnfermedad;
	*/
	
	@EJB
	private  EnfermedadTerneraBeanRemote enfermedadTerneraBeanRemote;
	
	@EJB
	private  EnfermedadBeanRemote enfermedadBeanRemote;
	
	@EJB
	private TernerasBeanRemote ternerasBeanRemote;
	

	
	private String terneraId;
	private String enfermedadId;
	private EnfermedadTerneraPK id;
	private Date fechaDesde;
	private Date fechaHasta;
	private String observacion;
	private Enfermedad enfermedad;
	private Ternera ternera;
	
	private EnfermedadTernera terneraEnferma;
	private Date dateInicio,dateFin,dateNacim;
		 
	public TerneraEnfermaBean() {
	
	}

	public EnfermedadTerneraPK getId() {
		return id;
	}

	public void setId(EnfermedadTerneraPK id) {
		this.id = id;
	}

	public String getTerneraId() {
		return terneraId;
	}

	public void setTerneraId(String terneraId) {
		this.terneraId = terneraId;
	}

	public String getEnfermedadId() {
		return enfermedadId;
	}

	public void setEnfermedadId(String enfermedadId) {
		this.enfermedadId = enfermedadId;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Ternera getTernera() {
		return ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
		
	public EnfermedadTernera getTerneraEnferma() {
		return terneraEnferma;
	}

	public void setTerneraEnferma(EnfermedadTernera terneraEnferma) {
		this.terneraEnferma = terneraEnferma;
	}

	public String agregarTerneraEnferma() throws TerneraEnfermaException {
		FacesContext context = FacesContext.getCurrentInstance();	
		Long idTernera=Long.parseLong(this.getTerneraId());		
		//ternera = daoTernera.obtenerTerneraId(idTernera);	
		ternera = ternerasBeanRemote.findTerneraPorId(idTernera);
		
		formatoFecha();			
		if(!(ternera.getFechaMuerte()==null)||!(ternera.getFechaBaja()==null)){
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Verifique que la ternera este habilitada, ternera muerta o dada de baja", "Ternera no habilitada"));
			return null;
		}
	
		if( !validarFecha()){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Verifique las fechas: \n Fecha Inicio o Fin mayor a fecha actual", "Fechas Incorrectas!"));
			return null;
		}
		if(!validarFechaNaciento()){
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Verifique la fecha inicio enfermedad - fecha inicio enfermedad < nacimiento", "Ternera no ha nacido aun"));
			return null;
		}
						
		if (this.observacion.length()>=250) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Otros aspectos Sanitarios \n Excede de los 250 caracteres ", "Otros aspectos sanitarios"));
			return null;
		}
		
		// Si alguno es vacio, mostramos una ventana de mensaje
		if (terneraId.equals("") || terneraId.equals("")||  dateInicio == null ) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe completar los campos \n(Ternera, Enfermedad, Fecha Inicio.", "Datos incompletos!"));
			return null;
		}
		else{
			long  idEnfermedad = Long.parseLong(enfermedadId);

			//enfermedad = daoEnfermedad.obtenerEnfermedadId(idEnfermedad);
			enfermedad = enfermedadBeanRemote.findEnfermedadPorId(idEnfermedad);
			
			if(dateFin == null){
				EnfermedadTerneraPK pk = new EnfermedadTerneraPK();
				pk.setIdEnfermedad(idEnfermedad);
				pk.setIdTernera(idTernera);
				pk.setFechaDesde(dateInicio);
				EnfermedadTernera terneraEnferma = new EnfermedadTernera (pk,this.ternera,this.enfermedad,this.observacion);
												
				//boolean existe = daoTerneraEnferma.obtenerTerneraEnfermaFechaExiste(terneraEnferma);
				boolean existe = enfermedadTerneraBeanRemote.obtenerTerneraEnfermaFechaExiste(terneraEnferma);
				if (existe) {
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,  "La Enfermedad por Ternera ya se encuentra registrada.",
							"La enfermedad ya Existe!"));
					return null;
				}
					
				try {
					
					//daoTerneraEnferma.crearEnfermedadTernera(terneraEnferma);
					enfermedadTerneraBeanRemote.crearTerneraEnferma(terneraEnferma);
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,  "la  Enfermedad por Ternera se ha sido registrado con Exito.",
							"Enfermedad Registrada!"));
					limpiarDatos();
					return "/ternerasEnfermas.xhtml?faces-redirect=true";
		
				
				} catch (Exception e) {
					e.printStackTrace();
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Hubo un error al almacenar. Intente nuevamente mas tarde",
							"Error al registrar!"));
				}
			}			
			else{
						
				EnfermedadTerneraPK pk = new EnfermedadTerneraPK();
				pk.setIdEnfermedad(idEnfermedad);
				pk.setIdTernera(idTernera);
				pk.setFechaDesde(dateInicio);
				EnfermedadTernera terneraEnferma = new EnfermedadTernera (pk,this.ternera,this.enfermedad,this.dateFin,this.observacion);
				
				//boolean existe = daoTerneraEnferma.obtenerTerneraEnfermaFechaExiste(terneraEnferma);
				boolean existe = enfermedadTerneraBeanRemote.obtenerTerneraEnfermaFechaExiste(terneraEnferma);
				
				if (existe) {
					
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "La Enfermedad por Ternera ya se encuentra registrada.",
							"La enfermedad ya Existe!"));
					return null;
				}
				
				try {
					//daoTerneraEnferma.crearEnfermedadTernera(terneraEnferma);
					enfermedadTerneraBeanRemote.crearTerneraEnferma(terneraEnferma);
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,  "la  Enfermedad por Ternera se ha sido registrado con Exito.",
							"Enfermedad Registrada!"));
					limpiarDatos();
					return "/ternerasEnfermas.xhtml?faces-redirect=true";
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,  "Ocurrio un error al almacenar. Intente nuevamente mas tarde",
							"Error al registrar!"));
					return null;
				}
			}
		}
		
		return null;
		
		
    }
	
	public void  formatoFecha(){
		
		Date fechaInicio = (Date) this.getFechaDesde();
		Date fechaFin = (Date) this.getFechaHasta();
		Date fechaNacim = (Date)this.ternera.getFechaNacimiento();	
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			if(!(fechaInicio==null)&& !(fechaFin==null)){
				String dti = dt1.format(fechaInicio);
				String dtf = dt1.format(fechaFin);
				String dtn = dt1.format(fechaNacim);
				dateInicio = dt1.parse(dti);
				dateFin = dt1.parse(dtf);
				dateNacim = dt1.parse(dtn);
			}else if(!(fechaInicio==null)&& (fechaFin==null)){
				String dti = dt1.format(fechaInicio);
				String dtn = dt1.format(fechaNacim);
				dateInicio = dt1.parse(dti);
				dateNacim = dt1.parse(dtn);
			}
			else if((fechaInicio==null)&& (fechaFin==null)){
				
			}
			else{
				String dtf = dt1.format(fechaFin);
				String dtn = dt1.format(fechaNacim);
				dateFin = dt1.parse(dtf);
				dateNacim = dt1.parse(dtn);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean validarFecha(){
		
		boolean fechaValida= false;
		
		//Date fechaInicio =  this.dateInicio;
		//Date fechaFin = this.dateFin;
		Date fecha = new Date();
		
			if(!(fechaDesde==null)&& !(fechaHasta==null)){
				if(dateInicio.compareTo(dateFin)<=0 && dateInicio.compareTo(fecha)<=0  && dateFin.compareTo(fecha)<=0){
					fechaValida= true;
				}

			}else if(!(fechaDesde==null)&& (fechaHasta==null)){
				if(dateInicio.compareTo(fecha)<=0 ){
					fechaValida= true;
				}
			}
			else if((fechaDesde==null)&& (fechaHasta==null)){

					fechaValida= false;
			}
			else{
				if( dateFin.compareTo(fecha)<=0 ){
					fechaValida= true;
				}
			}
			return fechaValida;
		
	}
	
	public boolean validarFechaNaciento(){
		
		boolean fechaValida= false;
		
		Date fechaInicio =  this.dateInicio;
		Date fechaFin = this.dateFin;
		Date fechaNac = this.dateNacim;
		
			if(!(fechaDesde == null )&& !(fechaHasta == null )){
				if(fechaInicio.compareTo(fechaNac)>=0 && fechaFin.compareTo(fechaNac)>=0){
					fechaValida= true;
				}

			}else if(!(fechaDesde == null )&& (fechaHasta == null )){
				if(fechaInicio.compareTo(fechaNac)>=0 ){
					fechaValida= true;
				}
			}
			else if((fechaDesde == null )&& (fechaHasta  == null  )){
					fechaValida= false;
			}
			else{
				if( fechaFin.compareTo(fechaNac)>=0 ){
					fechaValida= true;
				}
			}
			return fechaValida;
		
	}
	
	public String abrirEditarTerneraEnferma() throws TerneraEnfermaException {
		
	      FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params =  fc.getExternalContext().getRequestParameterMap();
	      long enfermedadId = Long.parseLong(params.get("enfermedadId")); 
	      long terneraId = Long.parseLong(params.get("terneraId")); 
	      String fecha = params.get("fechaInicio");
	      SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dateInicio = dt1.parse(fecha);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	
			//ternera = daoTernera.obtenerTerneraId(terneraId);
			
			//enfermedad= daoEnfermedad.obtenerEnfermedadId(enfermedadId);
			ternera = ternerasBeanRemote.findTerneraPorId(terneraId);
			
			enfermedad = enfermedadBeanRemote.findEnfermedadPorId(enfermedadId);
			
			
			EnfermedadTerneraPK pk = new EnfermedadTerneraPK();
			pk.setIdEnfermedad(enfermedadId);
			pk.setIdTernera(terneraId);
			pk.setFechaDesde(dateInicio);
			EnfermedadTernera ternEnf = new EnfermedadTernera (pk,ternera,enfermedad);
			
			//terneraEnferma = this.daoTerneraEnferma.obtenerTerneraEnfermaFecha(ternEnf);
			terneraEnferma = this.enfermedadTerneraBeanRemote.obtenerTerneraEnfermaFecha(ternEnf);
			
			this.id = this.terneraEnferma.getId();
			this.fechaHasta = terneraEnferma.getFechaHasta();
			this.enfermedadId = String.valueOf(terneraEnferma.getEnfermedad().getIdEnfermedad());
			this.terneraId = String.valueOf(terneraEnferma.getTernera().getIdTernera());
			this.observacion = terneraEnferma.getObservacion();
	     
	      return "editarTerneraEnferma";
	}
	
	public void  formatoFechaEditar(){
		
		Date fechaFin = (Date) this.getFechaHasta();
		
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			if(!(fechaFin==null)){

				String dtf = dt1.format(fechaFin);
				dateFin = dt1.parse(dtf);
				
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean validarFechaEditar(){
		
		boolean fechaValida= false;
	
		
		Date fecha = new Date();
		if(!(this.fechaHasta == null)){
			if(dateFin.compareTo(fecha)<=0 && dateInicio.compareTo(dateFin)<=0){
				fechaValida= true;
			}
		}else if(this.fechaHasta==null){
			fechaValida=true;
		}
		
		return fechaValida;
		
	}
	public String editarTerneraEnferma() throws TerneraEnfermaException {
		
		FacesContext context = FacesContext.getCurrentInstance();	
		/*
		ternera = daoTernera.obtenerTerneraId(this.terneraEnferma.getId().getIdTernera());	
		enfermedad = daoEnfermedad.obtenerEnfermedadId(this.terneraEnferma.getId().getIdEnfermedad());
		*/
		
		
		ternera = ternerasBeanRemote.findTerneraPorId(this.terneraEnferma.getId().getIdTernera());	
		
		enfermedad = enfermedadBeanRemote.findEnfermedadPorId(this.terneraEnferma.getId().getIdEnfermedad());

		
		this.fechaDesde = this.terneraEnferma.getId().getFechaDesde();
		formatoFechaEditar();
		String aspectSan =  this.observacion;
		
		
				
		if(this.fechaHasta == null){
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique final esta vacia", "Fechas Incorrectas!"));
			return null;
			//return "ternerasEnfermas";
		}	
		
		if( !validarFechaEditar()){
				
				context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique las fechas: Fecha Inicio o Fin mayor a fecha actual", "Fechas Incorrectas!"));
				return null;
				//return "ternerasEnfermas";
		}		
		
		
		
		
		if (this.observacion.length()>=250) {
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Otros aspectos Sanitarios Excede de los 250 caracteres ", "Otros aspectos sanitarios!"));
			return null;
		}
		
	
		EnfermedadTernera terneraEnf = new EnfermedadTernera (terneraEnferma.getId(),ternera,enfermedad,dateFin,aspectSan);
		
	
		try {
			
			//daoTerneraEnferma.editarEnfermedadTernera(terneraEnf);
			
			enfermedadTerneraBeanRemote.editarTerneraEnferma(terneraEnf);
			
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,  "La Enfermedad por Ternera se ha sido editada con Exito.",
					"Enfermedad Editada!"));

			limpiarDatos();
			return "/ternerasEnfermas.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al almacenar. Intente nuevamente mas tarde","Error al editar!"));
			return null;
		}
	}
	private void limpiarDatos(){
		terneraId = null ;
		enfermedadId = null;
		id = null;
		fechaDesde = null;
		fechaHasta = null;
		observacion = null;
		enfermedad = null;
		ternera = null;
		
		terneraEnferma = null;
		dateInicio = null;
		dateFin = null;
		dateNacim = null;
	}
	
}
