package com.livro.capitulo3.produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class ProdutoDAO {
	Transaction transacao = null;
	Session sessao = null;

	public void salva(Produto produto) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.save(produto);
			this.transacao.commit();
		} catch (HibernateException e) {

			System.out.println("Não Foi Possivel Inserir o Poduto: Erro. "
					+ e.getMessage());

		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();

				}

			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operacao de insercao. Erro. " + e2.getMessage());
			}

		}

	}
	
	public void atualizar(Produto produto){
		Transaction transacao = null;
		Session sessao = null;
		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.getTransaction();
			this.sessao.update(produto);
			this.transacao.commit();
			
		} catch (HibernateException e) {
			System.out.println("Não foi Possível Inserir o Produto. Erro: " + e.getMessage());
		}finally{
			
			try {
    			if (this.sessao.isOpen()) {
				   this.sessao.close();		
				    	
				}

				
			} catch (Exception e2) {
				System.out.println("Erro ao fechar operacão de Atualização. Erro: " + e2.getMessage());
			}
		   			
		}
		
		
	}
	
    public void excluir(Produto produto){
    	
    	try{
    		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        	this.transacao = this.sessao.beginTransaction();
        	this.sessao.delete(produto);
        	this.transacao.commit();
    	} catch(HibernateException e) {
        	
			System.out.println("Não foi possivel excluir o Cliente. Erro: " + e.getMessage());
    	} finally {
			
			try {
				if (this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operacao de exclusao. Erro: " + e2.getMessage());
			}
		
    	}  	
    	
    	
    }
    
    public Produto buscaProduto(Integer codigo) {
    	
    	Produto produto = null ;
    	
    	try {
    		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        	this.transacao = this.sessao.beginTransaction();
        	Criteria filtro = this.sessao.createCriteria(Produto.class);
        	filtro.add(Restrictions.eq("produto", codigo));
        	produto = (Produto) filtro.uniqueResult();
        	this.transacao.commit();
			
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		}finally{
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
				
			} catch (Exception e2) {
				System.out.println("Erro ao fechar procedimento de consulta. Erro: " + e2.getMessage());
			}		
		
			
		}
		return produto;
    
    			
    	
    }
    
    public List<Produto> listar(){
    	
    	List<Produto> produtos = null ;
    	
    	try {
    		this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        	this.transacao = this.sessao.beginTransaction();
        	Criteria filtro = this.sessao.createCriteria(Produto.class);
        	produtos = filtro.list();
        	this.transacao.commit();
        	
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		}finally{
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar processo de listagem. Erro: " + e2.getMessage());
			}
			
		}
		return produtos;
    	
    	
    	
    }
    

}
