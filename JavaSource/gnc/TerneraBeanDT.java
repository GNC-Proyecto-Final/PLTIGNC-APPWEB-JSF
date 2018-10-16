package gnc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.DAOTernerasBean;
import entidades.Ternera;



@ManagedBean(name="ternerasDT")
@ViewScoped
public class TerneraBeanDT {
	
	@EJB
	private DAOTernerasBean daoTerneras;
	
	private Ternera ternera; 
	private List<Ternera> terneras = null;
	
	private List<Ternera> filteredTerneras;
	private Ternera selectedTernera; 
	
	public  TerneraBeanDT(){
		
	}
	
	@PostConstruct
	public void getEnfermedadesList() {
		terneras = (List<Ternera>) daoTerneras.obtenerTodasTerneras();
	}

	public Ternera getTernera() {
		return ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

	public List<Ternera> getTerneras() {
		return terneras;
	}

	public void setTerneras(List<Ternera> terneras) {
		this.terneras = terneras;
	}

	public List<Ternera> getFilteredTerneras() {
		return filteredTerneras;
	}

	public void setFilteredTerneras(List<Ternera> filteredTerneras) {
		this.filteredTerneras = filteredTerneras;
	}

	public Ternera getSelectedTernera() {
		return selectedTernera;
	}

	public void setSelectedTernera(Ternera selectedTernera) {
		this.selectedTernera = selectedTernera;
	}

	

	
    
}
