package com.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.practice.enums.CashflowSide;

import lombok.Data;

/**
 * 银行交易产生的现金流entity
 * 
 * @author lisheng
 *
 */
@Data
@Entity
@Table(name = "Cashflow")
public class Cashflow extends AbstractModel {

	/**
	 * 构造器
	 * 
	 * @param executeAccount
	 * @param tragetAccount
	 * @param amount
	 * @param cashflowSide
	 */
	public Cashflow(String executeAccount, String tragetAccount, int amount, CashflowSide cashflowSide) {
		this.executeAccount = executeAccount;
		this.tragetAccount = tragetAccount;
		this.amount = amount;
		this.cashflowSide = cashflowSide;
	}

	/**
	 * defalut constructor
	 */
	public Cashflow() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cashflow_id")
	private long cashflowId;

	/**
	 * 转账账号名字
	 */
	@Column(name = "execute_account", nullable = true)
	private String executeAccount;

	/**
	 * 到账账号名字
	 */
	@Column(name = "traget_account", nullable = true)
	private String tragetAccount;

	/**
	 * 转账金额
	 */
	@Column(name = "amount")
	private int amount;

	/**
	 * {@link CashflowSide}
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "cashflow_side")
	private CashflowSide cashflowSide;

}
