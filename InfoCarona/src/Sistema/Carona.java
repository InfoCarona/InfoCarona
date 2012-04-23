package Sistema;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import Exception.ExceptionsCarona.DataInvalidaException;
import Exception.ExceptionsCarona.DestinoInvalidoException;
import Exception.ExceptionsCarona.HoraInvalidaException;
import Exception.ExceptionsCarona.OrigemInvalidaException;
import Exception.ExceptionsCarona.SessaoInvalidaException;

public class Carona {
	
	private String idSessao, origem, destino, data, hora, idCarona, donoDaCarona;
	private int vagas;
	private List<SugestaoDePontoDeEncontro> listaDeSugestoes;
	
	public Carona(String idSessao, String origem, String destino, String data, String hora, int vagas, String idCarona, String donoDaCarona) throws SessaoInvalidaException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException {
        setIdSessao(idSessao);
        setOrigem(origem);
        setDestino(destino);
        setData(data);
        setHora(hora);
        setVagas(vagas);
        setDonoDaCarona(donoDaCarona);
        this.idCarona = idCarona;
        this.listaDeSugestoes = new LinkedList<SugestaoDePontoDeEncontro>();
	}
	
	public List<SugestaoDePontoDeEncontro> getListaDeSugestoes(){
		return this.listaDeSugestoes;
	}
	
	public String getOrigem() {
		return this.origem;
	}
	
	public String getDonoDaCarona(){
		return this.donoDaCarona;
	}
	
	 private void setDonoDaCarona(String donoDaCarona) {
         this.donoDaCarona = donoDaCarona.trim();
	 }
	

	public void setOrigem(String origem) throws OrigemInvalidaException {
        if((origem == null) || (origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")) ||(origem.trim().equals(""))){
        	throw new OrigemInvalidaException();
        }
        this.origem = origem.trim();
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) throws DestinoInvalidoException {
        if ((destino == null) || (destino.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")) ||(destino.trim().equals(""))) {
                throw new DestinoInvalidoException();
        }
        this.destino = destino.trim();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws DataInvalidaException {
        if ((data == null) || (data.trim().equals("")) || !(checaData(data))) {
                throw new DataInvalidaException();
        }
        this.data = data.trim();
}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) throws HoraInvalidaException {
        if ((hora == null) || hora.trim().equals("") || !checaHoraInvalida(hora)) {
                throw new HoraInvalidaException();
        }
        this.hora = hora.trim();
}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	
	public String getIdSessao(){
		return this.idSessao;
	}
	
	private void setIdSessao(String idSessao) throws SessaoInvalidaException {
        if ((idSessao == null) || (idSessao.trim().equals(""))) {
                throw new SessaoInvalidaException();
        }
        this.idSessao = idSessao.trim();
	}
	
	public String getIdCarona(){
		return this.idCarona;
	}
	
	public String getAtributo(String atributo){
		String retorno = null;
		
		if(atributo.equals("origem")){
			retorno = this.origem;
		}else if(atributo.equals("vagas")){
			retorno = (this.vagas+"");
		}else if(atributo.equals("destino")){
			retorno = this.destino;
		}else if(atributo.equals("data")){
			retorno = this.data;
		}
		
		return retorno;
	}
	
	public String toString(){
		return (this.origem + " para " + this.destino + ", no dia " + this.data + ", as " + this.hora);
	}
	
	public String getDadosCarona(){
		return ("origem=" + origem + " destino=" + destino + " data=" + data + " hora=" + hora + " vagas=" + vagas);
	}
	
	//Metodos de verificacao
    private boolean checaData(String stringData) {
            boolean retorno = true;
            Calendar data = Calendar.getInstance();
            try {
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    formato.setLenient(false);
                    data.setTime(formato.parse(stringData));
            } catch (ParseException e) {
                    retorno = false;
            }
            Calendar dataAtual = Calendar.getInstance();
            if (dataAtual.getTime().compareTo(data.getTime()) == 1) { // -1 data valida, 1 data invalida
                    retorno = false;
            }
            return retorno;
    }
    
    
    private boolean checaHoraInvalida(String hora) {
        boolean retorno = true;
        try {
                int horas = Integer.parseInt(hora.substring(0, 2));
                int minutos = Integer.parseInt(hora.substring(3, 5));
                if ((horas >= 24) || (minutos >= 60)) {
                        retorno = false;
                }
        } catch (Exception e) {
                retorno = false;
        }
        return retorno;
}
	
}
