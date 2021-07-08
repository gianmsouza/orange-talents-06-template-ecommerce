package br.com.zupacademy.gian.mercadolivre.produto.imagens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagensProdutoRepository extends JpaRepository<ImagensProduto, Long> {

}
