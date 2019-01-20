package com.practice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.enums.TradeType;
import com.practice.model.Trade;
import com.practice.request.TradeRegisterRequest;
import com.practice.request.TradeUpdateRequest;
import com.practice.response.TradeResponse;
import com.practice.response.builder.TradeResponseBuilder;
import com.practice.service.TradeService;

@RestController
@RequestMapping(value = "/trade")
public class TradeController {

	@Autowired
	private TradeService tradeService;

	@PostMapping(value = "")
	public TradeResponse doTrade(@RequestBody TradeRegisterRequest request) {

		String targetAccount = request.getTargetAccount();
		String executeAccount = request.getExecuteAccount();
		int amount = request.getAmount();
		String tradeType = request.getTradeType();
		Trade trade = new Trade(targetAccount, executeAccount, amount, TradeType.valueOf(tradeType));
		TradeResponse tradeResponse = new TradeResponseBuilder(tradeService.executeTrade(trade)).build();
		return tradeResponse;
	}

	@PostMapping(value = "/update")
	public TradeResponse doUpdate(@RequestBody TradeUpdateRequest request) {

		String targetAccount = request.getTargetAccount();
		String executeAccount = request.getExecuteAccount();
		int amount = request.getAmount();
		String tradeType = request.getTradeType();
		Trade trade = new Trade(targetAccount, executeAccount, amount, TradeType.valueOf(tradeType));
		trade.setTradeId(request.getTradeId());
		trade.setVersion(request.getVersion());
		tradeService.updateTrade(trade);

		Trade result = tradeService.getTrade(request.getTradeId());
		TradeResponse tradeResponse = new TradeResponseBuilder(result).build();

		return tradeResponse;
	}

	@GetMapping(value = "/getTrade")
	public List<TradeResponse> getTrade() {
		List<Trade> tradeList = tradeService.getTrade();
		List<TradeResponse> clientTradeResponseList = tradeList.stream()
				.map(trade -> new TradeResponseBuilder(trade).build()).collect(Collectors.toList());
		return clientTradeResponseList;

	}

}
