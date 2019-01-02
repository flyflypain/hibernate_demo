package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

	@Override
	List<Trade> findAll();

	Trade findByTradeId(long tradeId);

	@SuppressWarnings("unchecked")
	Trade saveAndFlush(Trade trade);

//	@Transactional
//	@Modifying
//	@Query("update Balance b set b.amount = amount + ?1 where b.account = ?2")
//	void addBalance(int balance, String account);
//
//	@Transactional
//	@Modifying
//	@Query("update Balance b set b.amount = amount - ?1 where b.account = ?2")
//	void minusBalance(int balance, String account);

}
