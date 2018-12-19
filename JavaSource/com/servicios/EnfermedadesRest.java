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

import Controlador.EnfermedadBeanRemote;
import DAO.DAOEnfermedadesBean;
import entidades.Enfermedad;
import excepciones.GNCException;


@Stateless
@Path("/enfermedades")
public class EnfermedadesRest {
	/*
	@EJB
	DAOEnfermedadesBean daoEnfermedadesBean;
	*/
	@EJB
	EnfermedadBeanRemote enfermedadBeanRemote;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Enfermedad> getEnfermedad() {
		return enfermedadBeanRemote.obtenerTodasEnfermedades();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void addEnfermedad(Enfermedad enfermedad)  {
		try {
			enfermedadBeanRemote.ingresarNuevaEnfermedad(enfermedad);
		} catch (GNCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@DELETE
	@Path("/delete/{idEnfermedad}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public boolean deleteEnfermedad(@PathParam("idEnfermedad") long idEnfermedad) {
		
		try {
			return enfermedadBeanRemote.eliminarEnfermedadPorId(idEnfermedad);
		} catch (GNCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }

	@GET
    @Path("/{idEnfermedad}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Enfermedad getEnfermedadId(@PathParam("idEnfermedad") Long idEnfermedad) {
        return enfermedadBeanRemote.findEnfermedadPorId(idEnfermedad);
    }
	
	@GET
    @Path("/enfermedad/{idEnfermedad}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Enfermedad getFindEnfermedadId(@PathParam("idEnfermedad") Long idEnfermedad) {
        return enfermedadBeanRemote.findEnfermedadPorId(idEnfermedad);
    }
	
	@POST
    @Path("/enfermedadExiste")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean  existeEnfermedad(Enfermedad enfermedad)  {
		return enfermedadBeanRemote.existeEnfermedad(enfermedad);
	}
	
}
