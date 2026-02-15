package com.amdev.qrcode.service;

import com.amdev.qrcode.dto.ZplToPdfResponse;
import com.amdev.qrcode.ports.StoragePort;
import com.zpl2pdf.ZPLConfig;
import com.zpl2pdf.ZPLRenderer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class ZplServices {
    private final StoragePort storage;

    public ZplServices(StoragePort storageAdapter) {
        this.storage = storageAdapter;
    }

    public ZplToPdfResponse convertToPdf(String zpl) throws IOException {
        // Prepare Config (4x6 inch, 203 DPI)
        ZPLConfig config = ZPLConfig.default4x6();

        byte[] pdfBytes = ZPLRenderer.renderToPdfBytes(zpl, config);

        String pdfUrl = this.storage.uploadFile(
                pdfBytes,
                UUID.randomUUID().toString(),
                "pdf"
        );

        return new ZplToPdfResponse(pdfUrl);
    }
}
