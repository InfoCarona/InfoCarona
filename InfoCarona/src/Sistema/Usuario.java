package Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import Exception.ExceptionUsuario.AtributoInexistenteException;
import Exception.ExceptionUsuario.AtributoInvalidoException;
import Exception.ExceptionUsuario.EmailExistenteException;
import Exception.ExceptionUsuario.EmailInvalidoException;
import Exception.ExceptionUsuario.EnderecoInvalidoException;
import Exception.ExceptionUsuario.LoginExistenteException;
import Exception.ExceptionUsuario.LoginInvalidoException;
import Exception.ExceptionUsuario.NomeInvalidoException;
import Exception.ExceptionUsuario.SenhaInvalidoException;
import Exception.ExceptionUsuario.numeroMaximoException;
import Exception.ExceptionsCarona.CaronaInexistenteException;
import Exception.ExceptionsCarona.CaronaInvalidaException;
import Exception.ExceptionsCarona.DataInvalidaException;
import Exception.ExceptionsCarona.DestinoInvalidoException;
import Exception.ExceptionsCarona.HoraInvalidaException;
import Exception.ExceptionsCarona.IDCaronaInexistenteException;
import Exception.ExceptionsCarona.IDCaronaInvalidoException;
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

public class Usuario {

	private List<Carona> listaDeCaronas;
	private List<SolicitacaoDeVaga> listaDeSolicitacaoDeVagas;
	private int caronasSeguras;
	private int caronaNaoFuncionaram;
	private int faltasEmVagas;
	private int presencaEmVagas;
	private String nome, email, endereco, senha, id, login;

