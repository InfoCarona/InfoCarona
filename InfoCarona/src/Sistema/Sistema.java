package Sistema;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Exception.ExceptionUsuario.AtributoInexistenteException;
import Exception.ExceptionUsuario.AtributoInvalidoException;
import Exception.ExceptionUsuario.EmailExistenteException;
import Exception.ExceptionUsuario.EmailInvalidoException;
import Exception.ExceptionUsuario.EnderecoInvalidoException;
import Exception.ExceptionUsuario.LoginExistenteException;
import Exception.ExceptionUsuario.LoginInvalidoException;
import Exception.ExceptionUsuario.NomeInvalidoException;
import Exception.ExceptionUsuario.OpcaoInvalidaException;
import Exception.ExceptionUsuario.SenhaInvalidoException;
import Exception.ExceptionUsuario.UsuarioInexistenteException;
import Exception.ExceptionUsuario.UsuarioLoginExistenteException;
import Exception.ExceptionUsuario.UsuarioNaoPossuiVagaNaCaronaException;
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

public class Sistema {
	/**
	 * Classe Sistema utilizada incialmente para a logica do sistema todos os
	 * metodos da fachada, são chamadas por ele.
	 */

	private ControlerRepositorio controleRepositorio;
	private Id id;
	private Map<String, Usuario> usuariosLogados;

	public Sistema() {
		id = new Id(5);
		this.criaSistema();
	}

	private void criaSistema() {
		controleRepositorio = new ControlerRepositorio();
		usuariosLogados = new HashMap<String, Usuario>();
	}

	/**
	 * Metodo para Zerar as configurações do Usuario
	 */
	public void zerarSistema() {
		this.criaSistema();
		controleRepositorio.zerarSistema();
	}

	/**
	 * criarUsuario metodo para criar um novo objeto da Classe Usuario.
	 * 
	 * @param login
	 *            - login a ser cadastrado no usuario.
	 * @param senha
	 *            - senha de acesso do usuario.
	 * @param nome
	 *            - nome do usuario a ser cadastrado.
	 * @param endereco
	 *            - endereco do usuario.
	 * @param email
	 *            - email do usuario
	 * @throws EnderecoInvalidoException
	 * @throws SenhaInvalidoException
	 * @throws LoginInvalidoException
	 * @throws NomeInvalidoException
	 * @throws EmailInvalidoException
	 * @throws LoginExistenteException
	 * @throws EmailExistenteException
	 * 
	 * */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws EmailInvalidoException,
			NomeInvalidoException, LoginInvalidoException,
			SenhaInvalidoException, EnderecoInvalidoException,
			LoginExistenteException, EmailExistenteException {

		if (controleRepositorio.checaExisteLogin(login)) {
			throw new LoginExistenteException();
		}

		if (controleRepositorio.checaExisteEmail(email)) {
			throw new EmailExistenteException();
		}

		Usuario novoUsuario = new Usuario(nome, email, endereco, senha, login);
		controleRepositorio.addUsuario(novoUsuario);

	}

	public String abrirSessao(String login, String senha)
			throws LoginInvalidoException, UsuarioInexistenteException,
			LoginExistenteException, numeroMaximoException {

		String idSessao = id.gerarId();

		if (!(checaLogin(login))) {
			throw new LoginInvalidoException();
		}

		Usuario usuarioTemp = controleRepositorio.buscarUsuarioPorLogin(login);

		if (usuarioTemp.getSenha().equals(senha)) {
			usuariosLogados.put(idSessao, usuarioTemp);
		} else {
			throw new LoginInvalidoException();
		}

		return idSessao;
	}

	public void encerrarSessao(String login) {
		Collection<String> listaUsuariosLogados = usuariosLogados.keySet();
		Usuario removeUsuario = null;
		for (String idSessao : listaUsuariosLogados) {
			if (usuariosLogados.get(idSessao).getLogin().equals(login)) {
				removeUsuario = usuariosLogados.get(idSessao);
				break;
			}
		}
		listaUsuariosLogados.remove(removeUsuario);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoginInvalidoException, AtributoInvalidoException,
			UsuarioInexistenteException, AtributoInexistenteException,
			LoginExistenteException {

		String retorno = "";

		if (!checaLogin(login)) {
			throw new LoginInvalidoException();
		}

		if (checaAtributo(atributo)) {
			throw new AtributoInvalidoException();
		}
		if (!checaAtributoValido(atributo)) {
			throw new AtributoInexistenteException();
		}

		retorno = controleRepositorio.getAtributoUsuario(login, atributo);

		return retorno;
	}

	public List<Carona> localizarCarona(String origem, String destino)
			throws OrigemInvalidaException, DestinoInvalidoException,
			SessaoInvalidaException, SessaoInexistenteException {

		if ((origem == null)
				|| (origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))) {
			throw new OrigemInvalidaException();
		}
		if ((destino == null)
				|| (destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))) {
			throw new DestinoInvalidoException();
		}

