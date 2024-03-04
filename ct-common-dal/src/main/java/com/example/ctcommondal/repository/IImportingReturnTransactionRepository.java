package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ExportingBillTransactionEntity;
import com.example.ctcommondal.entity.ImportingBillReturnTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImportingReturnTransactionRepository extends JpaRepository<ImportingBillReturnTransactionEntity, String> {
    @Query("select e from ImportingBillReturnTransactionEntity e WHERE e.billID in :billIds")
    List<ImportingBillReturnTransactionEntity> findTransactionbyId(@Param("billIds") String billIds);
    @Query("select e from ImportingBillReturnTransactionEntity e WHERE e.billID in :billIds")
    List<ImportingBillReturnTransactionEntity> getAllDetails(@Param("billIds") List<String> billIds);
}
