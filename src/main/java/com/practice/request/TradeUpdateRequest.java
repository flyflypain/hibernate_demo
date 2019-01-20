package com.practice.request;

import lombok.Data;

@Data
public class TradeUpdateRequest {

	private long tradeId;

	private String targetAccount;

	private String executeAccount;

	private int amount;

	private String tradeType;

	private int version;
}
