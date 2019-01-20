package com.practice.response.builder;

import com.practice.model.Trade;
import com.practice.response.TradeResponse;

public class TradeResponseBuilder extends ClientAbstractResponseBuilder<TradeResponse, Trade> {

	public TradeResponseBuilder(Trade trade) {
		super(new TradeResponse(), trade);
	}

	@Override
	public void construct() {
		clientResponse.setAmount(model.getAmount());
		clientResponse.setCashflowList(model.getCashflowList());
		clientResponse.setExecuteAccount(model.getExecuteAccount());
		clientResponse.setTragetAccount(model.getTragetAccount());
		clientResponse.setTradeType(model.getTradeType());
		clientResponse.setVersion(model.getVersion());
	}

}
