package Sistema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Exception.ExceptionsCarona.CaronaInexistenteException;
import Exception.ExceptionsCarona.CaronaInvalidaException;

public class Repositorio {
	List<Usuario> listaDeUsuarios;
	List<Carona> listaDeCaronas;
	Iterator<Carona> iterador;
	public Repositorio(){
		criaRepositorio();
	}
	
	private void criaRepositorio(){
		listaDeUsuarios = new LinkedList<Usuario>();

	}
	
	/////metodos que estao corretos no repositorio/////
		

	
	public void addUsuario(Usuario user){
		listaDeUsuarios.add(user);
	}
	
	public void removeUsuarioLogin(Usuario user){
		listaDeUsuarios.remove(user);
	}

	public List<Usuario> getTodosOsUsuarios(){
		return listaDeUsuarios;
	}
	
 	public Usuario buscaUsuarioLogin(String login){
		Usuario retorno = null;
		for (Usuario user: listaDeUsuarios){
			if(user.getLogin().equals(login)){
				retorno = user;
			}
		}
		
		return retorno;
	}
	
	public Usuario buscaUsuarioEmail(String email){
		Usuario retorno = null;
		for (Usuario user: listaDeUsuarios){
			if(user.getEmail().equals(email)){
				retorno = user;
			}
		}
		
		return retorno;
	}

	public List<Usuario> buscaUsuarioNome(String nome){
		List<Usuario> retorno = new LinkedList<Usuario>();
		
		for (Usuario user: listaDeUsuarios){
			if(user.getNome().equals(nome)){
				retorno.add(user);
			}
		}
		
		return retorno;
	}
	
	public List<Carona> getTodasAsCaronas(){
		List<Carona> retorno = new LinkedList<Carona>();
		
		for(Usuario user: listaDeUsuarios){
			for(Carona caronaTemp : user.getCaronas()){
				retorno.add(caronaTemp);
			}
		}
		return retorno;
	}
	
	public Carona getCaronaId(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		Carona retorno = null;
		
		for(Usuario user: listaDeUsuarios){
			try{
				retorno = user.getCarona(idCarona);
			}catch(Exception e){
				retorno = null;
			}
		}
		return retorno;
	}
	
	
	////////////metodos do repositorio onde a responsabilidade de pesquisar as caronas eh do repositorio
	public List<Carona> buscaCaronasOrigemDestino(String origem, String destino) throws CaronaInexistenteException, CaronaInvalidaException{
		List<Carona> caronas = new LinkedList<Carona>();
		for(Usuario usuarioTemp : listaDeUsuarios){
			for(Carona caronaTemp: usuarioTemp.getCaronas()){
				if((caronaTemp.getOrigem().equals(origem) && caronaTemp.getDestino().equals(destino))){
					caronas.add(caronaTemp);
				}
			}
		}
		return caronas;
	}
	
	public List<Carona> buscaCaronasOrigem(String origem){
		List<Carona> caronas = new LinkedList<Carona>();
		for(Usuario usuarioTemp : listaDeUsuarios){
			for(Carona caronaTemp: usuarioTemp.getCaronas()){
				if(caronaTemp.getOrigem().equals(origem)){
					caronas.add(caronaTemp);
				}
			}
		}
		return caronas;
	}
	
	public List<Carona> buscaCaronasDestino(String destino){
		List<Carona> caronas = new LinkedList<Carona>();
		for(Usuario usuarioTemp : listaDeUsuarios){
			for(Carona caronaTemp: usuarioTemp.getCaronas()){
				if(caronaTemp.getDestino().equals(destino)){
					caronas.add(caronaTemp);
				}
			}
		}
		return caronas;
	}
	
	//////// metodos onde eu utilizo um iterador para varrer as caronas
	public void criaIterador(){
		listaDeCaronas = getTodasAsCaronas();
		iterador =  listaDeCaronas.iterator();
	}
	
	public boolean temCarona(){
		return iterador.hasNext();
	}
	
	public Carona proximaCarona(){
		return iterador.next();
	}
}
