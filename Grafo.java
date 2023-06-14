package grafo;

import java.util.List;
import java.util.Stack;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;


class Aresta {
  private float weight = 1;
  private Vertex from;
  private Vertex to;

  public Aresta(Vertex from, Vertex to) {
    setFrom(from);
    setTo(to);
  }

  public Aresta(Vertex from, Vertex to, float weight) {
    setFrom(from);
    setTo(to);
    setWeight(weight);
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getWeight() {
    return weight;
  }

  private void setFrom(Vertex from) {
    this.from = from;
  }

  public Vertex getFrom() {
    return from;
  }

  private void setTo(Vertex to) {
    this.to = to;
  }

  public Vertex getTo() {
    return to;
  }


  public String toString() {
    return "[" + 
        this.weight + ", " + 
        this.from.getLabel() + ", " + 
        this.to.getLabel() + "]";
  }
}

class Vertex {
  private String label;
  private List<Aresta> edges = new LinkedList<>();

  public Vertex(String label) {
    setLabel(label);
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void addAresta(Aresta edge) {
    this.edges.add(edge);
  }

  public void removeEdge(Aresta edge) {
    edges.remove(edge);
  }

  public List<Aresta> getEdges() {
      return edges;
  }
 
  public Aresta getEdge(String vA, String vB){
      for(Aresta e : edges) {
    	  if(e.getFrom().getLabel().equals(vA)) {
    		  if(e.getTo().getLabel().equals(vB)) {
    			  return e;
    		  }
    	  }
      }return null;     
  }
  
  public void removeEdges(List<Vertex> lista, String vA) {
	    for (Vertex v : lista) {
	        List<Aresta> edge = v.getEdges();
	        Iterator<Aresta> iterator = edge.iterator();
	        while (iterator.hasNext()) {
	            Aresta e = iterator.next();
	            if (e.getFrom().getLabel().equals(vA)) {
	                iterator.remove();
	            }
	            if (e.getTo().getLabel().equals(vA)) {
	                iterator.remove();
	            }
	        }
	    }
	}
  

  public String toString() {
    String ts = "[Label: " + label;
    for (Aresta edge : this.edges) 
      ts += edge;
    return ts + "]";
  }
}

public class Grafo {
  
  private List<Vertex> vertexes = new LinkedList<>();

  public Grafo() {  }

  protected Vertex findVertex(String value) {
    try {
      return vertexes
          .stream()
          .filter(v -> v.getLabel().equals(value))
          .collect(Collectors.toList())
          .get(0);
    } catch(Exception e){
      return null;
    }
  }

  public void addVertex(String value) {
    var vertex = findVertex(value);
    if (vertex == null)
      vertexes.add(new Vertex(value));
  }

  public void removeVertex(String value) {
    var vertex = findVertex(value);
    if (vertex != null) {
    	vertex.removeEdges(vertexes,value);
    	vertexes.remove(vertex);
    }
  }
  

  public void addEdge(String valA, String valB) {
	  if(valA != null && valB != null) {
	        var va = findVertex(valA);
	        var vb = findVertex(valB);
	        if (vb != null && va != null) {
	            va.addAresta(new Aresta(va, vb));
	            vb.addAresta(new Aresta(vb, va));
	        }
	    }
  }
  
  public void removeEdge(String valA, String valB) {
	  if(valA != null && valB != null) {
		  var va = findVertex(valA);
		  var vb = findVertex(valB);
		  va.removeEdge(va.getEdge(valA,valB));
		  vb.removeEdge(vb.getEdge(valB, valA));
	  }
  }
 

  public List<Vertex> vizinhos(String value) {
      var v = findVertex(value);
      if (v == null)
          return null;
      List<Vertex> vs = new LinkedList<>();
      for(Aresta e : v.getEdges())
          vs.add(e.getTo());
      
      return vs;
  }

  public boolean adjacente(String vA, String vB) {
      var v = findVertex(vA);
      if (v != null) {
        return v.getEdges()
            .stream()
            .filter(e -> e.getTo().getLabel().equals(vB))
            .count() == 1;
      }
      return false;
  }

  public List<Vertex> findCaminho(String inicio, String fim) {
    var checkado = new HashSet<Vertex>();
    var stack = new Stack<Vertex>();
    var caminho = new LinkedList<Vertex>();

    var startVertex = findVertex(inicio);
    var endVertex = findVertex(fim);
    if (startVertex == null || endVertex == null)
      return null;

    stack.push(startVertex);
    checkado.add(startVertex);

    while (!stack.empty()) {
        var vertex = stack.pop();
        caminho.add(vertex);

        if (vertex == endVertex) {
            System.out.print("Caminho percorrido: ");
            for (Vertex v : caminho) {
                System.out.print(v.getLabel() + " ");
            }
            System.out.println();

            return caminho;
        }

        for (Aresta edge : vertex.getEdges()) {
            var neighbor = edge.getTo();
            if (!checkado.contains(neighbor)) {
                checkado.add(neighbor);
                stack.push(neighbor);
            }
        }
    }

    return null;
}

  public List<Vertex> getVertices(){
	  return vertexes;
  }
  
  public Boolean existeVertice(Vertex v) {
	  var vertice = findVertex(v.getLabel());
      if (vertice == null) return false;
      else return true;
          
  }
  
  public void imprime() {
	    System.out.println("Vertices: ");
	    for (Vertex v : vertexes) {
	        System.out.println(v.getLabel());
	    }
	    System.out.println("Arestas: ");
	    for (Vertex v : vertexes) {
	        for (Aresta e : v.getEdges()) {
	            System.out.println(e.toString());
	        }
	    }
	}
  
  public void buscaBfs(String inicio, String fim) {
	    var startVertex = findVertex(inicio);
	    var endVertex = findVertex(fim);

	    if (startVertex == null || endVertex == null) {
	        System.out.println("Vértices de início ou fim não encontrados.");
	        return;
	    }

	    var visited = new HashSet<Vertex>();
	    var queue = new LinkedList<Vertex>();
	    var path = new LinkedList<Vertex>();

	    queue.add(startVertex);
	    visited.add(startVertex);

	    while (!queue.isEmpty()) {
	        var currentVertex = queue.poll();
	        path.add(currentVertex);

	        if (currentVertex == endVertex) {
	            System.out.print("Caminho percorrido: ");
	            for (Vertex v : path) {
	                System.out.print(v.getLabel() + " ");
	            }
	            System.out.println();
	            return;
	        }

	        for (Aresta edge : currentVertex.getEdges()) {
	            var neighbor = edge.getTo();
	            if (!visited.contains(neighbor)) {
	                visited.add(neighbor);
	                queue.add(neighbor);
	            }
	        }
	    }

	    System.out.println("Caminho não encontrado.");
	}

  
  @Override
  public String toString() {
    var s = "";
    for(Vertex v : vertexes)
      s += v;
    return s;
  }
}
