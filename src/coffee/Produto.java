package coffee;

public class Produto {
	private int codigo;
	private String nome;
	private double preco;
	private String categoria;
	
	public Produto(int codigo, String nome, double preco, String categoria) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getNome(){
		return nome;
	}
	
	public double getPreco(){
		return preco;
	}
	
	public String getCategoria(){
		return categoria;
	}
	
	public String exibirDados() {
		return "Código: " + codigo + "\nNome: " + nome + "\nPreço: R$ " + preco + "\nCategoria: " + categoria + "\n";  
	}
}
