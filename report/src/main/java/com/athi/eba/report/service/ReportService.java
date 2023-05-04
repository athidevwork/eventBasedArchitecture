package com.athi.eba.report.service;

import com.athi.eba.report.model.Report;
import com.athi.eba.report.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Long id) {
        Optional<Report> optionalOrder = reportRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Transactional //(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Report createReport(Report report) {
        logger.debug("report in service = " + report);
        report.setCreatedAt(String.valueOf(Timestamp.from(Instant.now())));
        report.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));
        logger.debug("Report = " + report);
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}
