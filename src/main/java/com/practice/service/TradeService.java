package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.event.TradeEvent;
import com.practice.model.Trade;

@Service
public class TradeService {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public void executeTrade(Trade trade) {
		publisher.publishEvent(new TradeEvent(trade));
	}

}
