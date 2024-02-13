package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.PaymentTransactionEntity;
import com.example.ctcommondal.entity.ReceiptTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReceiptTransactionRepository extends JpaRepository<ReceiptTransactionEntity, String> {
    @Query("select e from ReceiptTransactionEntity e WHERE e.idReceipt in :codeIds")
    List<ReceiptTransactionEntity> getAllDetails(@Param("codeIds") List<String> receiptIds);

    @Query("select e from ReceiptTransactionEntity e WHERE e.idReceipt in :id_Receipt")
    List<ReceiptTransactionEntity> findByReceiptId(@Param("id_Receipt") String id_Receipt);
}
