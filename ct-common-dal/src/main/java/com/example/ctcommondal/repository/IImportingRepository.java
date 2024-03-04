package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.ImportingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImportingRepository extends JpaRepository<ImportingEntity, String> {
    @Query("SELECT i FROM ImportingEntity i WHERE i.id = :id ")
    ImportingEntity findImportingById(@Param("id") String id);
    @Query("SELECT c FROM ImportingEntity c WHERE c.id in :ids  ")
    List<ImportingEntity> findAllImportingIds(@Param("ids") List<String> ids);
}
