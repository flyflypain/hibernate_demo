package com.practice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.practice.enums.TradeType;

import lombok.Data;

/**
 * 银行交易记录的entity
 * 
 * @author lisheng
 *
 */
@Data
@Entity
@Table(name = "Trade")
public class Trade {

	public Trade(String targetAccount, String executeAccount, int amount, TradeType tradeType) {
		this.tragetAccount = targetAccount;
		this.executeAccount = executeAccount;
		this.amount = amount;
		this.tradeType = tradeType;
	}

	/**
	 * default constructor
	 */
	public Trade() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trade_id")
	private long tradeId;

	/**
	 * 到账账号名字
	 */
	@Column(name = "traget_account", nullable = true)
	private String tragetAccount;

	/**
	 * 转账账号名字
	 */
	@Column(name = "execute_account", nullable = true)
	private String executeAccount;

	/**
	 * 转账金额
	 */
	@Column(name = "amount")
	private int amount;

	/**
	 * 更新版本
	 */
	@Column(name = "version")
	private int version = 1;

	/**
	 * 交易类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "trade_type")
	private TradeType tradeType;

	/**
	 * 相对应的cashflow
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trade", cascade = CascadeType.ALL)
	private List<Cashflow> cashflowList;

	/**
	 * 绑定cashflow到Trade
	 */
	public void addCashflow(Cashflow cashflow) {
		if (cashflowList == null) {
			cashflowList = new ArrayList<Cashflow>();
		}
		cashflowList.add(cashflow);
		cashflow.setTrade(this);
	}

}
