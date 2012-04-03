package Sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Exceptions.*;

public class Sistema {
	List <String> usuariosLogados;
	LinkedList <Usuario> BD;
	String idSessao;
	
	public Sistema(){
		usuariosLogados = new ArrayList<String>();
		BD = new LinkedList<Usuario>();
		idSessao = "";
	}
	public void zerarSistema(){
		return;
	}
	
	public String criarUsuario(String login, String senha, String nome, String endereco, String email){
		try{
			Usuario novoUsuario = new Usuario(nome, email, endereco, senha, login);
			BD.add(novoUsuario);
		}catch (Exception e) {
			return e.getMessage();
		}
		return "";
		
	}
	
	public String criarUsuario(String login, String nome, String endereco, String email){
		
		try{
			if(login == null){
				throw new Exceptions.LoginInvalidoException();
			}
		}catch(LoginInvalidoException e){
//			return e.getMessage();
		}
		return "";
		
	}
	
	public String abrirSessao(String login, String senha){
		for (Usuario usuarioTemp : BD) {
			if(usuarioTemp.getLogin().equals(login) && usuarioTemp.getSenha().equals(senha)){
					idSessao = usuarioTemp.getIdSessao();
			}
		}
		return idSessao;
	}
	
	public String getAtributoUsuario(String login, String atributo){
		String retorno = "";
		for (Usuario usuarioTemp : BD) {
			if(usuarioTemp.getLogin().equals(login)){
				retorno = usuarioTemp.getAtributoUsuario(login, atributo);
			}
		
		}
		return retorno;
	}
	
}
