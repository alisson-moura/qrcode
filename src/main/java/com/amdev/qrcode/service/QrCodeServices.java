package com.amdev.qrcode.service;

import com.amdev.qrcode.dto.QrCodeGenerateResponse;
import com.amdev.qrcode.ports.StoragePort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeServices {
    private final StoragePort storage;

    public QrCodeServices(StoragePort storageAdapter) {
        this.storage = storageAdapter;
    }

    public QrCodeGenerateResponse generateAndUpload(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngQrCodeData = pngOutputStream.toByteArray();

        String url = this.storage.uploadFile(
                pngQrCodeData,
                UUID.randomUUID().toString(),
                "image/png"
        );

        return new QrCodeGenerateResponse(url);
    }
}
