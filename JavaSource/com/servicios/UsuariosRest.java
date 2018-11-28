package com.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import DAO.DAOUsuariosBean;
import entidades.Usuario;

@Stateless
@Path("/usuarios")
public class UsuariosRest {

	@EJB
	DAOUsuariosBean daoUsuariosBean;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Usuario getUsuarios(String user, String password) {
		return daoUsuariosBean.obtenerUsuario(user, password);
		
	}
	
	
	@POST
	@Path("/{user}/{password}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Usuario getUsuariologin(@PathParam("user") String user, @PathParam("password") String password) {
		return daoUsuariosBean.obtenerUsuario(user, password);
	}
}
