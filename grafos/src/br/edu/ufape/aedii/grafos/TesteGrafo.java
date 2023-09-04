package br.edu.ufape.aedii.grafos;

public class TesteGrafo {
	public static void main(String[] args) {
		Grafo g = new GrafoMatricial(4);
		g.imprimir();
		
		g.adicionarVertice("A");
		g.adicionarVertice("B");
		g.adicionarVertice("C");
		g.adicionarVertice("D");
		
		g.adicionarAresta("A", "C");
		g.adicionarAresta("A", "D");
		g.adicionarAresta("D", "B");
		
		BuscaLargura b = new BuscaLargura(g);
		b.execute("D");
		
		b.imprimir();
		

	}
}
