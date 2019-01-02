package com.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 银行账号余额entity
 * 
 * @author lisheng
 *
 */
@Entity
@Table(name = "Balance")
@Data
public class Balance {

	public Balance(String account, int amount) {
		this.account = account;
		this.amount = amount;
	}

	public Balance() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "balance_id")
	private long balance_id;

	@Column(name = "account")
	private String account;

	@Column(name = "amount")
	private int amount;

	@Column(name = "version")
	private int version = 1;
}
