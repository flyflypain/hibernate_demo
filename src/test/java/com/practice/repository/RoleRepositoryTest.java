package com.practice.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.practice.demo.DemoApplication;
import com.practice.model.Userpool;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RoleRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void CRUD_retrieve() {
		List<Userpool> result = userRepository.findAll();
		assertEquals(3, result.size());
	}
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
