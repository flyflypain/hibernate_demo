package com.practice.request;

import lombok.Data;

@Data
public class TradeRegisterRequest {

	private String targetAccount;

	private int amount;

	private String tradeType;

}
