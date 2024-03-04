package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ExportbillEntity;
import com.example.ctcommondal.entity.ImportingReturnbillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImportingReturnBIllRepository extends JpaRepository<ImportingReturnbillEntity, String> {
    @Query("SELECT i FROM ImportingReturnbillEntity i WHERE i.id = :id ")
    ImportingReturnbillEntity findImportingById(@Param("id") String id);

    @Query("select e from ImportingReturnbillEntity e ")
    List<ImportingReturnbillEntity> getAllBill();
}
