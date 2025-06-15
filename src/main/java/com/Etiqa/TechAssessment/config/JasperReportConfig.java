package com.Etiqa.TechAssessment.config;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasperReportConfig {

    @Bean(name = "customerSubreport")
    public JasperReport customerSubreport() throws Exception {
        return JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/jasper/customer_subreport.jrxml"));
    }

    @Bean(name = "productSubreport")
    public JasperReport productSubreport() throws Exception {
        return JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/jasper/product_subreport.jrxml"));
    }

    @Bean(name = "masterReport")
    public JasperReport masterReport() throws Exception {
        return JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/jasper/customer_product_excel_report.jrxml"));
    }
}
