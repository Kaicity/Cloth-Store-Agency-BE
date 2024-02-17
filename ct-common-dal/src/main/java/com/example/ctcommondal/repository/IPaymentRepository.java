package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.PaymentEntity;
import com.example.ctcommondal.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<PaymentEntity, String> {
    @Query("select e from PaymentEntity e ")
    List<PaymentEntity> getAllPayment();

    @Query("SELECT p FROM PaymentEntity p WHERE p.id = :id ")
    PaymentEntity findPaymentById(@Param("id") String id);
}
