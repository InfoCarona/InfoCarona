package Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import Exception.ExceptionUsuario.AtributoInexistenteException;
import Exception.ExceptionUsuario.AtributoInvalidoException;
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
import Exception.ExceptionsCarona.VagaInvalidaException;

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
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, int vagas) throws SessaoInvalidaException, SessaoInexistenteException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException, VagaInvalidaException{
		
		if(checaIdSessao(idSessao)){
			throw new SessaoInvalidaException();
		}
		
		if(checaIdSessaoInexistente(idSessao)){ 
			throw new SessaoInexistenteException();
		}
	
		if(checaOrigem(origem) || origem.equals("")){
			throw new OrigemInvalidaException();
		}
		
		if(checaDestino(destino) || destino.equals("")){
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
	
	public String toString(){
		return usuario.toString();
	}
	
	public String localizarCarona(String idSessao, String origem, String destino) throws OrigemInvalidaException, DestinoInvalidoException{
		if (checaOrigem(origem)){
			throw new OrigemInvalidaException();
		}
		
		if (checaDestino(destino)){
			throw new DestinoInvalidoException();
		}
		
		String retorno;
		if ((!origem.equals("")) && ((!destino.equals("")))){
			retorno = auxiliaLocalizaCarona(idSessao, origem, destino);
		}else if ((origem.equals("")) && ((!destino.equals("")))){
			retorno = auxiliaLocalizaCaronaDestino(idSessao, destino);
			
		}else if ((!origem.equals("")) && ((destino.equals("")))){
			retorno = auxiliaLocalizaCaronaOrigem(idSessao, origem);
		}else{
			retorno = auxiliaLocalizaCarona(idSessao);
		}
		return retorno;
	}
	
	
	private String auxiliaLocalizaCarona(String idSessao) {
		List<String> listaAux = new LinkedList<String>();
		for (Carona  carona : listaDeCaronas){
			listaAux.add(carona.getIdCarona());
		}
		return listaAux.toString().replace("[", "{").replace("]", "}");
	}
	

	private String auxiliaLocalizaCaronaOrigem(String idSessao, String origem) {
		List<String> listaAux = new LinkedList<String>();
		for (Carona  carona : listaDeCaronas){
			if (carona.getOrigem().equals(origem)){
				listaAux.add(carona.getIdCarona());
			}	
		}
		return listaAux.toString().replace("[", "{").replace("]", "}");
	}

	private String auxiliaLocalizaCaronaDestino(String idSessao, String destino) {
		List<String> listaAux = new LinkedList<String>();
		for (Carona  carona : listaDeCaronas){
			if (carona.getDestino().equals(destino)){
				listaAux.add(carona.getIdCarona());
			}	
		}
		return listaAux.toString().replace("[", "{").replace("]", "}");
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
	
	public String getAtributoCarona(String idCarona,String atributo) throws ItemInexistenteException, IDCaronaInexistenteException, AtributoInvalidoException, AtributoInexistenteException{
		
		if(checaIdCarona(idCarona) || idCarona.equals("")){
			throw new IDCaronaInexistenteException();
		}
		if(checaAtributo(atributo)){
			throw new AtributoInvalidoException();
		}
		boolean existeCarona = false;
		String retorno = "";
		for (Carona  carona : listaDeCaronas){
			if (carona.getIdCarona().equals(idCarona)){
				existeCarona = true;
				retorno =  carona.getAtributo(atributo);
				break;
			}
		}
		if(retorno == null ){
			throw new AtributoInexistenteException();
		}
		if(!existeCarona){
			throw new ItemInexistenteException();
		}
		return retorno;
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
	
	public Carona getCarona(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		if(checaIdCarona(idCarona)){
			throw new CaronaInvalidaException();
		}
		Carona retorno = null;
		for(Carona carona : listaDeCaronas){
			if(carona.getIdCarona().equals(idCarona)){
				retorno = carona;
			}
		}
		return retorno;
	}
	
	
	public boolean isCaronaDoPerfil(String idCarona){
		for (Carona carona : listaDeCaronas){
			if(carona.getIdCarona().equals(idCarona)){
				return true;
			}
		}
		return false;
	}
	
	public void sugerirPontoEncontro(String idSessao, String idCarona, String pontos) throws CaronaInexistenteException, CaronaInvalidaException {
		SolicitacaoDeVaga solicita = new SolicitacaoDeVaga(idSessao, idCarona);
		String[] locais = pontos.split(";");
		for (String local : locais) {
			solicita.adicionaPontoDeEncontro(local);
		}

	}
	
	public void responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos) throws CaronaInexistenteException, CaronaInvalidaException{
		SolicitacaoDeVaga solicita = null; //aki um metodo p achar a solicitaçao nas lista de solitaçao
		String[] locais = pontos.split(";");
		for (String local : locais) {
			solicita.adicionaRespostaDePontoDeEncontro(local);
		}
	}
	
	private boolean checaDestino(String destino) {
		String charInvalidos = "()!-?.";
		boolean retorno = false;
		
		if (destino == null){
			retorno = true;
		}else{
			for (int i = 0; i < charInvalidos.length(); i++) {
				if (destino.contains(charInvalidos.charAt(i)+"")){
					retorno = true;
				}
			}
		}
		return retorno;
	}

	private boolean checaOrigem(String origem) {
		String charInvalidos = "()!-?.";
		boolean retorno = false;
		
		if (origem == null){
			retorno = true;
		}else{
			for (int i = 0; i < charInvalidos.length(); i++) {
				if (origem.contains(charInvalidos.charAt(i)+"")){
					retorno = true;
				}
			}
		}
		return retorno;
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

	private boolean checaAtributo(String atributo) {
		return (atributo == null || atributo.equals(""));
	}
	
	public void solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto, Carona carona) throws CaronaInexistenteException, CaronaInvalidaException{
		SolicitacaoDeVaga novaSolicitacao = new SolicitacaoDeVaga(idSessao, idCarona);
		carona.addSolicitacaoDeVaga(novaSolicitacao);
	}
}
