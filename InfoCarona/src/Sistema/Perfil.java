package Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import Exception.ExceptionsCarona.DataInvalidaException;
import Exception.ExceptionsCarona.DestinoInvalidoException;
import Exception.ExceptionsCarona.OrigemInvalidaException;
import Exception.ExceptionsCarona.SessaoInexistenteException;
import Exception.ExceptionsCarona.SessaoInvalidaException;

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
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, int vagas) throws SessaoInvalidaException, SessaoInexistenteException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException{
		
		if(checaIdSessao(idSessao)){
			throw new SessaoInvalidaException();
		}
		
		if(checaIdSessaoInexistente(idSessao)){ 
			throw new SessaoInexistenteException();
		}
	
		if(checaOrigem(origem)){
			throw new OrigemInvalidaException();
		}
		
		if(checaDestino(destino)){
			throw new DestinoInvalidoException();
		}
		
		if(checaData(data)){
			throw new DataInvalidaException();
		}
		
		String idCarona = ("carona" + (listaDeCaronas.size()+1) + "ID");
		Carona carona = new Carona(idSessao, origem, destino, data, hora, vagas, idCarona);
		listaDeCaronas.add(carona);
		return idCarona;
	}
	
	private boolean checaDestino(String destino) {
		return (destino == null || destino.equals(""));
	}

	private boolean checaOrigem(String origem) {
		return (origem == null || origem.equals(""));
	}

	public List<String> localizarCarona(String idSessao, String origem, String destino){
		return new LinkedList<String>();
	}
	
	private  boolean checaIdSessao(String idSessao){
		return  (idSessao == null || idSessao.equals(""));
	}
	
	private  boolean checaIdSessaoInexistente(String idSessao){
		return  (!idSessao.equals(usuario.getIdSessao()));
	} 
	
	private  boolean checaData(String stringData){
		if(stringData == null || stringData.equals("")){
			return true;
		}
		
		try {
			Calendar data = Calendar.getInstance();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.setLenient(false);
			data.setTime(formato.parse(stringData));
		} catch (ParseException e) {
			return true;
		}
			return false;
	} 
	
	private  boolean checaVaga(String idSessao){
		return  false;
	} 
}
