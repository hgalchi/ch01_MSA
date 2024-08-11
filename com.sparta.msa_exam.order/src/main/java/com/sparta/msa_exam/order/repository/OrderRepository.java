package com.sparta.msa_exam.order.repository;

import com.sparta.msa_exam.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    //조인하고 order.id와
    @Query("SELECT d.productId " +
            "FROM Orders as o " +
            "LEFT JOIN OrderDetails as d " +
            "ON o.id=d.order.id "+
            "WHERE o.id=:orderId "
    )
    public List<Integer> getOrderByIdWithProducts(@Param("orderId") Long orderId);

}
