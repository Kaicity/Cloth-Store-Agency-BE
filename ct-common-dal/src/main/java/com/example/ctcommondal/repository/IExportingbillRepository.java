package com.example.ctcommondal.repository;
import com.example.ctcommondal.entity.ExportbillEntity;
import com.example.ctcommondal.entity.ImportingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IExportingbillRepository extends JpaRepository<ExportbillEntity, String> {

    @Query("select e from ExportbillEntity e ")
    List<ExportbillEntity> getAllBill();
    @Query("SELECT i FROM ExportbillEntity i WHERE i.id = :id ")
    ExportbillEntity findImportingById(@Param("id") String id);
}
