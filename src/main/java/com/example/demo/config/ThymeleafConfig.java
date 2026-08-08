package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        // กำหนดโฟลเดอร์ custom-templates แทน /templates/ ปกติ
        resolver.setPrefix("classpath:/custom-templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false); // ปิด Cache เพื่อให้แก้ไขไฟล์แล้วเห็นผลทันทีขณะพัฒนา
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(1); // ลำดับความสำคัญในการทำงาน
        return viewResolver;
    }
     // ============================================================
    // ต่อยอดข้อ 2: ViewResolver ตัวที่สอง (order 2) ชี้ไปโฟลเดอร์อื่น
    // (secondary-templates/) เพื่อสังเกตว่า Spring เลือก resolver ตัวไหนก่อน
    // ============================================================

    @Bean
    public SpringResourceTemplateResolver secondaryTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/secondary-templates/"); // โฟลเดอร์อื่นอีกอัน
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        resolver.setCheckExistence(true); // ให้ engine เช็คไฟล์จริงก่อนตัดสินใจ resolve
        resolver.setOrder(1); // ลำดับภายใน engine ตัวที่สองนี้ (มี resolver เดียว เลยเป็น 1)
        return resolver;
    }

    @Bean
    public SpringTemplateEngine secondaryTemplateEngine(SpringResourceTemplateResolver secondaryTemplateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(secondaryTemplateResolver);
        return engine;
    }

    @Bean
    public ThymeleafViewResolver secondaryViewResolver(SpringTemplateEngine secondaryTemplateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(secondaryTemplateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(2); // ตัวนี้ order สูงกว่า (=ความสำคัญต่ำกว่า) จะถูกลองหลัง viewResolver ตัวแรก
        return viewResolver;
    }
}