package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ImportingBillReturnTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImportingReturnTransactionRepository extends JpaRepository<ImportingBillReturnTransactionEntity, String> {
}
