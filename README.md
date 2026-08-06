# Lab: Custom ViewResolver ใน Spring Boot + Thymeleaf

**วิชา:** CP353002 Principles of Software Design  
**หัวข้อ:** การกำหนด (configure) ViewResolver ของ Spring Boot ที่ใช้ Thymeleaf เป็น View Engine  

---

## 🎯 วัตถุประสงค์ของแล็ป
ศึกษาวิธีกำหนด ViewResolver ของ Spring Boot + Thymeleaf โดยเปลี่ยนโฟลเดอร์เก็บ Template จากค่าเริ่มต้น (`/src/main/resources/templates/`) ไปยังโฟลเดอร์ที่กำหนดเอง (`/src/main/resources/custom-templates/`) 

## 🏗️ โครงสร้างโปรเจกต์
```text
spring-thymeleaf-demo/
 ├── pom.xml
 ├── .gitignore
 ├── README.md
 └── src/main/
      ├── java/com/example/demo/
      │    ├── DemoApplication.java
      │    ├── config/ThymeleafConfig.java
      │    └── controller/HomeController.java
      └── resources/
           ├── application.properties
           └── custom-templates/
                └── home.html

                🧠 สรุปหลักการ (Principles of Software Design)
โปรเจกต์นี้สะท้อนหลักการ Separation of Concerns และ Dependency Inversion ใน MVC:

Controller: ไม่จำเป็นต้องรู้ Path จริงของไฟล์ HTML บน Disk รู้เพียงแค่ Logical View Name เช่น "home"

ViewResolver: ทำหน้าที่แปลง Logical View Name ไปเป็น Path ไฟล์จริง (classpath:/custom-templates/home.html)

ประโยชน์: หากมีการเปลี่ยน Location ของ Template หรือสลับ Framework ตัว Controller ก็ไม่จำเป็นต้องแก้ไขโค้ดเลย

🚀 วิธีการรันโปรเจกต์
รันโปรเจกต์ด้วยคำสั่ง:

Bash
mvn spring-boot:run
เปิด Browser ไปที่: http://localhost:8080/


> **📌 หลังจากสร้างไฟล์ `README.md` เสร็จแล้วอย่าลืม Push ขึ้น GitHub นะครับ:**
> ```bash
> git add README.md
> git commit -m "Add README.md"
> git push
> ```

---

## 📄 2. สรุปแนวทางการตอบคำถามใน `ViewResolver_Quiz.docx`

ในไฟล์ Word ข้อสอบ จะมีคำถามหลักสำคัญที่คุณต้องตอบ สามารถสรุปคำตอบไปกรอกได้ดังนี้ครับ:

### ❓ คำถาม: ใครเป็นผู้เรียกใช้ ViewResolver นี้ และเรียกเมื่อไร?
* **ผู้เรียกใช้:** `DispatcherServlet` (ซึ่งเป็น Front Controller หลักของ Spring MVC)
* **เรียกเมื่อไร:** 
  1. เมื่อมี HTTP Request เข้ามาที่ระบบ และ `DispatcherServlet` ส่งต่อไปยัง Controller (`HomeController`)
  2. เมื่อ `HomeController` ทำงานเสร็จและ Return ค่า **Logical View Name** (คือ String `"home"`) กลับมา
  3. `DispatcherServlet` จะนำ Logical View Name นั้นมาส่งต่อให้ `ViewResolver` (`ThymeleafViewResolver`) เพื่อทำการ Map เข้ากับ Prefix (`classpath:/custom-templates/`) และ Suffix (`.html`) จนได้ Path ไฟล์ HTML จริงเพื่อทำการ Render ส่งกลับไปให้ผู้ใช้

### 📸 สิ่งที่ต้องแนบในไฟล์ Word:
1. **ภาพ Screenshot หน้าจอ Browser:** ขณะเปิดไปที่ `http://localhost:8080/` แล้วเห็นข้อความ *"Hello from Thymeleaf with a custom ViewResolver!"*
2. **ภาพ Screenshot โค้ด:** หน้าจอ VS Code ที่เห็นโครงสร้างโฟลเดอร์ `custom-templates/home.html` และโค้ดใน `ThymeleafConfig.java`
3. **ลิงก์ GitHub Repo:** แปะลิงก์ GitHub ของคุณไว้ในเอกสารด้วยครับ

---