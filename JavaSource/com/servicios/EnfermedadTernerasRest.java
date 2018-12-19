package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Controlador.EnfermedadTerneraBeanRemote;
import DAO.DAOEnfermedadTernerasBean;
import entidades.EnfermedadTernera;
import excepciones.GNCException;

@Stateless
@Path("/enfermedadTerneras")
public class EnfermedadTernerasRest {
	/*
	
	@EJB
	DAOEnfermedadTernerasBean daoEnfermedadTernerasBean;
	*/
	@EJB
	EnfermedadTerneraBeanRemote enfermedadTerneraBeanRemote;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void addEnfermedadTernera(EnfermedadTernera enfermedadTernera) {
		try {
			enfermedadTerneraBeanRemote.crearTerneraEnferma(enfermedadTernera);
		} catch (GNCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateEnfermedadTernera(EnfermedadTernera enfermedadTernera) {
		try {
			enfermedadTerneraBeanRemote.editarTerneraEnferma(enfermedadTernera);
		} catch (GNCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<EnfermedadTernera> getEnfermedadTernera() {
		return enfermedadTerneraBeanRemote.obtenerTodasEnfermedadesTerneras();
	}
	
	@GET
	@Path("/informe")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<EnfermedadTernera> getObtenerInformeTodasEnfermedadesTerneras() {
		return enfermedadTerneraBeanRemote.obtenerInformeTodasEnfermedadesTerneras();
	}
	
	@POST
	@Path("/existe")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean getTerneraEnfermaFechaExiste(EnfermedadTernera enfermedadTernera) {
		return enfermedadTerneraBeanRemote.obtenerTerneraEnfermaFechaExiste(enfermedadTernera);
	}

	
	@POST
	@Path("/existe/{idEnfermedad}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean getTerneraEnfermaExiste(@PathParam("idEnfermedad") long idEnfermedad) {
		return enfermedadTerneraBeanRemote.existeEnfermedadEnTernaraEnfermedad(idEnfermedad);
	}
	

	@GET
	@Path("/fecha")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public EnfermedadTernera getTerneraEnfermaFecha( EnfermedadTernera enfermedadTernera) {
		return enfermedadTerneraBeanRemote.obtenerTerneraEnfermaFecha(enfermedadTernera);
	}
}
