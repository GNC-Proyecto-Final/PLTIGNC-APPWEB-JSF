package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Controlador.TernerasBeanRemote;
import DAO.DAOTernerasBean;
import entidades.Ternera;


@Stateless
@Path("/terneras")
public class TernerasRest {
	
	/*
	@EJB
	private DAOTernerasBean daoTernerasBean;
	*/
	
	@EJB
	private TernerasBeanRemote ternerasBeanRemote;
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Ternera> getTerneras() {
		return ternerasBeanRemote.obtenerTodasTerneras();
	
	}
	
	@GET
	@Path("/{nroCvna}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Ternera getTerneraNroCvna(@PathParam("nroCaravana") long nroCaravana) {
		return ternerasBeanRemote.obtenerTerneraNroCaravana(nroCaravana);
		
	}
	
	@GET
	@Path("/{idTern}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Ternera getTerneraId(@PathParam("idTernera") long idTernera) {
		return ternerasBeanRemote.findTerneraPorId(idTernera);
	}
	
	@GET
	@Path("/ternera/{idTernera}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Ternera getFindTerneraId(@PathParam("idTernera") long idTernera) {
		return ternerasBeanRemote.obtenerTerneraPorId(idTernera);
	}
	
}
