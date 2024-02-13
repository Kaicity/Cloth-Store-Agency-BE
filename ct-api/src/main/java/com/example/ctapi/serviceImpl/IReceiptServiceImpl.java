package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.ReceiptDto;
import com.example.ctapi.dtos.response.ReceiptFullDto;
import com.example.ctapi.dtos.response.ReceiptSearchDto;
import com.example.ctapi.dtos.response.ReceiptTransactionDto;
import com.example.ctapi.mappers.IReceiptMapper;
import com.example.ctapi.mappers.IReceiptTransactionMapper;
import com.example.ctapi.services.IReceiptService;
import com.example.ctcommon.enums.ReceiptStatus;
import com.example.ctcommondal.entity.ReceiptEntity;
import com.example.ctcommondal.entity.ReceiptTransactionEntity;
import com.example.ctcommondal.repository.IReceiptRepository;
import com.example.ctcommondal.repository.IReceiptTransactionRepository;
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

    @Override
    public void updateReceipt(ReceiptFullDto receiptFull) {
        try {
            // Extract Receipt ID
            String receiptId = receiptFull.getReceipt().getId();

            // Retrieve Receipt and ReceiptTransaction entities
            ReceiptEntity receiptEntity = iReceiptRepository.findById(receiptId)
                    .orElseThrow(() -> new RuntimeException("Receipt with id " + receiptId + " not found."));

            List<ReceiptTransactionEntity> receiptTransactionEntities = iReceiptTransactionRepository.findByReceiptId(receiptId);

            // Update ReceiptTransactions from updatedReceiptFullDto
            List<ReceiptTransactionDto> receiptTransactionDtos = receiptFull.getReceiptTransaction();
            for (int i = 0; i < receiptTransactionDtos.size(); i++) {
                ReceiptTransactionDto updatedTransaction = receiptTransactionDtos.get(i);
                ReceiptTransactionEntity currentTransaction = receiptTransactionEntities.get(i);
                currentTransaction.setQuatity(updatedTransaction.getQuantity());
                currentTransaction.setPrice(updatedTransaction.getPrice());
                currentTransaction.setAmount(updatedTransaction.getAmount());
            }

            // Save updated ReceiptTransactions
            iReceiptTransactionRepository.saveAll(receiptTransactionEntities);

            // Update Receipt details from updatedReceiptFullDto
            ReceiptDto updatedReceiptDto = receiptFull.getReceipt();
            receiptEntity.setCode(updatedReceiptDto.getCode());
            receiptEntity.setTotal(updatedReceiptDto.getTotal());
            receiptEntity.setStatus(updatedReceiptDto.getStatus());
            receiptEntity.setIdTypeReceipt(updatedReceiptDto.getTypePaymentReceipt().getName());
            receiptEntity.setNote(updatedReceiptDto.getNote());
            receiptEntity.setDateUpdated(LocalDateTime.now());

            // Save updated Receipt
            iReceiptRepository.save(receiptEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error updating ReceiptFullDto: " + e.getMessage());
        }

    }

    @Override
    public void deleteReceiptFullByid(String id) {
        try {
            // Xóa các Receipt Transaction trước
            List<ReceiptTransactionEntity> receiptTransactions = iReceiptTransactionRepository.findByReceiptId(id);
            iReceiptTransactionRepository.deleteAll(receiptTransactions);

            // Sau đó xóa Receipt
            iReceiptRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new RuntimeException("Error deleting ReceiptFullDto: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ReceiptSearchDto getAllReceiptFull() {
        int a = 0;
        List<ReceiptEntity> receiptEntityList = this.iReceiptRepository.getAllReceipt();
        List<ReceiptDto> receiptDtosList = IReceiptMapper.INSTANCE.toFromReceiptEntityList(receiptEntityList);

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
}

