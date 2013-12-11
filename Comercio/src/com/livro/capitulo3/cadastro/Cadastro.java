package com.livro.capitulo3.cadastro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.livro.capitulo3.categoria.Categoria;
import com.livro.capitulo3.categoria.CategoriaDAO;
import com.livro.capitulo3.produto.Produto;
import com.livro.capitulo3.produto.ProdutoDAO;

public class Cadastro {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cadastro cadastro = new Cadastro();
		
		cadastro.cadastrarProdutos();
		cadastro.cadastrarCategoria();
		System.out.println("Cadastros Gerados com sucesso!");
		

	}
	
	public void cadastrarProdutos() {
		String descricao[] = {"Bicicleta" , "Televisao" , "DVD"};
		Double preco[] = {356.83, 19.99, 195.60};
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = null ; 
		
		for (int i = 0; i < 3; i++) {
			produto = new Produto() ;
			produto.setDescricao(descricao[i]);
			produto.setPreco(preco[i]);
			produtoDAO.salva(produto);
		}
		
	}
	
	public void cadastrarCategoria(){
		String descricao[] = {"utilidades","geral"} ;
		CategoriaDAO  categoriaDAO = new CategoriaDAO();
		Categoria categoria = null ;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Set<Produto> produtos = new HashSet<Produto>();
		List<Produto> produtoListagem = produtoDAO.listar();
		
		for (int i = 0; i < produtoListagem.size(); i++) {
			produtos.add(produtoListagem.get(i));
		}
		
		for (int i = 0; i < 2; i++) {
			categoria = new Categoria();
			categoria.setDescricao(descricao[i]);
			categoria.setProdutos(produtos);
			categoriaDAO.salvar(categoria);
			
		} 
		
	}

}
