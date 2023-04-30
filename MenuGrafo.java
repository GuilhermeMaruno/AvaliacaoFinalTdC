package grafo;

import java.util.Scanner;

public class MenuGrafo {
	public static Grafo opera(Grafo g,Scanner sc,String nome) {
        int nav = 0;
        String vertX, vertY;

        Menu m = new Menu();

        while (true) {
            try {
				nav = m.imprimeMenuGrafo(sc,nome);
			} catch (Exception e) {
				
			}
            sc.nextLine();
            switch (nav) {
            	case 1:
                    System.out.println("\nGrafo "+nome+"\n");
            		g.imprime();
            		break;
                case 2:
                    System.out.println("\nVerificar Arestas");
                    System.out.println("\nInsira o primeiro vértice: ");
                    vertX = sc.nextLine();
                    System.out.println("\nInsira o segundo vértice: ");
                    vertY = sc.nextLine();
                    if (g.adjacente(vertX, vertY)) {
                        System.out.println("\nExiste");
                    } else {
                        System.out.println("\nNão Existe");
                    }
                    break;

                case 3:
                    System.out.println("\nVizinhos");
                    System.out.println("\nInsira o vértice: ");
                    vertX = sc.nextLine();
                    System.out.println("Vizinhos: "+g.vizinhos(vertX));
                    break;

                case 4:
                    System.out.println("\nAdicionando Vertice");
                    System.out.println("\nInsira o vértice: ");
                    vertX = sc.nextLine();
                    g.addVertex(vertX);
                    break;

                case 5:
                    System.out.println("\nRemover Vertice");
                    System.out.println("\nInsira o vértice: ");
                    vertX = sc.nextLine();
                    g.removeVertex(vertX);
                    break;

                case 6:
                    System.out.println("\nCriar Aresta");
                    System.out.println("\nInsira o primeiro vértice: ");
                    vertX = sc.nextLine();
                    System.out.println("\nInsira o segundo vértice: ");
                    vertY = sc.nextLine();
                    g.addEdge(vertX, vertY);
                    break;

                case 7:
                    System.out.println("\nDeletar Aresta");
                    System.out.println("\nInsira o primeiro vértice: ");
                    vertX = sc.nextLine();
                    System.out.println("\nInsira o segundo vértice: ");
                    vertY = sc.nextLine();
                    g.removeEdge(vertX, vertY);
                    break;
                    
                case 8: 
                	System.out.println("\nEncontrar Caminho");
                    System.out.println("\nInsira o primeiro vértice: ");
                    vertX = sc.nextLine();
                    System.out.println("\nInsira o segundo vértice: ");
                    vertY = sc.nextLine();
                    g.findCaminho(vertX,vertY);
                	break;

                case 9:
                    System.out.println("\nVoltando...");
                    return g;

                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
