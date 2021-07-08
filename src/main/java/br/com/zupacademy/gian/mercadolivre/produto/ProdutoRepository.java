package br.com.zupacademy.gian.mercadolivre.produto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findByIdAndUsuarioId(Long idProduto, Long idUsuario);

}