		return controleRepositorio.localizarCarona(origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws ItemInexistenteException, IDCaronaInexistenteException,
			AtributoInvalidoException, AtributoInexistenteException,
			SessaoInvalidaException, SessaoInexistenteException,
			CaronaInexistenteException, CaronaInvalidaException,
			IDCaronaInvalidoException {

		return controleRepositorio.getAtributoCarona(idCarona, atributo);
	}

	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, int vagas)
			throws SessaoInvalidaException, SessaoInexistenteException,
			OrigemInvalidaException, DestinoInvalidoException,
			DataInvalidaException, HoraInvalidaException,
			VagaInvalidaException, numeroMaximoException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCarona(origem, destino, data,
				hora, vagas, id.gerarId());

		return idCarona;
	}

	public Carona getCarona(String idCarona) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException,
			SessaoInexistenteException {
		if (ehVazioOuNull(idCarona)) {
			throw new CaronaInvalidaException();
		}
		return controleRepositorio.localizaCaronaPorId(idCarona);
	}

	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException, TrajetoInvalidoException,
			SessaoInvalidaException, SessaoInexistenteException,
			CaronaInexistenteException, CaronaInvalidaException {

		return controleRepositorio.getTrajeto(idCarona);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaInexistenteException,
			CaronaInvalidaException, PontoInvalidoException,
			SessaoInvalidaException, SessaoInexistenteException,
			IDCaronaInvalidoException, ItemInexistenteException,
			numeroMaximoException {
		if (pontos == null || pontos.equals("")) {
			throw new PontoInvalidoException();
		}
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Carona caronaTemp = this.getCarona(idCarona);

		return usuarioTemp.sugerirPontoEncontro(pontos, caronaTemp,
				id.gerarId(), usuarioTemp);

	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaInexistenteException, CaronaInvalidaException,
			SugestaoInexistenteException, SessaoInvalidaException,
			SessaoInexistenteException, IDCaronaInvalidoException,
			ItemInexistenteException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		SugestaoDePontoDeEncontro sugestaoTemp = controleRepositorio
				.getSugestaoId(idSugestao, idCarona);

		usuarioTemp.responderSugestaoPontoEncontro(sugestaoTemp, pontos);
	}

	public void encerrarSistema() {
		usuariosLogados = new HashMap<String, Usuario>();
		controleRepositorio.encerrarSistema();		
	}

	// metodo pra ver se o atributo passado existe
	private boolean checaAtributoValido(String atributo) {
		return (atributo.equals("nome") || atributo.equals("endereco") || atributo
				.equals("email"));
	}

	// Metodos abaixo servem para checar se os atributos para criar são passados
	// como null ou vazio

	private boolean checaLogin(String login) throws LoginInvalidoException {
		return (!(login == null || login.equals("")));

	}

	private boolean checaAtributo(String atributo) {
		return (atributo == null || atributo.equals(""));
	}

	private boolean checaIdSessao(String idSessao) {
		return (!(idSessao == null || idSessao.equals("")));
	}

	private Usuario procuraUsuarioLogado(String idSessao)
			throws SessaoInvalidaException, SessaoInexistenteException {
		Usuario retorno = null;
		if (!checaIdSessao(idSessao)) {
			throw new SessaoInvalidaException();
		}

		retorno = usuariosLogados.get(idSessao);

		if (retorno == null) {
			throw new SessaoInexistenteException();
		}

		return retorno;
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInexistenteException,

	CaronaInvalidaException, SessaoInvalidaException,
			SessaoInexistenteException, IDCaronaInvalidoException,
			ItemInexistenteException, numeroMaximoException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Carona carona = controleRepositorio.localizaCaronaPorId(idCarona);
		if (ponto.equals("")) {
			ponto = null; // subtende-se que o usuario aceita os pontos que o
							// dono da carona indicou
		}

		return usuarioTemp.solicitarVagaPontoEncontro(ponto, carona,
				id.gerarId(), usuarioTemp);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return controleRepositorio.getAtributoSolicitacao(idSolicitacao,
				atributo);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws SolicitacaoInexistenteException,
			SessaoInvalidaException, SessaoInexistenteException,
			VagaInvalidaException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = controleRepositorio
				.localizaSolicitacaoPorId(idSolicitacao);

		usuarioTemp.aceitarSolicitacaoPontoEncontro(solicitacao);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws SolicitacaoInexistenteException, SessaoInvalidaException,
			SessaoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = controleRepositorio
				.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.rejeitarSolicitacao(solicitacao);

	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException,
			SessaoInexistenteException, IDCaronaInvalidoException,
			ItemInexistenteException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		SolicitacaoDeVaga solicitacaoTemp = controleRepositorio
				.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.desistirRequisicao(solicitacaoTemp, caronaTemp);

	}

	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review) throws SessaoInvalidaException, SessaoInexistenteException, CaronaInexistenteException, CaronaInvalidaException, LoginInvalidoException, UsuarioInexistenteException, OpcaoInvalidaException, UsuarioNaoPossuiVagaNaCaronaException {
		
		boolean achou = false;
		Usuario usuarioTemp2 = controleRepositorio.buscarUsuarioPorLogin(loginCaroneiro);
		
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		
			for (SolicitacaoDeVaga solicitacao : usuarioTemp2.getListaDeSolicitacaoDeVagas()) {
				if (solicitacao.getCarona().getIdCarona().equals(idCarona)) {
						if(solicitacao.isSolicitacaoAceita()){
							if (!(review.equals("não faltou") || review.equals("faltou"))) {
								throw new OpcaoInvalidaException();
							}
							usuarioTemp2.adicionaReview(caronaTemp, review);
							achou = true;
						}
				}
			}
			if(!achou){
				throw new UsuarioNaoPossuiVagaNaCaronaException();
			}
		

	}

	public String visualizarPerfil(String idSessao, String login)
			throws LoginInvalidoException, SessaoInvalidaException,
			SessaoInexistenteException, UsuarioInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Usuario usuarioProcurado = null;
		try {
			usuarioProcurado = controleRepositorio.buscarUsuarioPorLogin(login);
		} catch (Exception e) {
			throw new LoginInvalidoException();
		}

		return usuarioTemp.visualizarPerfil(usuarioProcurado);
	}

	public String getAtributoPerfil(String login, String atributo)
			throws SessaoInvalidaException, SessaoInexistenteException,
			LoginInvalidoException, UsuarioInexistenteException {
		
		if (!atributo.equals("historico de vagas em caronas")) {
			return controleRepositorio.getAtributoUsuario(login, atributo);
		}
		Usuario usuarioTemp = controleRepositorio.buscarUsuarioPorLogin(login);
			List<String> lista = new LinkedList<String>();
			for (Carona caronaTemp : usuarioTemp.getSolicitacaoAceitas()) {
				lista.add(caronaTemp.getIdCarona());				
			}
			
			 return lista.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
			
		}
	

	public boolean ehVazioOuNull(String atributo) {
		if (atributo == null || atributo.equals("")) {
			return true;
		}
		return false;
	}

	public void reiniciarSistema() {
		this.criaSistema();
	}

	public Carona getCaronaUsuario(String idSessao, int indexCarona)
			throws SessaoInvalidaException, SessaoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas().get(indexCarona - 1);
	}

	public List<Carona> getTodasCaronasUsuario(String idSessao)
			throws SessaoInvalidaException, SessaoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas();
	}

	public List<SolicitacaoDeVaga> getSolicitacoesConfirmadas(String idCarona)
			throws SessaoInvalidaException, SessaoInexistenteException,
			CaronaInexistenteException, CaronaInvalidaException {
		return controleRepositorio.localizaCaronaPorId(idCarona)
				.getSolicitacoesConfirmadas();
	}

	public List<SolicitacaoDeVaga> getSolicitacoesPendentes(String idCarona)
			throws CaronaInexistenteException, CaronaInvalidaException {
		return controleRepositorio.localizaCaronaPorId(idCarona)
				.getSolicitacoesPendentes();
	}

	public List<SugestaoDePontoDeEncontro> getPontosEncontro(String idCarona)
			throws CaronaInexistenteException, CaronaInvalidaException {
		return controleRepositorio.localizaCaronaPorId(idCarona)
				.getListaDeSugestoes();
	}
}
