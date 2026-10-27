package coffee;

public class Adicional {
	private int codigo;
	private String nome;
	private double preco;
	
	public Adicional(int codigo, String nome, double preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
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
	
	public String exibirDados() {
		return "Código: " + codigo + "\nNome: " + nome + "\nPreço: R$ " + preco + "\n";  
	}
}
