package com.Etiqa.TechAssessment.controller;


import com.Etiqa.TechAssessment.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/excel")
    public ResponseEntity<byte[]> downloadExcelReport() throws Exception{
        return reportService.exportExcelReport();
    }

}
