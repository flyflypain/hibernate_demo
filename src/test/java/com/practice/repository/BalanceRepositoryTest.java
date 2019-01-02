package com.practice.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.practice.demo.DemoApplication;
import com.practice.model.Balance;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class BalanceRepositoryTest {

	@Autowired
	private BalanceRepository balanceRepository;

	@Before
	public void setUp() {
		balanceRepository.deleteAll();
		balanceRepository.saveAndFlush(new Balance("test1", 1000));
		balanceRepository.saveAndFlush(new Balance("test2", 2000));
		balanceRepository.saveAndFlush(new Balance("test3", 3000));
		balanceRepository.saveAndFlush(new Balance("test4", 4000));
		balanceRepository.saveAndFlush(new Balance("test5", 5000));
	}

	@Test
	public void CURD_create() {
		assertEquals(5, balanceRepository.findAll().size());
	}

	@Test
	public void CRUD_delete() {
		balanceRepository.deleteAll();
		assertEquals(0, balanceRepository.findAll().size());
	}

	@Test
	public void CRUD_retrieve() {
		Balance result = balanceRepository.findByAccount("test1");
		assertEquals(1000, result.getAmount());
	}

	@Test
	public void CRUD_add() {
		balanceRepository.addBalance(1111, "test1");
		Balance result = balanceRepository.findByAccount("test1");
		assertEquals(2111, result.getAmount());
	}

	@Test
	public void CRUD_minus() {
		balanceRepository.minusBalance(1000, "test2");
		Balance result = balanceRepository.findByAccount("test2");
		assertEquals(1000, result.getAmount());
	}

}
