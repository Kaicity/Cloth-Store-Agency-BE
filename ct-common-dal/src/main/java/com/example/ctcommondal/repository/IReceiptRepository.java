package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReceiptRepository extends JpaRepository<ReceiptEntity, String> {
    @Query("select e from ReceiptEntity e ")
    List<ReceiptEntity> getAllReceipt();
}
