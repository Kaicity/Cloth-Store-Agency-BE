package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ExportingBillTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExportingTransactionRepository extends JpaRepository<ExportingBillTransactionEntity,String> {
    @Query("select e from ExportingBillTransactionEntity e WHERE e.billID in :billIds")
    List<ExportingBillTransactionEntity> getAllDetails(@Param("billIds") List<String> billIds);
}
