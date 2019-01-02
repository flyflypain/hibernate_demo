package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.practice.event.TradeEvent;
import com.practice.exception.TestException;
import com.practice.model.Balance;
import com.practice.repository.BalanceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

	@Autowired
	private BalanceRepository balanceRepository;

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void transferForTrade(TradeEvent tradeEvent) {
		String tragetAccount = tradeEvent.getTrade().getTragetAccount();
		String executeAccount = tradeEvent.getTrade().getExecuteAccount();
		int amount = tradeEvent.getTrade().getAmount();
		try {
			transferTo(tragetAccount, amount);
			transferFrom(executeAccount, amount);
		} catch (Exception e) {
			throw new TestException("400", "error occur while runtime");
		}
		log.info("{} transfer {} to {}", executeAccount, amount, tragetAccount);
	}

	private Balance transferTo(String targetAccount, int amount) {
		balanceRepository.addBalance(amount, targetAccount);
		Balance newBalance = balanceRepository.findByAccount(targetAccount);
		return newBalance;
	}

	private Balance transferFrom(String executeAccount, int amount) {
		balanceRepository.minusBalance(amount, executeAccount);
		Balance newBalance = balanceRepository.findByAccount(executeAccount);
		return newBalance;
	}

}
