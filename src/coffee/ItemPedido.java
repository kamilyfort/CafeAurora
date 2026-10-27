package coffee;

public class ItemPedido {
	private Produto produto;
	private int quantidade;
	private Adicional[] adicionais;

	public ItemPedido(Produto produto, int quantidade, int quantidadeDeAdicionais) {
		this.produto = produto;
		this.quantidade = quantidade;
		adicionais = new Adicional[quantidadeDeAdicionais];
	}
	
	public Produto getProduto(){
		return produto;
	}
	
	public int getQuantidade(){
		return quantidade;
	}
	
	public Adicional[] getAdicionais(){
		return adicionais;
	}
	
	public double calcularSubtotal() {
		double valorSubtotal = 0;
		if(adicionais != null) {
			for(int i = 0; i < adicionais.length; i++) {
				if(adicionais[i] != null) {
					valorSubtotal += adicionais[i].getPreco();	
				}
			}
		}
		valorSubtotal = (valorSubtotal + produto.getPreco()) * quantidade;
		return valorSubtotal;
	}
	
	public String exibirDados() {
	    String dados = "";

	    dados += "Produto: " + produto.getNome() + "\n";
	    dados += "Categoria: " + produto.getCategoria() + "\n";
	    dados += "Preco unitario: R$ " + produto.getPreco() + "\n";

	    for (int i = 0; i < adicionais.length; i++) {
	        if (adicionais[i] != null) {
	            dados += "Adicional: " + adicionais[i].getNome() + "\n";
	            dados += "Preco do adicional: R$ "
	                    + adicionais[i].getPreco() + "\n";
	        }
	    }

	    dados += "Quantidade: " + quantidade + "\n";
	    dados += "Subtotal: R$ " + calcularSubtotal();

	    return dados;
	}
}
