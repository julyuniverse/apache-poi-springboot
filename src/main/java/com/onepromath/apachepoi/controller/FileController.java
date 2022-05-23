package com.onepromath.apachepoi.controller;

import com.onepromath.apachepoi.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/download/csv")
    public ResponseEntity<String> downloadCsv(@RequestParam("level") int level, @RequestParam("unitCount") int unitCount) {
        String data = fileService.downloadCsv(level, unitCount);

        LocalDateTime now = LocalDateTime.now();
        String formatNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=MS949");
        header.add("Content-Disposition", "attachment; filename=\"" + "level_" + level + "_unit_count_" + unitCount + "_" + formatNow + ".csv" + "\"");

        return new ResponseEntity<>(data, header, HttpStatus.CREATED);
    }
}
