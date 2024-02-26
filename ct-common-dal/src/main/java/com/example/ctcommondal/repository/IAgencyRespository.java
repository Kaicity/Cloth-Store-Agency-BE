package com.example.ctcommondal.repository;

import com.example.ctcommondal.entity.AgencyEntity;
import com.example.ctcommondal.entity.ImportingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgencyRespository extends JpaRepository<AgencyEntity, String> {
    @Query("SELECT a FROM AgencyEntity a WHERE a.id = :id ")
    AgencyEntity findAgencyById(@Param("id") String id);
}
