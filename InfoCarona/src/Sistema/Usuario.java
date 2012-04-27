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
	private String nome, email, endereco, senha, login;

	public Usuario(String nome, String email, String endereco, String senha,
			String login) throws EmailInvalidoException, NomeInvalidoException,
			LoginInvalidoException, SenhaInvalidoException,
			EnderecoInvalidoException {
		setNome(nome);
		setEmail(email);
		setEndereco(endereco);
		setSenha(senha);
		setLogin(login);
		this.listaDeCaronas = new LinkedList<Carona>();
		this.listaDeSolicitacaoDeVagas = new LinkedList<SolicitacaoDeVaga>();
		this.caronasSeguras = 0;
		this.caronaNaoFuncionaram = 0;
		this.faltasEmVagas = 0;
		this.presencaEmVagas = 0;

	}

	public String cadastrarCarona(String origem, String destino, String data,
			String hora, int vagas, String idCarona)
			throws SessaoInvalidaException, SessaoInexistenteException,
			OrigemInvalidaException, DestinoInvalidoException,
			DataInvalidaException, HoraInvalidaException,
			VagaInvalidaException, numeroMaximoException {

		Carona carona = new Carona(origem, destino, data, hora, vagas,
				idCarona, this.getNome());
		listaDeCaronas.add(carona);
		return idCarona;
	}

	public String toString() {
		return "Nome: " + nome + " Login: " + login;
	}

	public String sugerirPontoEncontro(String pontos, Carona carona,
			String idSugestao, Usuario usuarioQueSugeriu)
			throws CaronaInexistenteException, CaronaInvalidaException,
			PontoInvalidoException, numeroMaximoException {
		SugestaoDePontoDeEncontro sugestao = new SugestaoDePontoDeEncontro(
				idSugestao, usuarioQueSugeriu);

		String[] locais = pontos.split(";");// sugestao de locais(ponto) de
											// encontro
		for (String local : locais) {
			sugestao.getListaDeSugestaoDePontosDeEncontro().add(local);
		}

		carona.getListaDeSugestoes().add(sugestao);
		return idSugestao;
	}

	public List<SolicitacaoDeVaga> getListaDeSolicitacaoDeVagas() {
		return listaDeSolicitacaoDeVagas;
	}

	public void responderSugestaoPontoEncontro(SugestaoDePontoDeEncontro sugestao, String pontos) throws CaronaInexistenteException,
			CaronaInvalidaException, SugestaoInexistenteException {
		String[] locais = pontos.split(";");
		for (String local : locais) {
			sugestao.getlistaDeRespostasDePontosDeEncontro().add(local);
		}
	}

	public String solicitarVagaPontoEncontro(String ponto, Carona carona, String IdSolicitacao, Usuario donoSolcitacao) throws CaronaInexistenteException, CaronaInvalidaException, numeroMaximoException {
		SolicitacaoDeVaga novaSolicitacao = new SolicitacaoDeVaga(carona,  ponto, IdSolicitacao, donoSolcitacao);
		listaDeSolicitacaoDeVagas.add(novaSolicitacao);
		return IdSolicitacao;
	}

	public void aceitarSolicitacaoPontoEncontro(SolicitacaoDeVaga solicitacao)
			throws SolicitacaoInexistenteException, VagaInvalidaException {
		if (solicitacao.isSolicitacaoAceita()) {
			throw new SolicitacaoInexistenteException();
		}
		solicitacao.solicitacaoAceita();
	}

	public void desistirRequisicao(SolicitacaoDeVaga solicitacao ,Carona caronaTemp) {
		listaDeSolicitacaoDeVagas.remove(solicitacao);
		caronaTemp.removeSolicitacao(solicitacao);
	}

	public int getCaronasSeguras() {
		return this.caronasSeguras;
	}

	public void setCaronasSeguras() {
		this.caronasSeguras++;
	}

	public int getCaronasNaoFuncionaram() {
		return this.caronaNaoFuncionaram;
	}

	public void setCaronasNaoFuncionaram() {
		this.caronaNaoFuncionaram++;
	}

	public int getFaltasEmVagas() {
		return this.faltasEmVagas;
	}

	public void setFaltasEmVagas() {
		this.faltasEmVagas++;
	}

	public int getPresencaEmVagas() {
		return this.presencaEmVagas;
	}

	public void setPresencaEmVagas() {
		this.presencaEmVagas++;
	}

	// aki começa os metodos do usuario

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

	public void rejeitarSolicitacao(SolicitacaoDeVaga solicitacao)
			throws SolicitacaoInexistenteException {
		if (solicitacao.isSolicitacaoRejeitada()) {
			throw new SolicitacaoInexistenteException();
		}
		solicitacao.solicitacaoRejeitada();

	}

	public List<Carona> getCaronas() {
		return listaDeCaronas;
	}

	public String visualizarPerfil(Usuario usuarioProcurado) {
		return usuarioProcurado.toString();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Usuario)) {
			return false;
		}
		
		if (!(((Usuario) obj).getLogin().equals(this.login))){
			return false;
		}
		
		return true;
	}
}
