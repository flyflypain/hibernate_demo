package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.practice.event.DepositEvent;
import com.practice.event.TransferEvent;
import com.practice.event.WithdrawEvent;
import com.practice.exception.TestException;
import com.practice.model.Balance;
import com.practice.repository.BalanceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BalanceService {

	@Autowired
	private BalanceRepository balanceRepository;

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void transferForTrade(TransferEvent transferEvent) {
		String tragetAccount = transferEvent.getTrade().getTragetAccount();
		String executeAccount = transferEvent.getTrade().getExecuteAccount();
		int amount = transferEvent.getTrade().getAmount();
		try {
			transferTo(tragetAccount, amount);
			transferFrom(executeAccount, amount);
		} catch (Exception e) {
			throw new TestException("400", "error occur while transfer");
		}
		log.info("{} transfer {} to {}", executeAccount, amount, tragetAccount);
	}

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void depositForTrade(DepositEvent depositEvent) {
		String tragetAccount = depositEvent.getTrade().getTragetAccount();
		int amount = depositEvent.getTrade().getAmount();
		try {
			transferTo(tragetAccount, amount);
		} catch (Exception e) {
			throw new TestException("400", "error occur while deposit");
		}
		log.info("{} deposit {}", tragetAccount, amount);
	}

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void withdrawForTrade(WithdrawEvent withdrawEvent) {
		String executeAccount = withdrawEvent.getTrade().getExecuteAccount();
		int amount = withdrawEvent.getTrade().getAmount();
		try {
			transferFrom(executeAccount, amount);
		} catch (Exception e) {
			throw new TestException("400", "error occur while withdraw");
		}
		log.info("{} withdraw {}", executeAccount, amount);
	}

	/**
	 * 转入
	 * 
	 * @param targetAccount
	 * @param amount
	 * @return
	 */
	private Balance transferTo(String targetAccount, int amount) {
		balanceRepository.addBalance(amount, targetAccount);
		Balance newBalance = balanceRepository.findByAccount(targetAccount);
		return newBalance;
	}

	/**
	 * 转出
	 * 
	 * @param executeAccount
	 * @param amount
	 * @return
	 */
	private Balance transferFrom(String executeAccount, int amount) {
		balanceRepository.minusBalance(amount, executeAccount);
		Balance newBalance = balanceRepository.findByAccount(executeAccount);
		return newBalance;
	}

}
