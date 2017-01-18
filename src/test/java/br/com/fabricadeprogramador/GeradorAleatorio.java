package br.com.fabricadeprogramador;

import java.util.Random;

public class GeradorAleatorio {

	public String criarNomeAleatorio(){
		String nomes[] = {"Ana","Pedro","Paulo","Joaquim","Francisco","André","Paula","Clara"};
		String sobrenomes[] = {"Santos","Silva","Francisco","Nascimento","Andrade","De Jesus","Pereira","Morais"};
		int tamanhoNome = nomes.length;
		int tamanhoSobrenome = sobrenomes.length;
		
		Random gerador = new Random();
		String nomeGerado = nomes[gerador.nextInt(tamanhoNome)];
        int qtdNomes = gerador.nextInt(2) + 1;// De 1 ou 2 sobrenomes gerados
        
		for(int i = 0; i < qtdNomes; i++){
			//Gerando os sobrenomes aleatorios
			nomeGerado += " "+sobrenomes[gerador.nextInt(tamanhoSobrenome)];;
		}
		
		return nomeGerado;
	}

	public String criarNumeroAleatorio(int qtdNumeros){
		String numeroGerado = "";
		
		Random gerador = new Random();
		for(int i = 0; i < qtdNumeros; i++){
			//Gerando os sobrenomes aleatorios
			numeroGerado += gerador.nextInt(10);
		}
		return numeroGerado;
	}
}
