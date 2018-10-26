package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import DAO.DAOTernerasBean;
import entidades.Ternera;


@Path("/terneras")
public class TernerasRest {
	
	@EJB
	private DAOTernerasBean daoTernerasBean;
	

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Ternera> getTerneras() {
		return daoTernerasBean.obtenerTodasTerneras();
	
	}
	
	@GET
	@Path("/{nroCvna}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Ternera getTerneraNroCvna(@PathParam("nroCaravana") long nroCaravana) {
		return daoTernerasBean.obtenerTerneraNroCaravana(nroCaravana);
		
	}
	
	@GET
	@Path("/{idTern}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Ternera getTerneraId(@PathParam("idTernera") long idTernera) {
		return daoTernerasBean.obtenerTerneraId(idTernera);
	}
	
	
}
