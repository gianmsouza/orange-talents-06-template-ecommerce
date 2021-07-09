package br.com.zupacademy.gian.mercadolivre.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicasProdutoRepository extends JpaRepository<CaracteristicasProduto, Long> {

}
