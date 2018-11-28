package util;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="navegacion")
public class Navegacion {
	
	
	public String login(){
		return "/inicio";
	}
	public String nuevaEnfermedad(){
		return "/nuevaEnfermedad.xhtml?faces-redirect=true";
	}
	public String nuevaTerneraEnferma(){
		return "/nuevaTerneraEnferma.xhtml?faces-redirect=true";
	}
	public String terneraEnfermedades(){
		return "/enfermedades.xhtml?faces-redirect=true";
	}
	public String ternerasEnfermas(){
		return "/ternerasEnfermas.xhtml?faces-redirect=true";
	}


}
