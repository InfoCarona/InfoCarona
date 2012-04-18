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
import Exception.ExceptionsCarona.PontoInvalidoException;
import Exception.ExceptionsCarona.SessaoInexistenteException;
import Exception.ExceptionsCarona.SessaoInvalidaException;
import Exception.ExceptionsCarona.SolicitacaoInexistenteException;
import Exception.ExceptionsCarona.SugestaoInexistenteException;
import Exception.ExceptionsCarona.TrajetoInexistenteException;
import Exception.ExceptionsCarona.TrajetoInvalidoException;
import Exception.ExceptionsCarona.VagaInvalidaException;

public class Perfil {

	private List<Carona> listaDeCaronas;
	private List<SolicitacaoDeVaga> listaDeSolicitacaoDeVagas;
	private int caronasSeguras;
	private int caronaNaoFuncionaram;
	private int faltasEmVagas;
	private int presencaEmVagas;
	private String nome, email, endereco, senha, id, login;

	public Perfil(String nome, String email, String endereco, String senha, String login) throws Exception {
		this.listaDeCaronas = new LinkedList<Carona>();
		this.listaDeSolicitacaoDeVagas = new LinkedList<SolicitacaoDeVaga>();
		this.caronasSeguras = 0;
		this.caronaNaoFuncionaram = 0;
		this.faltasEmVagas = 0;
		this.presencaEmVagas = 0;
		
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.senha = senha;
		this.login = login;
		this.id  = "sessao" + login.substring(0,1).toUpperCase() + login.substring(1, login.length());
	}


	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, int vagas)
			throws SessaoInvalidaException, SessaoInexistenteException,
			OrigemInvalidaException, DestinoInvalidoException,
			DataInvalidaException, HoraInvalidaException, VagaInvalidaException {

		if (!(checaIdSessao(idSessao))) {
			throw new SessaoInvalidaException();
		}

		if (checaIdSessaoInexistente(idSessao)) {
			throw new SessaoInexistenteException();
		}

		if ((!checaOrigem(origem)) || origem.equals("")) {
			throw new OrigemInvalidaException();
		}

		if ((!checaDestino(destino)) || destino.equals("")) {
			throw new DestinoInvalidoException();
		}

		if (!(checaData(data))) {
			throw new DataInvalidaException();
		}
		if (!(checaHoraInvalida(hora))) {
			throw new HoraInvalidaException();
		}

		String idCarona = ("carona" + (listaDeCaronas.size() + 1) + "ID");
		Carona carona = new Carona(idSessao, origem, destino, data, hora,
				vagas, idCarona, this.getNome());
		listaDeCaronas.add(carona);
		return idCarona;
	}

	public String toString() {
		return this.toString();
	}

	public String localizarCarona(String idSessao, String origem, String destino)
			throws OrigemInvalidaException, DestinoInvalidoException {
		if (!(checaOrigem(origem))) {
			throw new OrigemInvalidaException();
		}

		if (!(checaDestino(destino))) {
			throw new DestinoInvalidoException();
		}

		String retorno;
		if ((!origem.equals("")) && ((!destino.equals("")))) {
			retorno = auxiliaLocalizaCarona(idSessao, origem, destino);
		} else if ((origem.equals("")) && ((!destino.equals("")))) {
			retorno = auxiliaLocalizaCaronaDestino(idSessao, destino);

		} else if ((!origem.equals("")) && ((destino.equals("")))) {
			retorno = auxiliaLocalizaCaronaOrigem(idSessao, origem);
		} else {
			retorno = auxiliaLocalizaCarona(idSessao);
		}
		return retorno;
	}

	private String auxiliaLocalizaCarona(String idSessao) {
		List<String> listaAux = new LinkedList<String>();
		for (Carona carona : listaDeCaronas) {
			listaAux.add(carona.getIdCarona());
		}
		return listaAux.toString().replace("[", "{").replace("]", "}");
	}

	private String auxiliaLocalizaCaronaOrigem(String idSessao, String origem) {
		List<String> listaAux = new LinkedList<String>();
		for (Carona carona : listaDeCaronas) {
			if (carona.getOrigem().equals(origem)) {
				System.out.println(carona.toString());
				listaAux.add(carona.getIdCarona());
			}
		}
		return listaAux.toString().replace("[", "{").replace("]", "}");
	}

	private String auxiliaLocalizaCaronaDestino(String idSessao, String destino) {
		List<String> listaAux = new LinkedList<String>();
		for (Carona carona : listaDeCaronas) {
			if (carona.getDestino().equals(destino)) {
				listaAux.add(carona.getIdCarona());
			}
		}
		return listaAux.toString().replace("[", "{").replace("]", "}");
	}

	private String auxiliaLocalizaCarona(String idSessao, String origem,
			String destino) {
		List<String> listaAux = new LinkedList<String>();
		for (Carona carona : listaDeCaronas) {
			if ((carona.getOrigem().equals(origem))
					&& (carona.getDestino().equals(destino))) {
				listaAux.add(carona.getIdCarona());
			}
		}

		return listaAux.toString().replace("[", "{").replace("]", "}");
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws ItemInexistenteException, IDCaronaInexistenteException,
			AtributoInvalidoException, AtributoInexistenteException {

		if (checaIdCarona(idCarona) || idCarona.equals("")) {
			throw new IDCaronaInexistenteException();
		}
		if (checaAtributo(atributo)) {
			throw new AtributoInvalidoException();
		}
		boolean existeCarona = false;
		String retorno = "";
		for (Carona carona : listaDeCaronas) {
			if (carona.getIdCarona().equals(idCarona)) {
				existeCarona = true;
				retorno = carona.getAtributo(atributo);
				break;
			}
		}
		if (retorno == null) {
			throw new AtributoInexistenteException();
		}
		if (!existeCarona) {
			throw new ItemInexistenteException();
		}
		return retorno;
	}

	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException, TrajetoInvalidoException {
		if (checaIdCarona(idCarona)) {
			throw new TrajetoInvalidoException();
		}
		String retorno = "";
		for (Carona carona : listaDeCaronas) {
			if (carona.getIdCarona().equals(idCarona)) {
				retorno = carona.getOrigem() + " - " + carona.getDestino();
			}
		}
		if (retorno.equals("")) {
			throw new TrajetoInexistenteException();
		}
		return retorno;
	}

	public Carona getCarona(String idCarona) throws CaronaInexistenteException,
			CaronaInvalidaException {
		if (checaIdCarona(idCarona)) {
			throw new CaronaInvalidaException();
		}
		for (Carona carona : listaDeCaronas) {
			if (carona.getIdCarona().equals(idCarona)) {
				return carona;
			}
		}
		throw new CaronaInexistenteException();
	}

	public boolean isCaronaDoPerfil(String idCarona) {
		for (Carona carona : listaDeCaronas) {
			if (carona.getIdCarona().equals(idCarona)) {
				return true;
			}
		}
		return false;
	}

	private boolean checaDestino(String destino) {
		String charInvalidos = "()!-?.";
		boolean retorno = true;

		if (destino == null) {
			retorno = false;
		} else {
			for (int i = 0; i < charInvalidos.length(); i++) {
				if (destino.contains(charInvalidos.charAt(i) + "")) {
					retorno = false;
				}
			}
		}
		return retorno;
	}

	private boolean checaOrigem(String origem) {
		String charInvalidos = "()!-?.";
		boolean retorno = true;

		if (origem == null) {
			retorno = false;
		} else {
			for (int i = 0; i < charInvalidos.length(); i++) {
				if (origem.contains(charInvalidos.charAt(i) + "")) {
					retorno = false;
				}
			}
		}
		return retorno;
	}

	private boolean checaIdSessao(String idSessao) {
		return (!(idSessao == null || idSessao.equals("")));
	}

	private boolean checaIdSessaoInexistente(String idSessao) {
		return (!idSessao.equals(this.getIdSessao()));
	}

	private boolean checaHoraInvalida(String hora) {
		boolean retorno = true;

		if (hora == null || hora.equals("")) {
			retorno = false;
		}
		try {
			Integer.parseInt(hora.substring(0, 2));
			Integer.parseInt(hora.substring(3, 5));
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}

	private boolean checaData(String stringData) {
		boolean retorno = true;
		if (stringData == null || stringData.equals("")) {
			retorno = false;
		} else {
			Calendar data = Calendar.getInstance();
			try {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				formato.setLenient(false);
				data.setTime(formato.parse(stringData));
			} catch (ParseException e) {
				retorno = false;
			}

			Calendar dataAtual = Calendar.getInstance();
			if (dataAtual.getTime().compareTo(data.getTime()) == 1) { // -1 data
																		// válida/
																		// 1
																		// data
																		// inválida
				retorno = false;
			}
		}
		return retorno;
	}

	private boolean checaVaga(String idSessao) {
		return false;
	}

	private boolean checaIdCarona(String idCarona) {
		return (idCarona == null);
	}

	private boolean checaAtributo(String atributo) {
		return (atributo == null || atributo.equals(""));
	}

	private boolean checaPontos(String pontos) {
		boolean retorno = false;
		if (pontos.equals("") || pontos == null) {
			retorno = true;
		}
		return retorno;
	}

	private boolean checaExisteSugestao(String ponto, Carona carona) {
		boolean retorno = false;
		for (SugestaoDePontoDeEncontro sugestao : carona.getListaDeSugestoes()) {
			if (sugestao.getListaDeSugestaoDePontosDeEncontro().contains(ponto)) {
				retorno = true;
			}
		}

		return retorno;
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos, Carona carona) throws CaronaInexistenteException,
			CaronaInvalidaException, PontoInvalidoException {
		String idSugestao = ("sugestao"
				+ (carona.getListaDeSugestoes().size() + 1) + "ID");
		SugestaoDePontoDeEncontro sugestao = new SugestaoDePontoDeEncontro(
				idSessao, idCarona, idSugestao);

		if (checaPontos(pontos)) {
			throw new PontoInvalidoException();
		}

		String[] locais = pontos.split(";");// sugestao de locais(ponto) de
											// encontro

		for (String local : locais) {
			sugestao.getListaDeSugestaoDePontosDeEncontro().add(local);
		}

		carona.getListaDeSugestoes().add(sugestao);
		return idSugestao;
	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos, Carona carona)
			throws CaronaInexistenteException, CaronaInvalidaException,
			SugestaoInexistenteException {
		SugestaoDePontoDeEncontro sugestao = procuraSugestao(idSugestao, carona);
		String[] locais = pontos.split(";");
		for (String local : locais) {
			sugestao.getlistaDeRespostasDePontosDeEncontro().add(local);
		}
	}

	public String solicitarVagaPontoEncontro(String idSessao, String ponto,
			Carona carona) throws CaronaInexistenteException,
			CaronaInvalidaException {
		String idSolicitacao = ("solicitacao"
				+ (listaDeSolicitacaoDeVagas.size() + 1) + "ID");
		SolicitacaoDeVaga novaSolicitacao = new SolicitacaoDeVaga(carona,
				this.getNome(), ponto, idSolicitacao);
		listaDeSolicitacaoDeVagas.add(novaSolicitacao);
		return idSolicitacao;
	}

	private SugestaoDePontoDeEncontro procuraSugestao(String idSugestao,
			Carona carona) throws SugestaoInexistenteException { // metodo
																	// depois
																	// criar
																	// excecao,
																	// caso nao
																	// exista
		for (SugestaoDePontoDeEncontro sugestao : carona.getListaDeSugestoes()) {
			if (sugestao.getIdSugestao().equals(idSugestao)) {
				return sugestao;
			}
		}
		throw new SugestaoInexistenteException();
	}

	public String getAtributoSolicitacao(SolicitacaoDeVaga solicitacao,
			String atributo) {
		return solicitacao.getAtributoSolicitacao(atributo);

	}

	public SolicitacaoDeVaga procuraSolicitacao(String idSolicitacao) {
		SolicitacaoDeVaga retorno = null;

		for (SolicitacaoDeVaga solicitacao : listaDeSolicitacaoDeVagas) {
			if (solicitacao.getIdSolicitacao().equalsIgnoreCase(idSolicitacao)) {
				retorno = solicitacao;
			}
		}

		return retorno;
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			SolicitacaoDeVaga solicitacao)
			throws SolicitacaoInexistenteException {
		if (solicitacao.isSolicitacaoAceita()) {
			throw new SolicitacaoInexistenteException();
		}
		solicitacao.solicitacaoAceita();
	}

	public void desistirRequisicao(String idSessao, String idSugestao,
			Carona caronaTemp) {
		for (SugestaoDePontoDeEncontro sugestao : caronaTemp
				.getListaDeSugestoes()) {
			if (sugestao.getIdSugestao().equals(idSugestao)) {
				caronaTemp.getListaDeSugestoes().remove(sugestao);
			}
		}

	}

	public String getAtributoPerfil(Perfil perfil, String atributo) {
		String retorno = "";
		if (atributo.equals("nome")) {
			retorno = this.getNome();
		} else if (atributo.equals("endereco")) {
			retorno = this.getEndereco();
		} else if (atributo.equals("email")) {
			retorno = this.getEmail();
		} else if (atributo.equals("historico de caronas")) {
			for (Carona caronaTemp : listaDeCaronas) {
				retorno += caronaTemp.getDadosCarona();
			}
		}else if (atributo.equals("historico de vagas em caronas")) {
			for (SolicitacaoDeVaga solicitacaoTemp : listaDeSolicitacaoDeVagas) {
				retorno += solicitacaoTemp.toString();
			}
			
		}else if (atributo.equals("caronas seguras e tranquilas")){
			retorno = this.caronasSeguras + "";
		}else if (atributo.equals("caronas que não funcionaram")){
			retorno = this.caronaNaoFuncionaram + "";
		}else if (atributo.equals("faltas em vagas de caronas")){
			retorno = this.faltasEmVagas + "";
		}else if (atributo.equals("presenças em vagas de caronas")){
			retorno = this.presencaEmVagas + "";
		}
		
		
		return retorno;
	}
	
		public int getCaronasSeguras(){
			return this.caronasSeguras;
		}
		
		public void setCaronasSeguras(){
			this.caronasSeguras++;
		}
		
		public int getCaronasNaoFuncionaram(){
			return this.caronaNaoFuncionaram;
		}
		
		public void setCaronasNaoFuncionaram(){
			this.caronaNaoFuncionaram++;
		}
		
		public int getFaltasEmVagas(){
			return this.faltasEmVagas;
		}
		
		public void setFaltasEmVagas(){
			this.faltasEmVagas++;
		}
		
		public int getPresencaEmVagas(){
			return this.presencaEmVagas;
		}
		
		public void setPresencaEmVagas(){
			this.presencaEmVagas++;
		}
		
		
		//aki começa os metodos do usuario
		
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
}
