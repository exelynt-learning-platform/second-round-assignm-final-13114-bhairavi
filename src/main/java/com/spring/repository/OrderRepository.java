package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Order;
import com.spring.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUser(User user);
}