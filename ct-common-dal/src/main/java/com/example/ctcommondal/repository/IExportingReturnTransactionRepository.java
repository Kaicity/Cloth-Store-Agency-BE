package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ExportingReturnTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExportingReturnTransactionRepository extends JpaRepository<ExportingReturnTransactionEntity, String> {
    @Query("select i from ExportingReturnTransactionEntity i WHERE i.exportReturnId in :ExportingReturnIds")
    List<ExportingReturnTransactionEntity> getAllReturnDetails(@Param("ExportingReturnIds") List<String> ExportingReturnIds);

}
