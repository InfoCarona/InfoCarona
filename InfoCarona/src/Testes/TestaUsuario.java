package Testes;

import junit.framework.Assert;

import org.junit.Test;

import Exception.ExceptionUsuario.EmailInvalidoException;
import Exception.ExceptionUsuario.EnderecoInvalidoException;
import Exception.ExceptionUsuario.LoginInvalidoException;
import Exception.ExceptionUsuario.NomeInvalidoException;
import Exception.ExceptionUsuario.SenhaInvalidoException;
import Sistema.Usuario;


public class TestaUsuario {
	Usuario usuario1, usuario2, usuario3, usuario4, usuario5;

	private void criaUsuario1() throws Exception{
		usuario1 = new Usuario("wallison", "walli@email", "UFCG", "1234", "walli");
	}
	
	private void criaUsuario2() throws Exception{
		usuario2 = new Usuario("", "walli@email", "UFCG", "1234", "walli");
	}
	
	private void criaUsuario3() throws Exception{
		usuario2 = new Usuario(null, "walli@email", "UFCG", "1234", "walli");
	}
	
	private void criaUsuario4() throws Exception{
		usuario3 = new Usuario("gilles", "", "UFCG", "4321", "gillesVovo");
	}
	
	private void criaUsuario5() throws Exception{
		usuario3 = new Usuario("gilles", null, "UFCG", "4321", "gillesVovo");
	}
	
	private void criaUsuario6() throws Exception{
		usuario4 = new Usuario("felipe", "felipe@email", "UFCG", "1234", "");
	}
	
	private void criaUsuario7() throws Exception{
		usuario4 = new Usuario("felipe", "felipe@email", "UFCG", "1234", null);
	}
	
	@Test
	public void Testaconstrutor() throws Exception{
		try {
			criaUsuario1();
		} catch (Exception e) {
			Assert.fail();
		}
		
		try{
			criaUsuario2();
			Assert.fail();
		}catch(NomeInvalidoException e){
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		
		try{
			criaUsuario3();
			Assert.fail();
		}catch(NomeInvalidoException e){
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		
		try{
			criaUsuario4();
			Assert.fail();
		}catch(EmailInvalidoException e){
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		
		try{
			criaUsuario5();
			Assert.fail();
		}catch(EmailInvalidoException e){
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		
		try{
			criaUsuario6();
			Assert.fail();
		}catch(LoginInvalidoException e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		try{
			criaUsuario7();
			Assert.fail();
		}catch(LoginInvalidoException e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
	}
	
}
