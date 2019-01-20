package com.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.event.DepositEvent;
import com.practice.event.TransferEvent;
import com.practice.event.WithdrawEvent;
import com.practice.model.Trade;
import com.practice.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private TradeRepository tradeRepository;

	@Transactional
	public Trade executeTrade(Trade trade) {
		trade.createCashflowForTrade();
		Trade result = tradeRepository.saveAndFlush(trade);
		publishTradeEvent(trade);
		return result;
	}

	@Transactional
	public Trade updateTrade(Trade trade) {
		Trade result = tradeRepository.saveAndFlush(trade);
		return result;
	}

	public Trade getTrade(long tradeId) {
		Trade result = tradeRepository.findByTradeId(tradeId);
		return result;
	}

	/**
	 * 发布交易trade event
	 */
	private void publishTradeEvent(Trade trade) {
		ApplicationEvent event = null;
		switch (trade.getTradeType()) {
		case TRANSFER:
			event = new TransferEvent(trade);
			break;
		case DEPOSIT:
			event = new DepositEvent(trade);
			break;
		case WITHDRAW:
			event = new WithdrawEvent(trade);
			break;
		}
		publisher.publishEvent(event);
	}

	public List<Trade> getTrade() {
		List<Trade> result = tradeRepository.findAll();
		return result;
	}
}
