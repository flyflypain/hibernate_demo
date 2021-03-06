package com.practice.event;

import org.springframework.context.ApplicationEvent;

import com.practice.model.Trade;

import lombok.Data;

@Data
public class WithdrawEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public WithdrawEvent(Trade trade) {
		super(trade);
		this.trade = trade;
	}

	private String message;

	private Trade trade;
}
