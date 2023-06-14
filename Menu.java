package grafo;

import java.util.Scanner;

public class Menu {
	
	public int imprimeMenuGrafo(Scanner sc, String nome) throws Exception {
		int nav;
		
		System.out.println("Gerenciar Grafo "+nome+"\nSELECIONE\n"+
		"\n1- Imprime\n2- Verificar Aresta\n3- Checar Vizinho\n4- Criar Vertice\n"+
		"5- Deletar Vertice\n6- Criar Aresta\n7- Deletar Aresta\n8- Encontrar Caminho\n9- Busca BFS\n10- Voltar");
		nav = sc.nextInt();
		
		return nav;
	}
	
	public int imprimeMenu(Scanner sc) throws Exception {
		int nav;
		
		System.out.println("SELECIONE\n"+
		"\n1- Gerenciar Grafo G1\n2- Gerenciar Grafo G2\n3- Uniao (G1,G2)\n"+
		"4- Intersecao (G1,G2)\n5- Sair");
		nav = sc.nextInt();
		
		return nav;
	}

}
