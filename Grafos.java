package grafo;

import java.util.LinkedList;
import java.util.List;

public class Grafos {
	public static Grafo uniao(Grafo g1, Grafo g2) {
	    Grafo uniao = new Grafo();

	    for (Vertex v : g1.getVertices()) {
	      uniao.addVertex(v.getLabel());
	    }

	    for (Vertex v : g2.getVertices()) {
	      if (!uniao.existeVertice(v)) {
	        uniao.addVertex(v.getLabel());
	      }
	    }


	    for (Vertex v : g1.getVertices()) {
	      for (Aresta a : v.getEdges()) {
	        Vertex from = uniao.findVertex(a.getFrom().getLabel());
	        Vertex to = uniao.findVertex(a.getTo().getLabel());
	        from.addAresta(new Aresta(from, to, a.getWeight()));
	      }
	    }

	    for (Vertex v : g2.getVertices()) {
	      for (Aresta a : v.getEdges()) {
	        Vertex from = uniao.findVertex(a.getFrom().getLabel());
	        Vertex to = uniao.findVertex(a.getTo().getLabel());
	        if (!uniao.adjacente(from.getLabel(), to.getLabel())) {
	          from.addAresta(new Aresta(from, to, a.getWeight()));
	        }
	      }
	    }

	    return uniao;
	}
	
	public static Grafo intersecao(Grafo g1, Grafo g2) {
	    Grafo intersecao = new Grafo();

	    for (Vertex v1 : g1.getVertices()) {
	        Vertex v2 = g2.findVertex(v1.getLabel());
	        if (v2 != null) {
	            intersecao.addVertex(v1.getLabel());
	            for (Aresta e1 : v1.getEdges()) {
	                Aresta e2 = v2.getEdge(e1.getFrom().getLabel(), e1.getTo().getLabel());
	                if (e2 != null) {
	                    intersecao.addEdge(e1.getFrom().getLabel(), e1.getTo().getLabel());
	                }
	            }
	        }
	    }
	    
	    return intersecao;
	}
}
