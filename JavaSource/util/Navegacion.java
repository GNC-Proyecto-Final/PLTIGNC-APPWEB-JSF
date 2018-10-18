package util;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="navegacion")
public class Navegacion {
	
	
	public String login(){
		return "inicio";
	}
	public String nuevaEnfermedad(){
		return "nuevaEnfermedad";
	}
	public String nuevaTerneraEnferma(){
		return "nuevaTerneraEnferma";
	}
	public String terneraEnfermedades(){
		return "enfermedades";
	}
	public String ternerasEnfermas(){
		return "ternerasEnfermas";
	}


}
