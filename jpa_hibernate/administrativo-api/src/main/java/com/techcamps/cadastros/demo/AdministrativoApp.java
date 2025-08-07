package com.techcamps.cadastros.demo;

import com.techcamps.cadastros.entities.Produto;
import com.techcamps.cadastros.models.ProdutoModel;
import java.util.List;

public class AdministrativoApp {

	public static void main(String[] args) {
	    ProdutoModel produtoModel = new ProdutoModel();
	
	    Produto p1 = new Produto();
	    p1.setNome("TV");
	    p1.setPreco(300.0);
	    p1.setQuantidade(100);
	    p1.setStatus(true);
	
	    // 1) Criando um produto
	    produtoModel.create(p1);
	
	    //2) Buscando todos os produtos na base de dados
	    List<Produto> produtos = produtoModel.findAll();
	    System.out.println("Qtde de produtos encontrados : " + produtos.size());


	}
}