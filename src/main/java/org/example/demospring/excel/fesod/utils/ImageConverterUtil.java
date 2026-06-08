package org.example.demospring.excel.fesod.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;

/**
 * @ClassName ImageConverterUtil
 * @Description 图片格式转换工具类
 * @Author wangzhipeng
 * @Date 2026/6/8 17:00
 */
public class ImageConverterUtil {

    // File → InputStream
    public static InputStream fileToInputStream(File file) throws Exception {
        return Files.newInputStream(file.toPath());
    }

    // File → byte[]
    public static byte[] fileToByteArray(File file) throws Exception {
        return Files.readAllBytes(file.toPath());
    }

    // File → Base64字符串
    public static String fileToBase64(File file) throws Exception {
        return Base64.getEncoder().encodeToString(fileToByteArray(file));
    }

    // byte[] → InputStream
    public static InputStream byteArrayToInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    // byte[] → Base64字符串
    public static String byteArrayToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    // InputStream → byte[]
    // public static byte[] inputStreamToByteArray(InputStream inputStream) throws Exception {
    //     return inputStream.readAllBytes(); // Java 9+
    // }

    // InputStream → Base64字符串
    // public static String inputStreamToBase64(InputStream inputStream) throws Exception {
    //     return Base64.getEncoder().encodeToString(inputStreamToByteArray(inputStream));
    // }

    // URL → InputStream
    public static InputStream urlToInputStream(URL url) throws Exception {
        return url.openStream();
    }

    // URL → byte[]
    // public static byte[] urlToByteArray(URL url) throws Exception {
    //     try (InputStream inputStream = url.openStream()) {
    //         return inputStreamToByteArray(inputStream);
    //     }
    // }

    // URL → Base64字符串
    // public static String urlToBase64(URL url) throws Exception {
    //     return Base64.getEncoder().encodeToString(urlToByteArray(url));
    // }
}