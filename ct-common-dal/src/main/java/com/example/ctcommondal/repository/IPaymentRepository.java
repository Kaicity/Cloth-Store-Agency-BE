package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<PaymentEntity, String> {
    @Query("select e from PaymentEntity e ")
    List<PaymentEntity> getAllPayment();
}
