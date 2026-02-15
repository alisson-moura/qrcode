package com.amdev.qrcode.controller;

import com.amdev.qrcode.dto.ZplToPdfResponse;
import com.amdev.qrcode.service.ZplServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/zpl")
public class ZplController {

    private final ZplServices zplServices;

    public ZplController(ZplServices zplServices) {
        this.zplServices = zplServices;
    }

    @PostMapping(value = "convert/pdf",consumes = MediaType.TEXT_PLAIN_VALUE )
    public ResponseEntity<ZplToPdfResponse> convertToPdf(@RequestBody String request) {
        try {
            ZplToPdfResponse response = this.zplServices.convertToPdf(request);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
