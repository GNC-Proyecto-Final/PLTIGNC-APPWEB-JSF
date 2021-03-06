package BeanGnc;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import Controlador.UsuariosBeanRemote;
import entidades.Usuario;
import util.SessionUtils;


@ManagedBean(name="usuario")
@SessionScoped
public class UsuarioBean {
	
	/*@EJB
	private DAOUsuariosBean DAOUsuariosBean;*/
	
	@EJB
	private UsuariosBeanRemote usuariosBeanRemote;
	
	private long idUsuario;
	private String apellido;
	private String contrasenia;
	private String nombre;
	private String perfil;
	private String usuario;

	

	public UsuarioBean() {
		super();
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	//validar login
	/**
	 * Valido el login y inicio una session
	 * */
	public String loginUsuario(){
		
		try{
				FacesContext context = FacesContext.getCurrentInstance();
				//Usuario us = DAOUsuariosBean.obtenerUsuario(usuario, contrasenia);
				Usuario us = usuariosBeanRemote.obtenerUsuario(usuario, contrasenia);
				
				if (us != null) {
					
					HttpSession session = SessionUtils.getSession();
					session.setAttribute("username", us.getUsuario());
					this.setApellido(us.getApellido());
					this.setNombre(us.getNombre());
					this.setPerfil(us.getPerfil());
					this.setIdUsuario(us.getIdUsuario());
					
					return "/bienvenido.xhtml?faces-redirect=true";
				}else{

							 	context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Usuario y/o Contrase�a Incorrecto",
									"Por favor ingrese Usuario y contrase�a correcto"));
				
					return "inicio";
				}
			
		}catch(Exception e){
			return null;
		}
	}
	//logout
	/**
	 * Cierro la session del usuario
	 * */
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/inicio.xhtml?faces-redirect=true";
	}
}
