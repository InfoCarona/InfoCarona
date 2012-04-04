package Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, int vagas) throws SessaoInvalidaException, SessaoInexistenteException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException{
		
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
		if(checaHoraInvalida(hora)){
			throw new HoraInvalidaException();
		}
		
		String idCarona = ("carona" + (listaDeCaronas.size()+1) + "ID");
		Carona carona = new Carona(idSessao, origem, destino, data, hora, vagas, idCarona);
		listaDeCaronas.add(carona);
		return idCarona;
	}
	public String localizarCarona(String idSessao, String origem, String destino){
		return auxiliaLocalizaCarona(idSessao, origem, destino);
	}
	
	private String auxiliaLocalizaCarona(String idSessao, String origem, String destino){
		List<String> listaAux = new LinkedList<String>();
		for (Carona  carona : listaDeCaronas){
			if ((carona.getOrigem().equals(origem)) && (carona.getDestino().equals(destino))){
				listaAux.add(carona.getIdCarona());
			}
		}
		return listaAux.toString().replace("[", "{").replace("]", "}");
	}
	
	public String getAtributoCarona(String idCarona,String atributo) throws ItemInexistenteException, IDCaronaInexistenteException{
		if(checaIdCarona(idCarona) || idCarona.equals("")){
			throw new IDCaronaInexistenteException();
		}
		for (Carona  carona : listaDeCaronas){
			if (carona.getIdCarona().equals(idCarona)){
				return carona.getAtributo(atributo);
			}
		}
		
		throw new ItemInexistenteException();
	}
	
	public String getTrajeto(String idCarona) throws TrajetoInexistenteException, TrajetoInvalidoException{
		if(checaIdCarona(idCarona)){
			throw new TrajetoInvalidoException();
		}
		String retorno = "";
		for(Carona carona : listaDeCaronas){
			if(carona.getIdCarona().equals(idCarona)){
				retorno = carona.getOrigem() + " - " + carona.getDestino();
			}
		}
		if(retorno.equals("")){
			throw new TrajetoInexistenteException();
		}
		return retorno;
	}
	
	public String getCarona(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		if(checaIdCarona(idCarona)){
			throw new CaronaInvalidaException();
		}
		String retorno = "";
		for(Carona carona : listaDeCaronas){
			if(carona.getIdCarona().equals(idCarona)){
				retorno = carona.toString();
			}
		}
		if(retorno.equals("")){
			throw new CaronaInvalidaException();
		}
		return retorno;
	}

	private boolean checaDestino(String destino) {
		return (destino == null || destino.equals(""));
	}

	private boolean checaOrigem(String origem) {
		return (origem == null || origem.equals(""));
	}
	
	private  boolean checaIdSessao(String idSessao){
		return  (idSessao == null || idSessao.equals(""));
	}
	
	private  boolean checaIdSessaoInexistente(String idSessao){
		return  (!idSessao.equals(usuario.getIdSessao()));
	} 
	
	private  boolean checaHoraInvalida(String hora){
		if(hora == null || hora.equals("")){
			return true;
		}
		try {
			Integer.parseInt(hora.substring(0,2));
			Integer.parseInt(hora.substring(3,5));
		} catch (Exception e) {
			return true;
		}
		return false;
	}
	private  boolean checaData(String stringData){
		if(stringData == null || stringData.equals("")){
			return true;
		}
		Calendar data = Calendar.getInstance();
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.setLenient(false);
			data.setTime(formato.parse(stringData));
		} catch (ParseException e) {
			return true;
		}
		
		Calendar dataAtual = Calendar.getInstance();
		if (dataAtual.getTime().compareTo(data.getTime()) == 1){ //-1 data válida/ 1 data inválida
			return true;
		}
			return false;
			
	} 
	
	private  boolean checaVaga(String idSessao){
		return  false;
	} 
	private boolean checaIdCarona(String idCarona) {
		return (idCarona == null);
	}

}
