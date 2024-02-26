package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ExportingReturnBillEntity;
import com.example.ctcommondal.entity.ImportingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExportingReturnBillRepository extends JpaRepository<ExportingReturnBillEntity, String> {
}
