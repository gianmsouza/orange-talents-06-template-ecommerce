package br.com.zupacademy.gian.mercadolivre.produto.pagamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RetornoPagamentoRepository extends JpaRepository<RetornoPagamento, Long> {

	@Query(value = "select * from retorno_pagamento where compra_id = :id and (status = 'SUCESSO' or status = '1')", nativeQuery = true)
	List<RetornoPagamento> findByCompraIdAndStatus(Long id);

}
