package Sistema;

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

public class Fachada {

	private Sistema sistema;
	private String argInvalido= "invalido";

	public Fachada() {
		this.sistema = new Sistema();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	public void criarUsuario(String login, String nome,
			String endereco, String email) throws Exception {
		sistema.criarUsuario(login, argInvalido, nome, endereco, email);
	}
	
	public void criarUsuario(String login, String nome,
			String endereco) throws Exception {
		sistema.criarUsuario(login, argInvalido, nome, endereco, argInvalido);
	}
	
	
	public void zerarSistema() {
		sistema.zerarSistema();
	}
	
	public String abrirSessao(String login, String senha) throws LoginInvalidoException, UsuarioInexistenteException, LoginExistenteException{
		return sistema.abrirSessao(login, senha);
	}
	
	public String getAtributoUsuario(String login, String atributo) throws LoginInvalidoException, AtributoInvalidoException, UsuarioInexistenteException, AtributoInexistenteException, LoginExistenteException{
		return sistema.getAtributoUsuario(login, atributo);
	}
	
	public void encerrarSistema(){
		sistema.encerrarSistema();
	}
	
	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas) throws SessaoInvalidaException, SessaoInexistenteException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException, VagaInvalidaException{
		return sistema.cadastrarCarona(idSessao, origem, destino, data, hora, vagas);
	}
	
	public String localizarCarona(String idSessao, String origem, String destino) throws OrigemInvalidaException, DestinoInvalidoException{
		return sistema.localizarCarona(idSessao, origem, destino);
	}
	
	public String getAtributoCarona(String idCarona, String atributo) throws ItemInexistenteException, IDCaronaInexistenteException, AtributoInvalidoException, AtributoInexistenteException{
		return sistema.getAtributoCarona(idCarona, atributo);
	}
	
	public String getTrajeto(String idCarona) throws TrajetoInexistenteException, TrajetoInvalidoException{
		return sistema.getTrajeto(idCarona);
	}
	
	public Carona getCarona(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		return sistema.getCarona(idCarona);
	}
	
	public void encerrarSessao(String login){
		sistema.encerrarSessao(login);
	}
	
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaInexistenteException, CaronaInvalidaException, PontoInvalidoException{
		return sistema.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}
	
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws CaronaInexistenteException, CaronaInvalidaException, SugestaoInexistenteException{
		sistema.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInexistenteException, CaronaInvalidaException{
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}
	
	public String SolicitarVaga(String idSessao, String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		return sistema.solicitarVaga(idSessao,idCarona);
	}
	public String getAtributoSolicitacao(String idSolicitacao, String atributo){
		return sistema.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao) throws SolicitacaoInexistenteException{
		sistema.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao) throws CaronaInexistenteException, CaronaInvalidaException{
		sistema.desistirRequisicao(idSessao, idCarona, idSugestao);
		
	}
}
