package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.DAOEnfermedadesBean;
import entidades.Enfermedad;


@Stateless
@Path("/enfermedades")
public class EnfermedadesRest {
	
	@EJB
	DAOEnfermedadesBean daoEnfermedadesBean;
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Enfermedad> getEnfermedad() {
		return daoEnfermedadesBean.obtenerTodasEnfermedades();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void addEnfermedad(Enfermedad enfermedad) {
		daoEnfermedadesBean.crearEnfermedad(enfermedad);

	}
	
	@DELETE
    @Path("/{enf}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteEnfermedad(Enfermedad enfermedad) {
        daoEnfermedadesBean.eliminarEnfermedad(enfermedad);
    }
	
	@GET
	@Path("/{idEnf}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Enfermedad getEnfermedadId(@PathParam("idEnfermedad") long idEnfermedad) {
		return daoEnfermedadesBean.obtenerEnfermedadId(idEnfermedad);
	}
}
