package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.model.Userpool;

@Repository
public interface UserRepository extends JpaRepository<Userpool, Long> {

	@Override
	List<Userpool> findAll();

	@SuppressWarnings("unchecked")
	@Override
	Userpool saveAndFlush(Userpool userpool);

	Userpool findByUserName(String userName);

}
