package Sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Exceptions.AtributoInexistenteException;
import Exceptions.AtributoInvalidoException;
import Exceptions.EmailExistenteException;
import Exceptions.EmailInvalidoException;
import Exceptions.LoginExistenteException;
import Exceptions.LoginInvalidoException;
import Exceptions.NomeInvalidoException;
import Exceptions.UsuarioInexistenteException;

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
				
			if(checaLogin(login)){
				throw new Exceptions.LoginInvalidoException();
			}
			if(checaNome(nome)){
				throw new NomeInvalidoException();
			}
			if(checaEmail(email)){
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
				
		if(checaLogin(login)){
			throw new Exceptions.LoginInvalidoException();
		}
		if(checaNome(nome)){
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
	
	public String abrirSessao(String login, String senha) throws LoginInvalidoException, UsuarioInexistenteException{
		boolean userExist = false;
		if(checaLogin(login)){
			throw new LoginInvalidoException();
		}
		for (Usuario usuarioTemp : BD) {
			if(usuarioTemp.getLogin().equals(login)) { 
				userExist = true;
				if(usuarioTemp.getSenha().equals(senha)){
					idSessao = usuarioTemp.getIdSessao();
				}else{
					throw new LoginInvalidoException();
				}
			}
		}
		if(!userExist){
			throw new UsuarioInexistenteException();
		}
		return idSessao;
	}
	
	public String getAtributoUsuario(String login, String atributo) throws LoginInvalidoException, AtributoInvalidoException, UsuarioInexistenteException, AtributoInexistenteException{
		
		if(checaLogin(login)){
			throw new LoginInvalidoException();
		}
		if(checaAtributo(atributo)){
			throw new AtributoInvalidoException();
		}
		if(!checaAtributoValido(atributo)){
			throw new AtributoInexistenteException();
		}
		String retorno = "";
		boolean userExist = false;
		for (Usuario usuarioTemp : BD) {
			if(usuarioTemp.getLogin().equals(login)){
				userExist = true;
				retorno = usuarioTemp.getAtributoUsuario(login, atributo);
			}		
		}
		if(!userExist){
			throw new UsuarioInexistenteException();
		}
		return retorno;
	}
	
	// metodo pra ver se o atributo passado existe
	private boolean checaAtributoValido(String atributo) {		
		return (atributo.equals("nome") || atributo.equals("endereco") || atributo.equals("email"));
	}
	// Metodos abaixo servem para checar se os atributos para criar são passados como null ou vazio
	private boolean checaLogin(String login){
		return (login == null || login.equals(""));
	}
	
	private boolean checaNome(String nome){
		return (nome == null || nome.equals(""));
	}
	
	private boolean checaEmail(String email){
		return (email == null || email.equals(""));
	}
	
	private boolean checaAtributo(String atributo) {		
		return (atributo == null || atributo.equals(""));
	}
	
}
