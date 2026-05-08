package com.deeppatel.portfolio.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadResume() {
        // Place the actual resume.pdf in backend/src/main/resources/static/resume.pdf
        Resource resource = new ClassPathResource("static/resume.pdf");
        
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Deep_Patel_Resume.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
