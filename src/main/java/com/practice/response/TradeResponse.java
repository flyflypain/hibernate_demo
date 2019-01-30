package com.practice.response;

import java.util.List;

import com.practice.enums.TradeType;
import com.practice.model.Cashflow;

import lombok.Data;

@Data
public class TradeResponse extends AbstractResponse {

	public TradeResponse() {
		super();
	}

	/**
	 * 到账账号名字
	 */
	private String tragetAccount;

	/**
	 * 转账账号名字
	 */
	private String executeAccount;

	/**
	 * 转账金额
	 */
	private int amount;

	/**
	 * 更新版本
	 */
	private int version = 1;

	/**
	 * 交易类型
	 */
	private TradeType tradeType;

	/**
	 * 相对应的cashflow
	 */
	private List<Cashflow> cashflowList;

}
