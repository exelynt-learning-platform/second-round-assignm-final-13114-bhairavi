package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Cart;
import com.spring.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

	List<Cart> findByUser(User user);

}
