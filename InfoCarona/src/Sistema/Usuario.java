package Sistema;

import Exception.ExceptionUsuario.LoginInvalidoException;

/*
 * 
 */
public class Usuario {
	private String nome, email, endereco, senha, id, login;
	
	
	public Usuario(String nome, String email, String endereco, String senha, String login){
		
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.senha = senha;
		this.login = login;
		this.id  = "sessao" + login.substring(0,1).toUpperCase() + login.substring(1, login.length());
		
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public String getIdSessao(){
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getAtributoUsuario(String login, String atributo){
		String retorno = "";
		if(atributo.equals("nome")){
			retorno = nome;
		}else if(atributo.equals("endereco")){
			retorno = endereco;
		}
		
		return retorno;
	}
	
	public String toString(){
		return "Nome: "+nome + "    Login: " + login;
	}
	
	
}
