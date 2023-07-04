package com.example.expensenest.service.impl;

import com.example.expensenest.entity.Invoice;
import com.example.expensenest.repository.InvoiceRepository;
import com.example.expensenest.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    public InvoiceServiceImpl (InvoiceRepository invoiceRepository) {
        super();
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getUserInvoices(int userId) {
        return invoiceRepository.findAllUserInvoices(userId);
    }

    @Override
    public List<Invoice> getFilteredInvoices(int userId, String searchString) {
        return invoiceRepository.getAllUserFilteredInvoices(userId, searchString);
    }
}
