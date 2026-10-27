package coffee;

public class Pedido {
	private int codigo;
	private ItemPedido[] itens;
	private int quantidadeDeItens;
	private String status;
	
	public Pedido(int codigo, int capacidadeMaxima) {
		this.codigo = codigo;
		quantidadeDeItens = 0;
		itens = new ItemPedido[capacidadeMaxima];
		status = "Aberto";
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getStatus(){
		return status;
	}
	
	public boolean adicionarItem(ItemPedido item){
		if(item == null || !status.equals("Aberto") || quantidadeDeItens == itens.length) {
			return false;
		}
		for(int i = 0; i < itens.length; i++) {
			if(itens[i] == null) {
				itens[i] = item;
				quantidadeDeItens++;
				return true;
			}
		}
		return false;
	}
	
	public double calcularTotal() {
	    return calcularTotal(0);
	}
	
	private double calcularTotal(int indice) {
	    if (indice == itens.length) {
	        return 0;
	    }

	    if (itens[indice] == null) {
	        return calcularTotal(indice + 1);
	    }

	    return itens[indice].calcularSubtotal() + calcularTotal(indice + 1);
	}
	
	public String exibirResumo() {
	    String resumo = "";

	    resumo += "\n================================\n";
	    resumo += "          CAFE AURORA\n";
	    resumo += "             PEDIDO " + codigo + "\n";
	    resumo += "================================\n\n";

	    resumo += "Status: " + status + "\n\n";
	    resumo += "ITENS DO PEDIDO\n";

	    if (quantidadeDeItens == 0) {
	        resumo += "Nenhum produto adicionado!\n";
	    } else {
	        for (int i = 0; i < itens.length; i++) {
	            if (itens[i] != null) {
	                resumo += "\n--------------------------------\n";
	                resumo += "Item " + (i + 1) + "\n";
	                resumo += itens[i].exibirDados() + "\n";
	            }
	        }
	    }

	    resumo += "\n================================\n";
	    resumo += "Quantidade de itens: " + quantidadeDeItens + "\n";
	    resumo += "Valor total: R$ " + calcularTotal() + "\n";
	    resumo += "================================";

	    return resumo;
	}
	
	public boolean finalizarPedido(){
		if(!status.equals("Aberto")) {
			return false;
		}
		if(quantidadeDeItens == 0) {
			return false;
		}
		status = "Finalizado";
		return true;
	}
	
	public boolean cancelarPedido(){
		if(status.equals("Aberto")) {
			status = "Cancelado";
			return true;
		}
		return false;
		
	}

}
