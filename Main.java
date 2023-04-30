package grafo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nav = 0;
        Scanner sc = new Scanner(System.in);
        Grafo g1 = new Grafo();
        Grafo g2 = new Grafo();
        Grafo uniao = new Grafo();
        Grafo intersecao = new Grafo();
        Menu m = new Menu();

        while (true) {
            try {
				nav = m.imprimeMenu(sc);
			} catch (Exception e) {
				
			}
            sc.nextLine();
            switch (nav) {
                case 1:
                	g1 = MenuGrafo.opera(g1,sc,"G1");
                	break;
                
                case 2:
                	g2 = MenuGrafo.opera(g2,sc,"G2");
                	break;
                	
                case 3: 
                    System.out.println("\nUniao (G1,G2)\n");
                    uniao = Grafos.uniao(g1, g2);
                    uniao.imprime();
                	break;
                	
                case 4:
                	System.out.println("\nIntersecao (G1,G2)\n");
                    intersecao = Grafos.intersecao(g1, g2);
                    intersecao.imprime();
                	break;
                	
                case 5:
                	System.out.println("\nSaindo...");
                	sc.close();
                	return;
                	
                default:
                	System.out.println("\nOpção inválida. Tente novamente.");
                    break;
                	
            }
        
        }
    }
}