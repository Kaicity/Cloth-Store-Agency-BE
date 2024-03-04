package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.*;
import com.example.ctapi.mappers.IExportingbillMapper;
import com.example.ctapi.mappers.IExportingbillTransactionMapper;
import com.example.ctapi.mappers.IImportingReturnBIllTransactionMapper;
import com.example.ctapi.mappers.IImportingReturnBillMapper;
import com.example.ctapi.services.IExportingReturnBillService;
import com.example.ctapi.services.IImportingReturnService;
import com.example.ctcommon.enums.BillStatus;
import com.example.ctcommon.enums.TypeBillRealTime;
import com.example.ctcommondal.entity.ExportbillEntity;
import com.example.ctcommondal.entity.ExportingBillTransactionEntity;
import com.example.ctcommondal.entity.ImportingBillReturnTransactionEntity;
import com.example.ctcommondal.entity.ImportingReturnbillEntity;
import com.example.ctcommondal.repository.IExportingTransactionRepository;
import com.example.ctcommondal.repository.IExportingbillRepository;
import com.example.ctcommondal.repository.IImportingReturnBIllRepository;
import com.example.ctcommondal.repository.IImportingReturnTransactionRepository;
import com.example.ctcoremodel.CustomerModel;
import com.example.ctcoreservice.services.IWarehouseRequestService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IImportingReturnBillServiceImpl implements IImportingReturnService {
    private final IImportingReturnBIllRepository iImportingReturnBIllRepository;
    private final IImportingReturnTransactionRepository iImportingReturnTransactionRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final IWarehouseRequestService warehouseRequestService;
    private final Logger logger = LoggerFactory.getLogger(IExportingServiceImpl.class);

    @Override
    public void createImportingReturnbill(ImportingReturnBillFullDto importingReturnBIll) {
        try {
            // thêm vào thông tin chung của đơn hàng
            importingReturnBIll.getImportbill().setStatus(BillStatus.BOOKING);

            ImportingReturnBIllDto tempEx = importingReturnBIll.getImportbill();
            if (tempEx.getCustomer() == null) {

                CustomerModel customer = new CustomerModel();
                tempEx.setCustomer(customer);
            }
            ImportingReturnbillEntity importreturnbillEntity = IImportingReturnBillMapper.INSTANCE.toFromExportingReturnbillEntity(importingReturnBIll.getImportbill());
            iImportingReturnBIllRepository.save(importreturnbillEntity);
            // thêm vào chi tiet don hang
            for (ImportingReturnBillTransactionDto detail : importingReturnBIll.getImportingTransactions()) {
                detail.setBill(importingReturnBIll.getImportbill());
            }

            List<ImportingBillReturnTransactionEntity> importingReturnBillTransactionEntity = IImportingReturnBIllTransactionMapper.
                    INSTANCE.toFromImportingReturnbillTransactionsDto(importingReturnBIll.getImportingTransactions());

            for (ImportingBillReturnTransactionEntity detail : importingReturnBillTransactionEntity) {
                iImportingReturnTransactionRepository.save(detail);
            }

            messagingTemplate.convertAndSend("/topic/" + "billRealTimeSection",
                    new SocketMessage("billRealTimeSection", TypeBillRealTime.BOOKING.toString(), importingReturnBIll));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
