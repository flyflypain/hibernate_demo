package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.enums.TradeType;
import com.practice.model.Trade;
import com.practice.response.TradeResponse;
import com.practice.response.builder.TradeResponseBuilder;
import com.practice.service.TradeService;

@RestController
@RequestMapping(value = "/trade")
public class TradeController {

	@Autowired
	private TradeService tradeService;

	@PostMapping(value = "")
	public TradeResponse doTrade(@RequestParam(name = "targetAccount", required = false) String targetAccount,
			@RequestParam(name = "executeAccount", required = false) String executeAccount,
			@RequestParam("amount") int amount, @RequestParam("tradeType") String tradeType) {

		Trade trade = new Trade(targetAccount, executeAccount, amount, TradeType.valueOf(tradeType));
		TradeResponse tradeResponse = TradeResponseBuilder.build(tradeService.executeTrade(trade));
		return tradeResponse;
	}

}