	public Usuario(String nome, String email, String endereco, String senha, String login) throws EmailInvalidoException, NomeInvalidoException, LoginInvalidoException, SenhaInvalidoException, EnderecoInvalidoException  {
		setNome(nome);
        setEmail(email);
        setEndereco(endereco);
        setSenha(senha);
        setLogin(login);
        this.id  = "sessao" + login.substring(0,1).toUpperCase() + login.substring(1, login.length());
        this.listaDeCaronas = new LinkedList<Carona>();
        this.listaDeSolicitacaoDeVagas = new LinkedList<SolicitacaoDeVaga>();
        this.caronasSeguras = 0;
        this.caronaNaoFuncionaram = 0;
        this.faltasEmVagas = 0;
        this.presencaEmVagas = 0;
		
	}


	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, int vagas, Id idCarona)
			throws SessaoInvalidaException, SessaoInexistenteException,
			OrigemInvalidaException, DestinoInvalidoException,
			DataInvalidaException, HoraInvalidaException, VagaInvalidaException, numeroMaximoException {

		if (checaIdSessaoInexistente(idSessao)) {
            throw new SessaoInexistenteException();
		}
    
		String id = idCarona.gerarId();
		Carona carona = new Carona(idSessao, origem, destino, data, hora,
				vagas, id, this.getNome());
		listaDeCaronas.add(carona);
		return id;
	}

	public String toString() {
		return "Nome: "+nome + " Login: " + login;
	}
	

	private boolean checaIdSessaoInexistente(String idSessao) {
		return (!idSessao.equals(this.getIdSessao()));
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

//	private boolean checaExisteSugestao(String ponto, Carona carona) {
//		boolean retorno = false;
//		for (SugestaoDePontoDeEncontro sugestao : carona.getListaDeSugestoes()) {
//			if (sugestao.getListaDeSugestaoDePontosDeEncontro().contains(ponto)) {
//				retorno = true;
//			}
//		}
//
//		return retorno;
//	}

	public String sugerirPontoEncontro(String idSessao,
			String pontos, Carona carona, Id idSugestao) throws CaronaInexistenteException,
			CaronaInvalidaException, PontoInvalidoException, numeroMaximoException {
		String id = idSugestao.gerarId();
		SugestaoDePontoDeEncontro sugestao = new SugestaoDePontoDeEncontro(
				idSessao, carona.getIdCarona(), id);

		if (checaPontos(pontos)) {
			throw new PontoInvalidoException();
		}

		String[] locais = pontos.split(";");// sugestao de locais(ponto) de
											// encontro

		for (String local : locais) {
			sugestao.getListaDeSugestaoDePontosDeEncontro().add(local);
		}

		carona.getListaDeSugestoes().add(sugestao);
		return id;
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
			Carona carona, Id IdSolicitacao) throws CaronaInexistenteException,
			CaronaInvalidaException, numeroMaximoException {
		String id = IdSolicitacao.gerarId();
		SolicitacaoDeVaga novaSolicitacao = new SolicitacaoDeVaga(carona,
				this.getNome(), ponto, id);
		listaDeSolicitacaoDeVagas.add(novaSolicitacao);
		return id;
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



	public SolicitacaoDeVaga procuraSolicitacao(String idSolicitacao) {
		SolicitacaoDeVaga retorno = null;

		for (SolicitacaoDeVaga solicitacao : listaDeSolicitacaoDeVagas) {
			if (solicitacao.getIdSolicitacao().equals(idSolicitacao)) {
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

	public String getAtributoUsuario(Usuario perfil, String atributo) {
		List<String> listaTemp = new LinkedList<String>();
		String retorno = "";
		if (atributo.equals("nome")) {
			retorno = this.getNome();
		} else if (atributo.equals("endereco")) {
			retorno = this.getEndereco();
		} else if (atributo.equals("email")) {
			retorno = this.getEmail();
		} else if (atributo.equals("historico de caronas")) {
			for (Carona caronaTemp : listaDeCaronas) {
				listaTemp.add(caronaTemp.getIdCarona());
				//retorno += caronaTemp;
			}
			retorno = listaTemp.toString().replace("{", "[").replace("}", "]");
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
		
		
		//aki comeï¿½a os metodos do usuario
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) throws EmailInvalidoException {
            if ((email == null || email.trim().equals(""))) {        
                    throw new EmailInvalidoException();
            }
            this.email = email.trim();
		}
		
		public String getEndereco() {
			return endereco;
		}
		
		public void setEndereco(String endereco) {
			this.endereco = endereco.trim();
		}
		public String getSenha() {
			return senha;
		}
		
		public void setSenha(String senha) throws SenhaInvalidoException {
            if ((senha == null || senha.trim().equals(""))) {
                    throw new SenhaInvalidoException();
            }
            this.senha = senha.trim();
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
		
		private void setLogin(String login) throws LoginInvalidoException {
            if ((login == null || login.trim().equals(""))) {
                    throw new LoginInvalidoException();
            }
            this.login = login.trim();
		}
		
		public void setNome(String nome) throws NomeInvalidoException {
            if (nome == null || nome.trim().equals("")) {
                    throw new NomeInvalidoException();
            }
            this.nome = nome.trim();
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
		
		public void rejeitarSolicitacao(SolicitacaoDeVaga solicitacao) throws SolicitacaoInexistenteException {
			if (solicitacao.isSolicitacaoRejeitada()) {
				throw new SolicitacaoInexistenteException();
			}
			solicitacao.solicitacaoRejeitada();
			
		}
		
		public List<Carona> getCaronas(){
			return listaDeCaronas;
		}
		//Retirar da classe
		
		public String localizarCarona(String idSessao, String origem, String destino)
				throws OrigemInvalidaException, DestinoInvalidoException {
			if((origem == null) || (origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))){
	        	throw new OrigemInvalidaException();
	        }
			if ((destino == null) || (destino.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))) {
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
				AtributoInvalidoException, AtributoInexistenteException, IDCaronaInvalidoException {

			if (checaIdCarona(idCarona) || idCarona.equals("")) {
				throw new IDCaronaInvalidoException();
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
		
		public String getAtributoSolicitacao(SolicitacaoDeVaga solicitacao,
				String atributo) {
			return solicitacao.getAtributoSolicitacao(atributo);

		}
}




