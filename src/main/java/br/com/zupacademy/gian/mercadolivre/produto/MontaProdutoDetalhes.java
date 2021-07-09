package br.com.zupacademy.gian.mercadolivre.produto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import br.com.zupacademy.gian.mercadolivre.produto.imagens.ImagensProduto;
import br.com.zupacademy.gian.mercadolivre.produto.opiniao.OpiniaoProduto;
import br.com.zupacademy.gian.mercadolivre.produto.opiniao.OpiniaoProdutoResponse;
import br.com.zupacademy.gian.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.gian.mercadolivre.produto.pergunta.PerguntaResponse;

@Component
public class MontaProdutoDetalhes {
	
	public Long contarTotalDeNotasProduto(Long idProduto, EntityManager manager) {		
		String sql = "select count(*) from OpiniaoProduto where produto_id = :idProduto";
		
		TypedQuery<Long> query = manager.createQuery(sql, Long.class);
		query.setParameter("idProduto", idProduto);		
	
		return query.getSingleResult();
	}

	public Double mediaNotaProduto(Long idProduto, EntityManager manager) {
		String sql = "select avg(nota) from OpiniaoProduto where produto_id = :idProduto";
		
		TypedQuery<Double> query = manager.createQuery(sql, Double.class);
		query.setParameter("idProduto", idProduto);		
		
		return query.getSingleResult();
	}	
	
	public List<OpiniaoProdutoResponse> listaOpinioesProduto(Long idProduto, EntityManager manager) {
		String sql = "from OpiniaoProduto where produto_id = :idProduto";
		
		TypedQuery<OpiniaoProduto> query = manager.createQuery(sql, OpiniaoProduto.class);
		query.setParameter("idProduto", idProduto);			
		List<OpiniaoProduto> resultList = query.getResultList();		
		
		return resultList.stream().map(OpiniaoProdutoResponse::new).collect(Collectors.toList());
	}

	public List<PerguntaResponse> listaPerguntasProduto(Long idProduto, EntityManager manager) {
		String sql = "from Pergunta where produto_id = :idProduto";
		
		TypedQuery<Pergunta> query = manager.createQuery(sql, Pergunta.class);
		query.setParameter("idProduto", idProduto);			
		List<Pergunta> resultList = query.getResultList();	
		
		return resultList.stream().map(PerguntaResponse::new).collect(Collectors.toList());
	}

	public Set<CaracteristicasProdutoResponse> listaCaracteristicasProduto(Long idProduto, EntityManager manager) {
		String sql = "from CaracteristicasProduto where produto_id = :idProduto";
		
		TypedQuery<CaracteristicasProduto> query = manager.createQuery(sql, CaracteristicasProduto.class);
		query.setParameter("idProduto", idProduto);		
		List<CaracteristicasProduto> resultList = query.getResultList();		
		
		return resultList.stream().map(CaracteristicasProdutoResponse::new).collect(Collectors.toSet());
	}

	public Set<String> listaLinksImagens(Long idProduto, EntityManager manager) {
		String sql = "from ImagensProduto where produto_id = :idProduto";
		
		TypedQuery<ImagensProduto> query = manager.createQuery(sql, ImagensProduto.class);
		query.setParameter("idProduto", idProduto);		
		List<ImagensProduto> resultList = query.getResultList();
		Set<String> listaLinksImagens = new HashSet<>(); 
		
		for (ImagensProduto imagensProduto : resultList) {
			listaLinksImagens.add(imagensProduto.getLink());
		}
		
		return listaLinksImagens;
	}
}
