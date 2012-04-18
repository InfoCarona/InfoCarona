package Sistema;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
import Exception.ExceptionUsuario.UsuarioInexistenteException;
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

public class Sistema {
/**
 * Classe Sistema utilizada incialmente para a logica do sistema
 * todos os metodos da fachada, são chamadas por ele. 
 */
	
	List<Usuario> BD;
	List<Usuario> PerfisLogados;
	String idSessao;
	Usuario usuario;
	private Iterator<Usuario> iterador, iterador2;

	public Sistema() {
		BD = new LinkedList<Usuario>();
		PerfisLogados = new LinkedList<Usuario>();
		idSessao = "";
	}
/**
 * Metodo para Zerar as configurações do Usuario
 */
	public void zerarSistema() {
		BD = new LinkedList<Usuario>();
	}
/**
 * criarUsuario metodo para criar um novo objeto da Classe 
 * Usuario. 
 * @param login - login a ser cadastrado no usuario.
 * @param senha - senha de acesso do usuario.
 * @param nome - nome do usuario a ser cadastrado.
 * @param endereco - endereco do usuario.
 * @param email - email do usuario
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
			String endereco, String email) throws EmailInvalidoException, NomeInvalidoException, LoginInvalidoException, SenhaInvalidoException, EnderecoInvalidoException, LoginExistenteException, EmailExistenteException {


		if (checaExisteLogin(login)) {
			throw new LoginExistenteException();
		}

		if (checaExisteEmail(email)) {
			throw new EmailExistenteException();
		}

		Usuario novoUsuario = new Usuario(nome, email, endereco, senha, login);
		BD.add(novoUsuario);

	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaInexistenteException,
			CaronaInvalidaException, PontoInvalidoException, SessaoInvalidaException, SessaoInexistenteException {
		Carona carona = getUsuarioComCarona(idCarona).getCarona(idCarona);
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.sugerirPontoEncontro(idSessao, idCarona, pontos, carona);

	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaInexistenteException, CaronaInvalidaException,
			SugestaoInexistenteException, SessaoInvalidaException, SessaoInexistenteException {
		Carona carona = getUsuarioComCarona(idCarona).getCarona(idCarona);
		usuario = procuraUsuarioLogado(idSessao);
		usuario.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao,
				pontos, carona);
	}

	public String abrirSessao(String login, String senha)
			throws LoginInvalidoException, UsuarioInexistenteException,
			LoginExistenteException {
		boolean userExist = false;
		if (!(checaLogin(login))) {
			throw new LoginInvalidoException();
		}

		for (Usuario usuarioTemp : BD) {
			
			if (usuarioTemp.getLogin().equals(login)) {
				userExist = true;
				if (usuarioTemp.getSenha().equals(senha)) {
					idSessao = usuarioTemp.getIdSessao();
					//perfil = perfilTemp;
					PerfisLogados.add(usuarioTemp);
				} else {
					throw new LoginInvalidoException();
				}
			}
		}
		if (!userExist) {
			throw new UsuarioInexistenteException();
		}
		return idSessao;
	}

	public void encerrarSessao(String login) {
		Usuario usuarioTemp = getUsuarioComLogin(login);
		PerfisLogados.remove(usuarioTemp);
		
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoginInvalidoException, AtributoInvalidoException,
			UsuarioInexistenteException, AtributoInexistenteException,
			LoginExistenteException {

		if (!checaLogin(login)) {
			throw new LoginInvalidoException();
		}
		
		if (checaAtributo(atributo)) {
			throw new AtributoInvalidoException();
		}
		if (!checaAtributoValido(atributo)) {
			throw new AtributoInexistenteException();
		}
		String retorno = "";
		boolean userExist = false;
		for (Usuario usuarioTemp : BD) {
			if (usuarioTemp.getLogin().equals(login)) {
				userExist = true;
				retorno = usuarioTemp.getAtributoUsuario(login, atributo);
			}
		}
		if (!userExist) {
			throw new UsuarioInexistenteException();
		}
		return retorno;
	}

	public void encerrarSistema() {

	}

	// metodo pra ver se o atributo passado existe
	private boolean checaAtributoValido(String atributo) {
		return (atributo.equals("nome") || atributo.equals("endereco") || atributo
				.equals("email"));
	}

	// Metodos abaixo servem para checar se os atributos para criar são passados
	// como null ou vazio

	private boolean checaExisteLogin(String login){
		iterador = BD.iterator();
		while (iterador.hasNext()) {
			Usuario usuarioTemp = iterador.next();

			if (usuarioTemp.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checaLogin(String login) throws LoginInvalidoException {
		return (!(login == null || login.equals("")));

	}

	
	private boolean checaExisteEmail(String email)
			throws EmailExistenteException {
		iterador2 = BD.iterator();
		while (iterador2.hasNext()) {
			Usuario usuarioTemp = iterador2.next();

			if (usuarioTemp.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checaAtributo(String atributo) {
		return (atributo == null || atributo.equals(""));
	}

	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws SessaoInvalidaException, SessaoInexistenteException,
			OrigemInvalidaException, DestinoInvalidoException,
			DataInvalidaException, HoraInvalidaException, VagaInvalidaException {

		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new VagaInvalidaException();
		}
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.cadastrarCarona(idSessao, origem, destino, data, hora,
				vaga);
	}

	public String localizarCarona(String idSessao, String origem, String destino)
			throws OrigemInvalidaException, DestinoInvalidoException, SessaoInvalidaException, SessaoInexistenteException {
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.localizarCarona(idSessao, origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws ItemInexistenteException, IDCaronaInexistenteException,
			AtributoInvalidoException, AtributoInexistenteException, SessaoInvalidaException, SessaoInexistenteException, CaronaInexistenteException, CaronaInvalidaException {
		usuario = getUsuarioComCarona(idCarona);
		return usuario.getAtributoCarona(idCarona, atributo);
	}

	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException, TrajetoInvalidoException, SessaoInvalidaException, SessaoInexistenteException {
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.getTrajeto(idCarona);
	}

	public Carona getCarona(String idCarona) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException {
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.getCarona(idCarona);
	}

	public Usuario getUsuarioComCarona(String idCarona)
			throws CaronaInexistenteException, CaronaInvalidaException {
		for (Usuario usuarioTemp : BD) {
			if (usuarioTemp.isCaronaDoPerfil(idCarona)) {
				return usuarioTemp;
			}
		}
		throw new CaronaInexistenteException();
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException {
		Carona carona = getUsuarioComCarona(idCarona).getCarona(idCarona);
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.solicitarVagaPontoEncontro(idSessao, ponto, carona);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		for (Usuario perfilTemp : BD) {
			SolicitacaoDeVaga solicitacao = perfilTemp.procuraSolicitacao(idSolicitacao);
			if (solicitacao != null) {
				if (solicitacao.getIdSolicitacao().equals(idSolicitacao)) {
					return perfilTemp.getAtributoSolicitacao(solicitacao, atributo);
				}
			}
		}
		return null;
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws SolicitacaoInexistenteException, SessaoInvalidaException, SessaoInexistenteException {
		SolicitacaoDeVaga solicitacao = this.procuraSolicitacao(idSolicitacao);
		
		usuario = procuraUsuarioLogado(idSessao);
		usuario.aceitarSolicitacaoPontoEncontro(idSessao, solicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException {
		Carona caronaTemp = getUsuarioComCarona(idCarona).getCarona(idCarona);
		usuario = procuraUsuarioLogado(idSessao);
		usuario.desistirRequisicao(idSessao, idSugestao, caronaTemp);

	}

	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaInexistenteException, CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException {
		Carona carona = getUsuarioComCarona(idCarona).getCarona(idCarona);
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.solicitarVagaPontoEncontro(idSessao, null, carona);
	}

	private SolicitacaoDeVaga procuraSolicitacao(String idSolicitacao) throws SolicitacaoInexistenteException {
		SolicitacaoDeVaga retorno = null;

		for (Usuario usuarioTemp : BD) {
			SolicitacaoDeVaga solicitacao = usuarioTemp
					.procuraSolicitacao(idSolicitacao);
			if (solicitacao != null) {
				retorno = solicitacao;
			}
		}
		if(retorno == null){
			throw new SolicitacaoInexistenteException();
		}

		return retorno;
	}

	public String visualizarPerfil(String idSesao, String login) throws LoginInvalidoException, SessaoInvalidaException, SessaoInexistenteException{
		if(!checaExisteLogin(login)){
			throw new LoginInvalidoException();
		}
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.toString();
	}

	public Usuario getUsuarioComLogin(String login) {
		Usuario retorno = null;
		for (Usuario usuarioTemp : BD) {
			if (usuarioTemp.getLogin().equals(login)) {
				retorno = usuarioTemp;
			}
		}
		return retorno;
	}

	public String getAtributoPerfil(String login, String atributo) throws SessaoInvalidaException, SessaoInexistenteException {
		Usuario usuarioTemp = getUsuarioComLogin(login);
		usuario = procuraUsuarioLogado(idSessao);
		return usuario.getAtributoUsuario(usuarioTemp, atributo);
	}
	
	private Usuario procuraUsuarioLogado(String idSessao) throws SessaoInvalidaException, SessaoInexistenteException{
		Usuario retorno = null;
		if(!checaIdSessao(idSessao)){
			throw new SessaoInvalidaException();
		}
		
		for(Usuario usuarioTemp : PerfisLogados){
			if(usuarioTemp.getIdSessao().equals(idSessao)){
				retorno = usuarioTemp;
			}
		}
		if(retorno == null){
			throw new SessaoInexistenteException();
		}
		return retorno;
	}
	
	private boolean checaIdSessao(String idSessao) {
		return (!(idSessao == null || idSessao.equals("")));
	}


}
