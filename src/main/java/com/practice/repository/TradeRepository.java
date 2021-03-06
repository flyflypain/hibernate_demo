package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

	@Override
	List<Trade> findAll();

	Trade findByTradeId(long tradeId);

	@Transactional
	Trade saveAndFlush(Trade trade);

}
