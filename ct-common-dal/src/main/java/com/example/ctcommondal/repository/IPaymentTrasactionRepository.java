package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.PaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPaymentTrasactionRepository extends JpaRepository<PaymentTransactionEntity, String> {
    @Query("select e from PaymentTransactionEntity e WHERE e.idPayment in :paymentIds")
    List<PaymentTransactionEntity> getAllDetails(@Param("paymentIds") List<String> paymentIds);

    @Query("select e from PaymentTransactionEntity e WHERE e.idPayment in :id_payment")
    List<PaymentTransactionEntity> findByPaymentId(@Param("id_payment") String id_payment);
}
