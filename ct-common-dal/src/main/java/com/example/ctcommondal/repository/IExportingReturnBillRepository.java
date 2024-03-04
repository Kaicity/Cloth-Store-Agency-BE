package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ExportingReturnBillEntity;
import com.example.ctcommondal.entity.ImportingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IExportingReturnBillRepository extends JpaRepository<ExportingReturnBillEntity, String> {
    @Query("SELECT i FROM ExportingReturnBillEntity i WHERE i.id = :id ")
    ExportingReturnBillEntity findExportingReturnById(@Param("id") String id);
}
