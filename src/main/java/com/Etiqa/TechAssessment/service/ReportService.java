package com.Etiqa.TechAssessment.service;


import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.entity.Product;
import com.Etiqa.TechAssessment.repository.CustomerRepository;
import com.Etiqa.TechAssessment.repository.ProductRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("masterReport")
    private JasperReport masterReport;

    @Autowired
    @Qualifier("customerSubreport")
    private JasperReport customerSubreport;

    @Autowired
    @Qualifier("productSubreport")
    private JasperReport productSubreport;

    public ResponseEntity<byte[]> exportExcelReport() throws Exception{
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("totalCustomers", customers.size());
        parameters.put("totalProducts", products.size());
        parameters.put("SUBREPORT_CUSTOMERS", customerSubreport);
        parameters.put("SUBREPORT_PRODUCTS", productSubreport);
        parameters.put("customerList", customers);
        parameters.put("productList", products);

        JasperPrint filledReport = JasperFillManager.fillReport(masterReport, parameters, new JREmptyDataSource());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(filledReport));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        byte[] bytes = outputStream.toByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=CustomerProductReport.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }
}
