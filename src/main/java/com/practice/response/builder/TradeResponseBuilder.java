package com.practice.response.builder;

import com.practice.model.Trade;
import com.practice.response.TradeResponse;

public class TradeResponseBuilder {

	public static TradeResponse build(Trade trade) {
		TradeResponse tradeResponse = new TradeResponse();
		tradeResponse.setAmount(trade.getAmount());
		tradeResponse.setCashflowList(trade.getCashflowList());
		tradeResponse.setExecuteAccount(trade.getExecuteAccount());
		tradeResponse.setTragetAccount(trade.getTragetAccount());
		tradeResponse.setTradeType(trade.getTradeType());
		return tradeResponse;
	}

}
