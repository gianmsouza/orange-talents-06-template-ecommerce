package br.com.zupacademy.gian.mercadolivre.produto.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoProdutoRepository extends JpaRepository<OpiniaoProduto, Long> {

}
