package Sistema;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ManipulaArquivoXML {

	private List lista = new LinkedList();
	private String nomeDoArquivo;

	/**
	 * 
	 * Construtor de Grava, Grava eh uma classe responsalve por ler, gravar,
	 * remover e atualizar arquivos XML
	 * 
	 * @param nomeArquivo
	 *            Eh um String com o nome que vc deseja salvar o seu arquivo
	 *            xml.
	 * 
	 * */
	public ManipulaArquivoXML(String nomeArquivo) {
		this.setNomeArquivo(nomeArquivo);
	}
	
	private void setNomeArquivo(String nome){
		this.nomeDoArquivo = nome;
	}

	/**
	 * Retorna uma List com o conteudo do XML, se o xml nao existe ele sera
	 * criado em branco
	 * 
	 * */
	public List ler() {
		FileReader reader = null;
		try {
			reader = new FileReader(this.nomeDoArquivo);
		} catch (FileNotFoundException e) {

			XStream xstream = new XStream(new DomDriver());

			String myXML = xstream.toXML(lista);

			BufferedWriter writer = null;

			try {
				writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}

			try {
				writer.write(myXML);
				writer.close();
				System.out
						.println("Arquivo nao existia.\n...\nArquivo foi criado.");

			} catch (IOException f) {

				f.printStackTrace();
			}
		}
		XStream xstream = new XStream(new DomDriver());
		try {
			List newList = ((List) xstream.fromXML(reader));

			return newList;
		} catch (Exception e) {
			System.out.println("Nao existia dados no arquivo.");
		}

		return lista;
	}

	/**
	 * 
	 * 
	 * Adiciona Objetos no arquivo, se nao existir o arquivo, ele sera criado
	 * para que o objeto seja adicionado
	 * 
	 * @param obj
	 *            Eh um Object para ser adicionado ao arquivo xml
	 * 
	 * */
	public void addNoXML(Object obj) {
		
		//verifica se o arquivo existe.
		FileReader reader = null;
		try {
			reader = new FileReader(this.nomeDoArquivo);
		} catch (FileNotFoundException e) {
			XStream xstream = new XStream(new DomDriver());
			lista.add(obj);

			String myXML = xstream.toXML(lista);
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));

			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			try {
				writer.write(myXML);
				writer.close();
				System.out.println("Arquivo nao existia.\n...\nArquivo foi criado.");

			} catch (IOException f) {
				f.printStackTrace();
			}
		}

		XStream xstream = new XStream(new DomDriver());
		try {
			List pw = ((List) xstream.fromXML(reader));
			pw.add(obj);

			String myXML = xstream.toXML(pw);
			BufferedWriter writer = null;

			try {
				writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));

			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}

			try {
				writer.write(myXML);
				writer.close();

			} catch (IOException f) {
				f.printStackTrace();
			}
			
		} catch (Exception e) {
			System.out.println("Nao existe arquivo");
		}
	}

	/**
	 * 
	 * Remove o Objeto na posicao escolhida, se o arquivo nao existe ele sera
	 * criado em branco
	 * 
	 * @param posicao
	 *            Eh a posicao do objeto a ser removido
	 * 
	 * */
	public void remover(int posicao) {
		FileReader reader = null;
		List pw = null;
		try {
			reader = new FileReader(this.nomeDoArquivo);

		} catch (FileNotFoundException e) {

			XStream xstream = new XStream(new DomDriver());
			String myXML = xstream.toXML(lista);

			BufferedWriter writer = null;
			
			try {
				writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}

			try {
				writer.write(myXML);
				writer.close();
				System.out.println("Arquivo nao existia.\n...\nArquivo foi criado.");

			} catch (IOException f) {
				f.printStackTrace();
			}
		}

		XStream xstream = new XStream(new DomDriver());
		try {

			pw = ((List) xstream.fromXML(reader));
			pw.remove(posicao);

			String myXML = xstream.toXML(pw);

			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
			} catch (IOException e1) {

			}

			try {
				writer.write(myXML);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Nao existe arquivo");
		}

	}

	/**
	 * Atualiza grava um objeto por cima do arquivo existente
	 * 
	 * @param obj
	 *            Eh um Object que sera gravado no arquivo
	 * */
	public void atualiza(Object obj) {
		XStream xstream = new XStream(new DomDriver());

		String myXML = xstream.toXML(obj);
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
		} catch (IOException e1) {

		}

		try {
			writer.write(myXML);
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Nao existe arquivo");
		}
	}

}
