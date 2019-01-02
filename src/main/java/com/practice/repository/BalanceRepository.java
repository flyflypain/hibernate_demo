package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.model.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

	@Override
	List<Balance> findAll();

	Balance findByAccount(String account);

	@Transactional
	@Modifying
	@Query("update Balance b set b.amount = amount + ?1 where b.account = ?2")
	void addBalance(int balance, String account);

	@Transactional
	@Modifying
	@Query("update Balance b set b.amount = amount - ?1 where b.account = ?2")
	void minusBalance(int balance, String account);

}
