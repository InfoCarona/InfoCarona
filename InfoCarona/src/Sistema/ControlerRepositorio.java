package Sistema;

import java.util.LinkedList;
import java.util.List;

import Exception.ExceptionUsuario.UsuarioInexistenteException;
import Exception.ExceptionsCarona.CaronaInexistenteException;
import Exception.ExceptionsCarona.CaronaInvalidaException;

public class ControlerRepositorio {
	Repositorio repositorio;
	public ControlerRepositorio(){
		repositorio = new Repositorio();
	}
	public String getAtributoUsuario(String login, String atributo) throws UsuarioInexistenteException{
		String retorno = "";
		Usuario usuarioTemp = this.getUsuario(login);
		
		if (atributo.equals("nome")) {
			retorno = usuarioTemp.getNome();
		} else if (atributo.equals("endereco")) {
			retorno = usuarioTemp.getEndereco();
		} else if (atributo.equals("email")) {
			retorno = usuarioTemp.getEmail();
		}
			//			else if (atributo.equals("historico de caronas")) {
			//			for (Carona caronaTemp : listaDeCaronas) {
			//				retorno += caronaTemp.getDadosCarona();
			//			}
			//		}else if (atributo.equals("historico de vagas em caronas")) {
			//			for (SolicitacaoDeVaga solicitacaoTemp : listaDeSolicitacaoDeVagas) {
			//				retorno += solicitacaoTemp.toString();
			//			}
			//			
			//		}else if (atributo.equals("caronas seguras e tranquilas")){
			//			retorno = this.caronasSeguras + "";
			//		}else if (atributo.equals("caronas que não funcionaram")){
			//			retorno = this.caronaNaoFuncionaram + "";
			//		}else if (atributo.equals("faltas em vagas de caronas")){
			//			retorno = this.faltasEmVagas + "";
			//		}else if (atributo.equals("presenças em vagas de caronas")){
			//			retorno = this.presencaEmVagas + "";
			//		}
		
		return retorno;
	}
	
	public Usuario getUsuario(String login) throws UsuarioInexistenteException{
		Usuario usuarioTemp = repositorio.buscaUsuarioLogin(login);
		if(usuarioTemp == null){
			throw new UsuarioInexistenteException();
		}
		return usuarioTemp;
	}
	
	public List<Carona> getTodasAsCaronas(){
		return repositorio.getTodasAsCaronas();
	}
	
	
	
	///existe tbm os metodos no repositorio onde jah faz a pesquisa da carona por origem e destino, só é implementar aki pra chamar os do repositorio direto
	
	//////////metodos onde o repositorio retorna uma lista com todas as caronas e a responsabilidade de filtrar elas eh do controle
	public List<Carona> getCaronaOrigemDestino(String origem, String destino) throws CaronaInexistenteException, CaronaInvalidaException{
		List<Carona> retorno = null;
		if(!(origem.equals("") && destino.equals(""))){
			retorno = this.buscaCaronasOrigemDestino(origem, destino);
		}
		else if(!(origem.equals("")) && (destino.equals(""))){
			retorno = this.buscaCaronasOrigem(origem);
		}
		else if(origem.equals("") && (!(destino.equals("")))){
			retorno = this.buscaCaronasDestino(destino);
		}
		return retorno;
	}	
	
	public List<Carona> buscaCaronasOrigemDestino(String origem, String destino) throws CaronaInexistenteException, CaronaInvalidaException{
		List<Carona> caronas = this.getTodasAsCaronas();
		
		for(Carona caronaTemp: caronas){
			if(!(caronaTemp.getOrigem().equals(origem) && caronaTemp.getDestino().equals(destino))){
				caronas.remove(caronaTemp);
			}
		}
		return caronas;
	}
	
	public List<Carona> buscaCaronasOrigem(String origem){
		List<Carona> caronas = this.getTodasAsCaronas();
		
		for(Carona caronaTemp: caronas){
			if(!(caronaTemp.getOrigem().equals(origem))){
				caronas.remove(caronaTemp);
			}
		}
		return caronas;
	}
	
	public List<Carona> buscaCaronasDestino(String destino){
		List<Carona> caronas = this.getTodasAsCaronas();
		
		for(Carona caronaTemp: caronas){
			if(!(caronaTemp.getDestino().equals(destino))){
				caronas.remove(caronaTemp);
			}
		}
		return caronas;
	}
	

	
	////////// metodos que usam os metodos do iterador do repositorio, acho q ele consome mais pq eu tenho q ter a lista das caronas
	// e pra isso lá se foi 2 for neh, mas tah aii pra vcs excolherem
	public List<Carona> buscaCaronasOrigemDestino2(String origem, String destino) throws CaronaInexistenteException, CaronaInvalidaException{
		List<Carona> caronas = new LinkedList<Carona>();
		while(repositorio.temCarona()){
			Carona caronaTemp = repositorio.proximaCarona();
			if((caronaTemp.getOrigem().equals(origem) && caronaTemp.getDestino().equals(destino))){
				caronas.add(caronaTemp);
			}
		}
		return caronas;
	}
	
	public List<Carona> buscaCaronasOrigem2(String origem){
		List<Carona> caronas = new LinkedList<Carona>();
		while(repositorio.temCarona()){
			Carona caronaTemp = repositorio.proximaCarona();
			if((caronaTemp.getOrigem().equals(origem))){
				caronas.add(caronaTemp);
			}
		}
		return caronas;
	}
	
	public List<Carona> buscaCaronasDestino2(String destino){
		List<Carona> caronas = new LinkedList<Carona>();
		while(repositorio.temCarona()){
			Carona caronaTemp = repositorio.proximaCarona();
			if((caronaTemp.getDestino().equals(destino))){
				caronas.add(caronaTemp);
			}
		}
		return caronas;
	}
	
}
