package com.example.expensenest.service.impl;
import com.example.expensenest.entity.SalesReport;
import com.example.expensenest.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReportServiceImplTest {

    @Mock
    private ReportRepository mockReportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSalesReportData() {
        int sellerId = 1;
        String startDate = "2023-01-01";
        String endDate = "2023-01-31";

        // Mock the data returned by the repository
        List<SalesReport> mockSalesReports = new ArrayList<>();
        // Add some mock SalesReport objects to the list

        when(mockReportRepository.getSalesReportBySellerID(sellerId, startDate, endDate))
                .thenReturn(mockSalesReports);

        // Call the service method
        List<SalesReport> result = reportService.getSalesReportData(sellerId, startDate, endDate);

        // Assert the result
        assertEquals(mockSalesReports, result);
    }
}
