package com.codeup.deimosspringblog.repositories;

import com.codeup.deimosspringblog.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
