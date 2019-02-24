//package com.practice.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.practice.demo.DemoApplication;
//import com.practice.enums.TradeType;
//import com.practice.model.Trade;
//import com.practice.repository.TradeRepository;
//
//import lombok.experimental.Wither;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
//public class TradeServiceTest {
//
//	@InjectMocks
//	@Autowired
//	private TradeService tradeService;
//
//	@Mock
//	private TradeRepository tradeRepository;
//	
//	@Autowired
//	private cash
//	
//	@org.junit.Before
//	public void setUp() throws Exception {
//
//		Trade trade = new Trade("test1", "test2", 100, TradeType.TRANSFER);
//		when(tradeRepository.saveAndFlush(trade)).thenReturn(trade);
//	}
//
//	@Test
//	public void 正常系_Transfer_success() {
//		// setup
//		Trade trade = new Trade("test1", "test2", 100, TradeType.TRANSFER);
//
//		// execute
//		Trade result = tradeService.executeTrade(trade);
//
//		// verify
//		assertNotNull(result);
//		assertEquals(TradeType.TRANSFER, result.getTradeType());
//		assertEquals("test1", result.getTragetAccount());
//	}
//
//	@Test
//	public void 正常系_Deposit_success() {
//		// setup
//		Trade trade = new Trade("test1", null, 100, TradeType.DEPOSIT);
//		// execute
//		Trade result = tradeService.executeTrade(trade);
//		// verify
//		assertEquals(TradeType.DEPOSIT, result.getTradeType());
//	}
//}
