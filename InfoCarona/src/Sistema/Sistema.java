package Sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Exception.ExceptionUsuario.AtributoInexistenteException;
import Exception.ExceptionUsuario.AtributoInvalidoException;
import Exception.ExceptionUsuario.EmailExistenteException;
import Exception.ExceptionUsuario.EmailInvalidoException;
import Exception.ExceptionUsuario.LoginExistenteException;
import Exception.ExceptionUsuario.LoginInvalidoException;
import Exception.ExceptionUsuario.NomeInvalidoException;
import Exception.ExceptionUsuario.UsuarioInexistenteException;
import Exception.ExceptionsCarona.CaronaInexistenteException;
import Exception.ExceptionsCarona.CaronaInvalidaException;
import Exception.ExceptionsCarona.DataInvalidaException;
import Exception.ExceptionsCarona.DestinoInvalidoException;
import Exception.ExceptionsCarona.HoraInvalidaException;
import Exception.ExceptionsCarona.IDCaronaInexistenteException;
import Exception.ExceptionsCarona.ItemInexistenteException;
import Exception.ExceptionsCarona.OrigemInvalidaException;
import Exception.ExceptionsCarona.SessaoInexistenteException;
import Exception.ExceptionsCarona.SessaoInvalidaException;
import Exception.ExceptionsCarona.TrajetoInexistenteException;
import Exception.ExceptionsCarona.TrajetoInvalidoException;

public class Sistema {
	LinkedList <Perfil> BD;
	String idSessao;
	Perfil perfil;
	
	public Sistema(){
		BD = new LinkedList<Perfil>();
		idSessao = "";
	}
	public void zerarSistema(){
		return;
	}
	
	public String criarUsuario(String login, String senha, String nome, String endereco, String email){
		try{
			Usuario novoUsuario = new Usuario(nome, email, endereco, senha, login);
			Perfil novoPerfil = new Perfil(novoUsuario);
			BD.add(novoPerfil);
		}catch (Exception e) {
			return e.getMessage();
		}
		return "";
		
	}
	
	public String criarUsuario(String login, String nome, String endereco, String email) throws LoginInvalidoException, NomeInvalidoException, EmailInvalidoException, EmailExistenteException, LoginExistenteException{
				
			if(checaLogin(login)){
				throw new LoginInvalidoException();
			}
			if(checaNome(nome)){
				throw new NomeInvalidoException();
			}
			if(checaEmail(email)){
				throw new EmailInvalidoException();
			}
			java.util.Iterator<Perfil> iterador = BD.iterator();
			while(iterador.hasNext()){
				Perfil perfilTemp = iterador.next();
				Usuario userTemp = perfilTemp.getUsuario();
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
			throw new LoginInvalidoException();
		}
		if(checaNome(nome)){
			throw new NomeInvalidoException();
		}
		
		java.util.Iterator<Perfil> iterador = BD.iterator();
		while(iterador.hasNext()){
			Perfil perfilTemp = iterador.next();
			Usuario userTemp = perfilTemp.getUsuario();
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
		for (Perfil perfilTemp : BD) {
			Usuario usuarioTemp = perfilTemp.getUsuario();
			if(usuarioTemp.getLogin().equals(login)) { 
				userExist = true;
				if(usuarioTemp.getSenha().equals(senha)){
					idSessao = usuarioTemp.getIdSessao();
					perfil = perfilTemp;
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
		for (Perfil perfilTemp : BD) {
			Usuario usuarioTemp = perfilTemp.getUsuario();
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
	public void encerrarSistema(){
		
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

	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, int vagas) throws SessaoInvalidaException, SessaoInexistenteException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException{
		return perfil.cadastrarCarona(idSessao, origem, destino, data, hora, vagas);
	}
	
	public String localizarCarona(String idSessao, String origem, String destino){
		return perfil.localizarCarona(idSessao, origem, destino);
	}
	
	public String getAtributoCarona(String idCarona,String atributo) throws ItemInexistenteException, IDCaronaInexistenteException{
		return perfil.getAtributoCarona(idCarona, atributo);
	}
	public String getTrajeto(String idCarona) throws TrajetoInexistenteException, TrajetoInvalidoException{
		return perfil.getTrajeto(idCarona);
	}
	public String getCarona(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		return perfil.getCarona(idCarona);
	}
}
