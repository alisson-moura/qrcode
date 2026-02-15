package com.amdev.qrcode.controller;

import com.amdev.qrcode.dto.QrCodeGenerateRequest;
import com.amdev.qrcode.dto.QrCodeGenerateResponse;
import com.amdev.qrcode.service.QrCodeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeServices qrCodeServices;

    public QrCodeController(QrCodeServices qrCodeServices) {
        this.qrCodeServices = qrCodeServices;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request) {
        try {
            QrCodeGenerateResponse response = this.qrCodeServices.generateAndUpload(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
