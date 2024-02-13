package com.example.ctcommondal.repository;

import com.example.ctcommon.enums.TypePaymentReceipt;
import com.example.ctcommondal.entity.TypePaymentReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypePaymentReceiptRepository extends JpaRepository<TypePaymentReceiptEntity, String> {
    @Query("SELECT t FROM TypePaymentReceiptEntity t WHERE t.type = :type")
    List<TypePaymentReceiptEntity> findAllTypePaymentReceipt(@Param("type") TypePaymentReceipt type);

    @Query("SELECT t FROM TypePaymentReceiptEntity t WHERE t.id = :id")
    List<TypePaymentReceiptEntity> findTypePaymentReceiptById(@Param("id") TypePaymentReceipt id);
}
