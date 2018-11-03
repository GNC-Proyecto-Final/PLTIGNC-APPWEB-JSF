package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.DAOUsuariosBean;
import entidades.Enfermedad;
import entidades.Usuario;

@Path("/usuarios")
public class UsuarioRest {
	
	@EJB
	DAOUsuariosBean daoUsuariosBean;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Usuario getUsuarios(String user, String password) {
		return daoUsuariosBean.obtenerUsuario(user, password);
		
	}

}
