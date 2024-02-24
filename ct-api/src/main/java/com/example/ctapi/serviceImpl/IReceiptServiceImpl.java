package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.*;
import com.example.ctapi.mappers.IReceiptMapper;
import com.example.ctapi.mappers.IReceiptTransactionMapper;
import com.example.ctapi.mappers.ITypePaymentReceiptMapper;
import com.example.ctapi.services.IReceiptService;
import com.example.ctcommon.enums.ReceiptStatus;
import com.example.ctcommondal.entity.PaymentTransactionEntity;
import com.example.ctcommondal.entity.ReceiptEntity;
import com.example.ctcommondal.entity.ReceiptTransactionEntity;
import com.example.ctcommondal.entity.TypePaymentReceiptEntity;
import com.example.ctcommondal.repository.IReceiptRepository;
import com.example.ctcommondal.repository.IReceiptTransactionRepository;
import com.example.ctcommondal.repository.ITypePaymentReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IReceiptServiceImpl implements IReceiptService {
    private final Logger logger = LoggerFactory.getLogger(IReceiptServiceImpl.class);
    private final IReceiptRepository iReceiptRepository;
    private final IReceiptTransactionRepository iReceiptTransactionRepository;
    private final ITypePaymentReceiptRepository iTypePaymentReceiptRepository;

    @Override
    public void createReceipt(ReceiptFullDto receiptFull) {
        try {
            int a = 0;
            //set status pament
            receiptFull.getReceipt().setStatus(ReceiptStatus.UNCOMPLETE);
            //mapper từ ReceiptDto sang entity xong lưu
            ReceiptEntity receiptEntity = IReceiptMapper.INSTANCE.toFromReceiptDto(receiptFull.getReceipt());
            iReceiptRepository.save(receiptEntity);

            //duyện qua vòng lặp
            for (ReceiptTransactionDto detail : receiptFull.getReceiptTransaction()) {
                detail.setReceipt(receiptFull.getReceipt());
            }
            List<ReceiptTransactionEntity> receiptTransactionEntities = IReceiptTransactionMapper.INSTANCE
                    .toFromReceiptTransactionDtoList(receiptFull.getReceiptTransaction());
            iReceiptTransactionRepository.saveAll(receiptTransactionEntities);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void updateReceipt(ReceiptFullDto receiptFull) {
        try {
            String receiptId = receiptFull.getReceipt().getId();

            // Xóa các PaymentTransaction trước
            List<ReceiptTransactionEntity> receiptTransactionEntities = iReceiptTransactionRepository.findByReceiptId(receiptId);
            iReceiptTransactionRepository.deleteAll(receiptTransactionEntities);

            // Cập nhật thông tin của receipt
            ReceiptEntity receiptEntity = iReceiptRepository.findById(receiptId)
                    .orElseThrow(() -> new RuntimeException("Receipt with id " + receiptId + " not found."));

            // Cập nhật thông tin của Payment từ PaymentDto
            ReceiptDto updatedReceiptDto = receiptFull.getReceipt();
            receiptEntity.setCode(updatedReceiptDto.getCode());
            receiptEntity.setTotal(updatedReceiptDto.getTotal());
            receiptEntity.setIdTypeReceipt(updatedReceiptDto.getTypePaymentReceipt().getId());
            receiptEntity.setNote(updatedReceiptDto.getNote());
            receiptEntity.setDateUpdated(LocalDateTime.now());

            // Lưu lại thông tin receipt đã cập nhật
            iReceiptRepository.save(receiptEntity);

            // Mapper từ importingTransactionDto sang ImportingTransactionEntity và lưu vào cơ sở dữ liệu
            List<ReceiptTransactionEntity> receiptTransactionUpdate = IReceiptTransactionMapper
                    .INSTANCE.toFromReceiptTransactionDtoList(receiptFull.getReceiptTransaction());
            // Cập nhật lại receipt cho các receipttransaction
            for (ReceiptTransactionEntity transactionEntity : receiptTransactionUpdate) {
                transactionEntity.setIdReceipt(receiptId);
            }

            // Lưu lại thông tin các PaymentTransactionEntity đã cập nhật
            iReceiptTransactionRepository.saveAll(receiptTransactionUpdate);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void deleteReceiptFullByid(String id) {
        try {
            List<ReceiptTransactionEntity> receiptTransactionEntities = iReceiptTransactionRepository.findByReceiptId(id);
            iReceiptTransactionRepository.deleteAll(receiptTransactionEntities);
            // Sau đó xóa receipt
            iReceiptRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public ReceiptSearchDto getAllReceiptFull() {
        List<ReceiptEntity> receiptEntityList = this.iReceiptRepository.getAllReceipt();
        List<ReceiptDto> receiptDtosList = IReceiptMapper.INSTANCE.toFromReceiptEntityList(receiptEntityList);

        for (ReceiptDto r : receiptDtosList) {
            TypePaymentReceiptEntity typePaymentReceiptEntity = iTypePaymentReceiptRepository
                    .findTypePaymentReceiptById(r.getTypePaymentReceipt().getId());
            TypePaymentReceiptDto typePaymentReceiptDto = ITypePaymentReceiptMapper.INSTANCE.toFromTypePaymentReceiptEntity(typePaymentReceiptEntity);
            r.setTypePaymentReceipt(typePaymentReceiptDto);
        }

        List<String> ids = receiptDtosList.stream().map(ReceiptDto::getId).collect(Collectors.toList());

        List<ReceiptTransactionEntity> receiptTransactionEntityList = this.iReceiptTransactionRepository.getAllDetails(ids);
        List<ReceiptTransactionDto> receiptTransactionDtos = IReceiptTransactionMapper.
                INSTANCE.toFromReceiptTransactionEntityList(receiptTransactionEntityList);

        // duyệt qua từng hóa đơn đặt hàng
        List<ReceiptFullDto> receiptFullDtos = new ArrayList<>();
        for (ReceiptDto p : receiptDtosList) {
            ReceiptFullDto receiptFullDto = new ReceiptFullDto();
            receiptFullDto.setReceipt(p);
            //lấy hết tất cả chi tiết

            List<ReceiptTransactionDto> details = receiptTransactionDtos
                    .stream()
                    .filter(detail -> p.getId().equals(detail.getReceipt().getId()))
                    .collect(Collectors.toList());

            receiptFullDto.setReceiptTransaction(details);
            receiptFullDtos.add(receiptFullDto);
        }
        ReceiptSearchDto result = new ReceiptSearchDto();
        result.setResult(receiptFullDtos);
        return result;
    }

    @Transactional
    @Override
    public ReceiptFullDto getReceiptById(String id) {
        try {
            ReceiptEntity receiptEntity = iReceiptRepository.findReceiptById(id);
            ReceiptDto receiptDto = IReceiptMapper.INSTANCE.toFromReceiptEntity(receiptEntity);

            TypePaymentReceiptEntity typePaymentReceiptEntity = iTypePaymentReceiptRepository
                    .findTypePaymentReceiptById(receiptDto.getTypePaymentReceipt().getId());
            TypePaymentReceiptDto typePaymentReceiptDto = ITypePaymentReceiptMapper.INSTANCE.toFromTypePaymentReceiptEntity(typePaymentReceiptEntity);
            receiptDto.setTypePaymentReceipt(typePaymentReceiptDto);

            List<ReceiptTransactionEntity> receiptTransactionEntities = iReceiptTransactionRepository.findByReceiptId(id);
            List<ReceiptTransactionDto> receiptTransactionDtos = IReceiptTransactionMapper.INSTANCE.toFromReceiptTransactionEntityList(receiptTransactionEntities);

            ReceiptFullDto receiptFullDto = new ReceiptFullDto();
            receiptFullDto.setReceipt(receiptDto);
            receiptFullDto.setReceiptTransaction(receiptTransactionDtos);
            return receiptFullDto;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}

