package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ImportingTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImportingTransactionRepository extends JpaRepository<ImportingTransactionEntity, String> {
    @Query("select i from ImportingTransactionEntity i WHERE i.importingId in :importingIds")
    List<ImportingTransactionEntity> getAllDetails(@Param("importingIds") List<String> importingIds);

    @Query("select i from ImportingTransactionEntity i WHERE i.importingId in :importingId")
    List<ImportingTransactionEntity> findListImportingTransactionId(@Param("importingId") String importingId);

    @Query("select i from ImportingTransactionEntity i WHERE i.id in :id")
    ImportingTransactionEntity findImportingTransactionById(@Param("id") String id);
}
