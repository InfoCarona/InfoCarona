package Sistema;

import java.util.LinkedList;
import java.util.List;

public class Perfil {
	
	private Usuario usuario;
	private List<Carona> listaDeCaronas;
	
	public Perfil(Usuario usuario){
		this.usuario = usuario;
		this.listaDeCaronas = new LinkedList<Carona>();
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, int vagas){
		String idCarona = ("carona" + (listaDeCaronas.size()+1) + "ID");
		Carona carona = new Carona(idSessao, origem, destino, data, hora, vagas, idCarona);
		listaDeCaronas.add(carona);
		return idCarona;
	}
	
	public List<String> localizarCarona(String idSessao, String origem, String destino){
		return new LinkedList<String>();
	}
}
