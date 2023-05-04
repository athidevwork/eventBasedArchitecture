package com.athi.eba.report.controller;

import com.athi.eba.report.model.Report;
import com.athi.eba.report.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.athi.eba.event.EventUtil;
import org.athi.eba.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    ReportService reportService;

    @PostMapping("/createReport")
    public ResponseEntity<Report> createPayment(@RequestBody Report report) throws JsonProcessingException {
        Report reportCreated = reportService.createReport(report);
        logger.debug("In report controller" + reportCreated);

        Event event = Event.builder().eventName("Report Created").contextId(report.getReportId().toString())
                        .objectType("Report").payload(report.toString()).build();
        EventUtil.sendEvent(event);
        return ResponseEntity.ok(reportCreated);
    }
}