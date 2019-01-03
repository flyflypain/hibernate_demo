package com.practice.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.practice.demo.DemoApplication;
import com.practice.enums.TradeType;
import com.practice.model.Trade;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TradeRepositoryTest {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private CashflowRepository cashflowRepository;

	@Before
	public void setUp() {

		tradeRepository.deleteAll();

		// 实例化trade
		Trade trade1 = new Trade("test1", "test2", 100, TradeType.TRANSFER);
		trade1.createCashflowForTrade();
		tradeRepository.saveAndFlush(trade1);
	}

	@Test
	public void CURD_create() {
		assertEquals(1, tradeRepository.findAll().size());
		assertEquals(2, cashflowRepository.findAll().size());
	}

	@Test
	public void CRUD_delete() {
		tradeRepository.deleteAll();
		assertEquals(0, tradeRepository.findAll().size());
	}

//	@Test
//	public void CRUD_retrieve() {
//		Balance result = tradeRepository.findByAccount("test1");
//		assertEquals(1000, result.getAmount());
//	}
//
//	@Test
//	public void CRUD_add() {
//		tradeRepository.addBalance(1111, "test1");
//		Balance result = tradeRepository.findByAccount("test1");
//		assertEquals(2111, result.getAmount());
//	}
//
//	@Test
//	public void CRUD_minus() {
//		tradeRepository.minusBalance(1000, "test2");
//		Balance result = tradeRepository.findByAccount("test2");
//		assertEquals(1000, result.getAmount());
//	}

}
