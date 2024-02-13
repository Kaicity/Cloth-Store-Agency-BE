package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.PaymentDto;
import com.example.ctapi.dtos.response.PaymentFullDto;
import com.example.ctapi.dtos.response.PaymentSearchDto;
import com.example.ctapi.dtos.response.PaymentTransactionDto;
import com.example.ctapi.mappers.IPaymentMapper;
import com.example.ctapi.mappers.IPaymentTransactionMapper;
import com.example.ctapi.services.IPaymentService;
import com.example.ctcommon.enums.PaymentStatus;
import com.example.ctcommondal.entity.PaymentEntity;
import com.example.ctcommondal.entity.PaymentTransactionEntity;
import com.example.ctcommondal.repository.IPaymentRepository;
import com.example.ctcommondal.repository.IPaymentTrasactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IPaymentserviceImpl implements IPaymentService {
    private final Logger logger = LoggerFactory.getLogger(IPaymentserviceImpl.class);
    private final IPaymentRepository iPaymentRepository;
    private final IPaymentTrasactionRepository iPaymentTrasactionRepository;

    @Transactional
    @Override
    public void createPayment(PaymentFullDto paymentFull) {
        try {
            //set status pament
            paymentFull.getPayment().setStatus(PaymentStatus.UNCOMPLETE);
            //mapper từ paymentDto sang entity xong lưu
            PaymentEntity paymentEntity = IPaymentMapper.INSTANCE.toFromPaymentDto(paymentFull.getPayment());
            iPaymentRepository.save(paymentEntity);

            //duyện qua vòng lặp
            for (PaymentTransactionDto detail : paymentFull.getPaymentTransactions()) {
                detail.setPayment(paymentFull.getPayment());
            }
            List<PaymentTransactionEntity> paymentTransactionEntities = IPaymentTransactionMapper.INSTANCE
                    .toFromPaymentTransactionDtoList(paymentFull.getPaymentTransactions());
            iPaymentTrasactionRepository.saveAll(paymentTransactionEntities);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void updatePayment(PaymentFullDto paymentFull) {
        try {
            // Extract Payment ID
            String paymentId = paymentFull.getPayment().getId();

            // Retrieve Payment and PaymentTransaction entities
            PaymentEntity paymentEntity = iPaymentRepository.findById(paymentId)
                    .orElseThrow(() -> new RuntimeException("Payment with id " + paymentId + " not found."));

            List<PaymentTransactionEntity> paymentTransactionEntities = iPaymentTrasactionRepository.findByPaymentId(paymentId);

            // Update PaymentTransactions from paymentFull
            List<PaymentTransactionDto> updatedPaymentTransactions = paymentFull.getPaymentTransactions();
            for (int i = 0; i < updatedPaymentTransactions.size(); i++) {
                PaymentTransactionDto updatedTransaction = updatedPaymentTransactions.get(i);
                PaymentTransactionEntity currentTransaction = paymentTransactionEntities.get(i);
                currentTransaction.setQuatity(updatedTransaction.getQuantity());
                currentTransaction.setPrice(updatedTransaction.getPrice());
                currentTransaction.setAmount(updatedTransaction.getAmount());
            }

            // Save updated PaymentTransactions
            iPaymentTrasactionRepository.saveAll(paymentTransactionEntities);

            // Update Payment details from paymentFull
            PaymentDto updatedPaymentDto = paymentFull.getPayment();
            paymentEntity.setCode(updatedPaymentDto.getCode());
            paymentEntity.setTotal(updatedPaymentDto.getTotal());
            paymentEntity.setStatus(updatedPaymentDto.getStatus());
            paymentEntity.setIdTypePayment(updatedPaymentDto.getTypePaymentReceiptDto().getName());
            paymentEntity.setNote(updatedPaymentDto.getNote());
            paymentEntity.setDateUpdated(LocalDateTime.now());

            // Save updated Payment
            iPaymentRepository.save(paymentEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public PaymentSearchDto getAllPaymentFull() {
        List<PaymentEntity> paymentEntities = this.iPaymentRepository.getAllPayment();
        List<PaymentDto> paymentDtos = IPaymentMapper.INSTANCE.toFromPaymentEntityList(paymentEntities);

        List<String> ids = paymentDtos.stream().map(PaymentDto::getId).collect(Collectors.toList());

        List<PaymentTransactionEntity> paymentTransactionEntities = this.iPaymentTrasactionRepository.getAllDetails(ids);
        List<PaymentTransactionDto> paymentTransactionDtos = IPaymentTransactionMapper.
                INSTANCE.toFromPaymentTransactionEntityList(paymentTransactionEntities);

        // duyệt qua từng hóa đơn đặt hàng
        List<PaymentFullDto> paymentFullDtos = new ArrayList<>();
        for (PaymentDto p : paymentDtos) {
            PaymentFullDto paymentFullDto = new PaymentFullDto();
            paymentFullDto.setPayment(p);
            //lấy hết tất cả chi tiết
            //List<PaymentTransactionDto> details = paymentTransactionDtos;
            List<PaymentTransactionDto> details = paymentTransactionDtos
                    .stream().filter(detail -> detail.getPayment().getId().equals(p.getId())).collect(Collectors.toList());
            paymentFullDto.setPaymentTransactions(details);
            paymentFullDtos.add(paymentFullDto);
        }

        PaymentSearchDto result = new PaymentSearchDto();
        result.setResult(paymentFullDtos);
        return result;
    }

    @Override
    @Transactional
    public void deletePaymentFullByid(String id) {
        try {
            // Xóa các PaymentTransaction trước
            List<PaymentTransactionEntity> paymentTransactions = iPaymentTrasactionRepository.findByPaymentId(id);
            iPaymentTrasactionRepository.deleteAll(paymentTransactions);

            // Sau đó xóa Payment
            iPaymentRepository.deleteById(id);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
