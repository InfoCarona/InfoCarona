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
import Exception.ExceptionUsuario.LoginExistenteException;
import Exception.ExceptionUsuario.LoginInvalidoException;
import Exception.ExceptionUsuario.NomeInvalidoException;
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
	LinkedList<Perfil> BD;
	String idSessao;
	Perfil perfil;
	private Iterator<Perfil> iterador, iterador2;

	public Sistema() {
		BD = new LinkedList<Perfil>();
		idSessao = "";
	}

	public void zerarSistema() {
		BD = new LinkedList<Perfil>();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		if (!(checaLogin(login))) {
			throw new LoginInvalidoException();
		}

		if (checaExisteLogin(login)) {
			throw new LoginExistenteException();
		}

		if (checaNome(nome)) {
			throw new NomeInvalidoException();
		}
		if (!(checaEmail(email))) {
			throw new EmailInvalidoException();
		}

		if (checaExisteEmail(email)) {
			throw new EmailExistenteException();
		}

		Perfil novoPerfil = new Perfil(login, senha, nome, endereco, email);
		BD.add(novoPerfil);

	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaInexistenteException,
			CaronaInvalidaException, PontoInvalidoException {
		Carona carona = getPerfilComCarona(idCarona).getCarona(idCarona);
		return perfil.sugerirPontoEncontro(idSessao, idCarona, pontos, carona);

	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaInexistenteException, CaronaInvalidaException,
			SugestaoInexistenteException {
		Carona carona = getPerfilComCarona(idCarona).getCarona(idCarona);
		perfil.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao,
				pontos, carona);
	}

	public String abrirSessao(String login, String senha)
			throws LoginInvalidoException, UsuarioInexistenteException,
			LoginExistenteException {
		boolean userExist = false;
		if (!(checaLogin(login))) {
			throw new LoginInvalidoException();
		}

		for (Perfil perfilTemp : BD) {
			Usuario usuarioTemp = perfilTemp.getUsuario();
			if (usuarioTemp.getLogin().equals(login)) {
				userExist = true;
				if (usuarioTemp.getSenha().equals(senha)) {
					idSessao = usuarioTemp.getIdSessao();
					perfil = perfilTemp;
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
		idSessao = "";
		perfil = null;
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
		for (Perfil perfilTemp : BD) {
			Usuario usuarioTemp = perfilTemp.getUsuario();
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

	private boolean checaExisteLogin(String login)
			throws LoginExistenteException {
		iterador = BD.iterator();
		while (iterador.hasNext()) {
			Perfil perfilTemp = iterador.next();
			Usuario userTemp = perfilTemp.getUsuario();

			if (userTemp.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}

	private boolean checaLogin(String login) throws LoginInvalidoException {
		return (!(login == null || login.equals("")));

	}

	private boolean checaNome(String nome) {
		return (nome == null || nome.equals(""));
	}

	private boolean checaExisteEmail(String email)
			throws EmailExistenteException {
		iterador2 = BD.iterator();
		while (iterador2.hasNext()) {
			Perfil perfilTemp = iterador2.next();
			Usuario userTemp = perfilTemp.getUsuario();

			if (userTemp.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	private boolean checaEmail(String email) throws EmailInvalidoException {

		return (!(email == null || email.equals("")));
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

		return perfil.cadastrarCarona(idSessao, origem, destino, data, hora,
				vaga);
	}

	public String localizarCarona(String idSessao, String origem, String destino)
			throws OrigemInvalidaException, DestinoInvalidoException {
		return perfil.localizarCarona(idSessao, origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws ItemInexistenteException, IDCaronaInexistenteException,
			AtributoInvalidoException, AtributoInexistenteException {
		return perfil.getAtributoCarona(idCarona, atributo);
	}

	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException, TrajetoInvalidoException {
		return perfil.getTrajeto(idCarona);
	}

	public Carona getCarona(String idCarona) throws CaronaInexistenteException,
			CaronaInvalidaException {

		return perfil.getCarona(idCarona);
	}

	public Perfil getPerfilComCarona(String idCarona)
			throws CaronaInexistenteException, CaronaInvalidaException {
		for (Perfil perfil : BD) {
			if (perfil.isCaronaDoPerfil(idCarona)) {
				return perfil;
			}
		}
		throw new CaronaInexistenteException();
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInexistenteException,
			CaronaInvalidaException {
		Carona carona = getPerfilComCarona(idCarona).getCarona(idCarona);
		return perfil.solicitarVagaPontoEncontro(idSessao, ponto, carona);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		for (Perfil perfil : BD) {
			SolicitacaoDeVaga solicitacao = perfil
					.procuraSolicitacao(idSolicitacao);
			if (solicitacao != null) {
				if (solicitacao.getIdSolicitacao().equals(idSolicitacao)) {
					return perfil.getAtributoSolicitacao(solicitacao, atributo);
				}
			}
		}
		return null;
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws SolicitacaoInexistenteException {
		SolicitacaoDeVaga solicitacao = this.procuraSolicitacao(idSolicitacao);
		perfil.aceitarSolicitacaoPontoEncontro(idSessao, solicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) throws CaronaInexistenteException,
			CaronaInvalidaException {
		Carona caronaTemp = getPerfilComCarona(idCarona).getCarona(idCarona);
		perfil.desistirRequisicao(idSessao, idSugestao, caronaTemp);

	}

	public String solicitarVaga(String idSessao2, String idCarona)
			throws CaronaInexistenteException, CaronaInvalidaException {
		Carona carona = getPerfilComCarona(idCarona).getCarona(idCarona);
		return perfil.solicitarVagaPontoEncontro(idSessao2, null, carona);
	}

	private SolicitacaoDeVaga procuraSolicitacao(String idSolicitacao) {
		SolicitacaoDeVaga retorno = null;

		for (Perfil perfil : BD) {
			SolicitacaoDeVaga solicitacao = perfil
					.procuraSolicitacao(idSolicitacao);
			if (solicitacao != null) {
				retorno = solicitacao;
			}
		}

		return retorno;
	}

	public String visualizarPerfil(String idSesao, String login) {
		return perfil.toString();
	}

	public Perfil getPerfilLogin(String login) {
		Perfil retorno = null;
		for (Perfil perfilTemp : BD) {
			if (perfilTemp.getUsuario().getLogin().equals(login)) {
				retorno = perfilTemp;
			}
		}
		return retorno;
	}

	public String getAtributoPerfil(String login, String atributo) {
		Perfil perfilTemp = getPerfilLogin(login);
		return perfil.getAtributoPerfil(perfilTemp, atributo);
	}

}
