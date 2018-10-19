package BeanGnc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.DAOEnfermedadesBean;
import entidades.Enfermedad;



@ManagedBean(name="Enfermedades")
@ViewScoped
public class EnfermedadBeanDT {
	
	@EJB
	private DAOEnfermedadesBean DAOEnfermedadesBean;
	
	private Enfermedad enfermedad; 
	private List<Enfermedad> enfermedades = null;
	
	private List<Enfermedad> filteredEnfermedades;
	private Enfermedad selectedEnfermedad; 
	
	public  EnfermedadBeanDT(){
		
	}
	
	@PostConstruct
	public void getEnfermedadesList() {
		enfermedades = (List<Enfermedad>) DAOEnfermedadesBean.obtenerTodasEnfermedades();
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public List<Enfermedad> getFilteredEnfermedades() {
		return filteredEnfermedades;
	}

	public void setFilteredEnfermedades(List<Enfermedad> filteredEnfermedades) {
		this.filteredEnfermedades = filteredEnfermedades;
	}

	public Enfermedad getSelectedEnfermedad() {
		return selectedEnfermedad;
	}

	public void setSelectedEnfermedad(Enfermedad selectedEnfermedad) {
		this.selectedEnfermedad = selectedEnfermedad;
	}

	
    
}
