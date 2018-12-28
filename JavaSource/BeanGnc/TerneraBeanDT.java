package BeanGnc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Controlador.TernerasBeanRemote;
import entidades.Ternera;
import excepciones.TerneraException;



@ManagedBean(name="ternerasDT")
@ViewScoped
public class TerneraBeanDT {
	
	/*
	@EJB
	private DAOTernerasBean daoTerneras;
	*/
	
	@EJB
	private TernerasBeanRemote ternerasBeanRemote;
	
	private Ternera ternera; 
	private List<Ternera> terneras = null;
	
	private List<Ternera> filteredTerneras;
	private Ternera selectedTernera; 
	
	public  TerneraBeanDT(){
		
	}
	
	@PostConstruct
	public void getEnfermedadesList()  {
		//terneras = (List<Ternera>) daoTerneras.obtenerTodasTerneras();
		try {
			terneras = (List<Ternera>) ternerasBeanRemote.obtenerTodasTerneras();
		} catch (TerneraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
