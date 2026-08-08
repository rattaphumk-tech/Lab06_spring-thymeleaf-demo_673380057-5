package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello from Thymeleaf with a custom ViewResolver!");
        return "home"; // Logical View Name ("home")
    }
     // ต่อยอดข้อ 1: view name ใหม่ "about" -> จะถูก ViewResolver ตัวเดิม (order 1)
    // แปลงเป็น classpath:/custom-templates/about.html โดยอัตโนมัติ
    // แสดงว่า path resolution เดิมรองรับได้หลาย view โดยไม่ต้องแก้ ThymeleafConfig เลย
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("message", "สวัสดีครับ ผมชื่อ Rattaphum Kerdprajeen รหัสนักศึกษา 673380057-5");
        return "about";
    }

    // ต่อยอดข้อ 2: ไม่มีไฟล์ "secret.html" อยู่ใน custom-templates/ เลย
    // ดังนั้น viewResolver ตัวแรก (order 1) จะหาไม่เจอแล้วปล่อยผ่าน (เพราะ checkExistence=true)
    // Spring จะไปลอง secondaryViewResolver (order 2) ต่อ ซึ่งไปเจอไฟล์ที่ secondary-templates/secret.html
    @GetMapping("/secret")
    public String secret(Model model) {
        model.addAttribute("message", "Resolved by the SECOND ViewResolver (order 2)!");
        return "secret";
    }
}
