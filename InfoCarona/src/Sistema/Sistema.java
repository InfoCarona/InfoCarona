package Sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

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
	
	public String criarUsuario(String login, String nome, String endereco, String email) throws LoginInvalidoException, NomeInvalidoException, EmailInvalidoException, EmailExistenteException, LoginExistenteException{
		
		
			if(login == null || login.equals("")){
				throw new Exceptions.LoginInvalidoException();
			}
			if(nome == null || nome.equals("")){
				throw new NomeInvalidoException();
			}
			if(email == null || email.equals("")){
				throw new EmailInvalidoException();
			}
			java.util.Iterator<Usuario> iterador = BD.iterator();
			while(iterador.hasNext()){
				Usuario userTemp = iterador.next();
				if(userTemp.email.equals(email)){
					throw new EmailExistenteException();
				}
				if(userTemp.login.equals(login)){
					throw new LoginExistenteException();
				}
			}
				return "";
	}
	
	public String criarUsuario(String login, String nome, String endereco) throws LoginInvalidoException, NomeInvalidoException, EmailInvalidoException, EmailExistenteException, LoginExistenteException{
		
		
		if(login == null || login.equals("")){
			throw new Exceptions.LoginInvalidoException();
		}
		if(nome == null || nome.equals("")){
			throw new NomeInvalidoException();
		}
		
		java.util.Iterator<Usuario> iterador = BD.iterator();
		while(iterador.hasNext()){
			Usuario userTemp = iterador.next();
			if(userTemp.login.equals(login)){
				throw new LoginExistenteException();
			}
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
