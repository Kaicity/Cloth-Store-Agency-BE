package com.example.ctcommondal.repository;
import com.example.ctcommondal.entity.ExportbillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IExportingbillRepository extends JpaRepository<ExportbillEntity, String> {

    @Query("select e from ExportbillEntity e ")
    List<ExportbillEntity> getAllBill();

}
