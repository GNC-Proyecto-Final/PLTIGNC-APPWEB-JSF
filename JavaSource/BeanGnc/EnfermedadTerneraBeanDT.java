package BeanGnc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Controlador.EnfermedadTerneraBeanRemote;
import entidades.EnfermedadTernera;



@ManagedBean(name="TerneraEnferma")
@ViewScoped
public class EnfermedadTerneraBeanDT {
	
	/*
	@EJB
	private DAOEnfermedadTernerasBean daoTenerasEnfermas;
	*/
	
	@EJB
	private EnfermedadTerneraBeanRemote enfermedadTerneraBeanRemote;
	
	private EnfermedadTernera terneraEnferma; 
	private List<EnfermedadTernera> ternerasEnfermas = null;
	
	private List<EnfermedadTernera> filtrarTernerasEnfermas;
	private EnfermedadTernera terneraEnfermaSeleccionada; 
	
	public  EnfermedadTerneraBeanDT(){
		
	}
	
	@PostConstruct
	public void getEnfermedadesList() {
		//ternerasEnfermas = (List<EnfermedadTernera>) daoTenerasEnfermas.obtenerTodasEnfermedadesTerneras();
		ternerasEnfermas = (List<EnfermedadTernera>) enfermedadTerneraBeanRemote.obtenerInformeTodasEnfermedadesTerneras();
	}


	

	public EnfermedadTernera getTerneraEnferma() {
		return terneraEnferma;
	}

	public void setTerneraEnferma(EnfermedadTernera terneraEnferma) {
		this.terneraEnferma = terneraEnferma;
	}

	public List<EnfermedadTernera> getTernerasEnfermas() {
		return ternerasEnfermas;
	}

	public void setTernerasEnfermas(List<EnfermedadTernera> ternerasEnfermas) {
		this.ternerasEnfermas = ternerasEnfermas;
	}

	public List<EnfermedadTernera> getFiltrarTernerasEnfermas() {
		return filtrarTernerasEnfermas;
	}

	public void setFiltrarTernerasEnfermas(List<EnfermedadTernera> filtrarTernerasEnfermas) {
		this.filtrarTernerasEnfermas = filtrarTernerasEnfermas;
	}

	public EnfermedadTernera getTerneraEnfermaSeleccionada() {
		return terneraEnfermaSeleccionada;
	}

	public void setTerneraEnfermaSeleccionada(EnfermedadTernera terneraEnfermaSeleccionada) {
		this.terneraEnfermaSeleccionada = terneraEnfermaSeleccionada;
	}

	

	
    
}
