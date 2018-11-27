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

import DAO.DAOEnfermedadTernerasBean;
import entidades.EnfermedadTernera;

@Stateless
@Path("/enfermedadTerneras")
public class EnfermedadTernerasRest {
	
	@EJB
	DAOEnfermedadTernerasBean daoEnfermedadTernerasBean;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void addEnfermedadTernera(EnfermedadTernera enfermedadTernera) {
		daoEnfermedadTernerasBean.crearEnfermedadTernera(enfermedadTernera);

	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateEnfermedadTernera(EnfermedadTernera enfermedadTernera) {
		daoEnfermedadTernerasBean.editarEnfermedadTernera(enfermedadTernera);

	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<EnfermedadTernera> getEnfermedadTernera() {
		return daoEnfermedadTernerasBean.obtenerTodasEnfermedadesTerneras();
	}

	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean getTerneraEnfermaFechaExiste(EnfermedadTernera enfermedadTernera) {
		return daoEnfermedadTernerasBean.obtenerTerneraEnfermaFechaExiste(enfermedadTernera);
	}
	
	@GET
	@Path("/{idTernEnf}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean getTerneraEnfermaExiste(@PathParam("idEnfermedad") long idEnfermedad) {
		return daoEnfermedadTernerasBean.obtenerTerneraEnfermaExiste(idEnfermedad);
	}
	

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public EnfermedadTernera getTerneraEnfermaFecha(EnfermedadTernera enfermedadTernera) {
		return daoEnfermedadTernerasBean.obtenerTerneraEnfermaFecha(enfermedadTernera);
	}
}
