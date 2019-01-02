package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.enums.TradeType;
import com.practice.model.Trade;
import com.practice.service.TradeService;

@RestController
public class TradeController {

	@Autowired
	private TradeService tradeService;

	@PostMapping(value = "/trade")
	public String doTrade(@RequestParam("targetAccount") String targetAccount,
			@RequestParam("executeAccount") String executeAccount, @RequestParam("amount") int amount,
			@RequestParam("tradeType") String tradeType) {

		System.out.println(amount);
		Trade trade = new Trade(targetAccount, executeAccount, amount, TradeType.valueOf(tradeType));
		tradeService.executeTrade(trade);
		return "success";
	}
}
