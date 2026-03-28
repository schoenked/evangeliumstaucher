package de.evangeliumstaucher.app.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class JsonCompressor {

    public static String compress(String json) {
        if (json == null || json.isEmpty()) {
            return json;
        }

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // try-with-resources schließt den GZIPOutputStream automatisch und schreibt den Trailer
            try (GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
                gzip.write(json.getBytes(StandardCharsets.UTF_8));
            }

            // Erst NACH dem Schließen des gzips holen wir uns das finale Byte-Array
            byte[] compressedBytes = bos.toByteArray();
            return Base64.getEncoder().encodeToString(compressedBytes);

        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Komprimieren des JSON-Strings", e);
        }
    }

    public static String decompress(String compressedBase64) {
        if (compressedBase64 == null || compressedBase64.isEmpty()) {
            return compressedBase64;
        }

        try {
            byte[] compressedBytes = Base64.getDecoder().decode(compressedBase64);

            try (ByteArrayInputStream bis = new ByteArrayInputStream(compressedBytes);
                 GZIPInputStream gis = new GZIPInputStream(bis)) {

                return new String(gis.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Entpacken des Base64-Strings", e);
        }
    }
}