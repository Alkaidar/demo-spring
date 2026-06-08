package org.example.demospring.excel.fesod.write;

import org.apache.fesod.sheet.FesodSheet;
import org.example.demospring.excel.fesod.pojo.ImageDemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ImageTests {

    @Test
    public void imageWrite() throws Exception {
        String fileName = "imageWrite" + System.currentTimeMillis() + ".xlsx";
        String imagePath = "C:\\Users\\ybkf042\\Pictures\\Saved Pictures\\20260605-100843.jpg";

        List<ImageDemoData> list = new ArrayList<>();
        ImageDemoData data = new ImageDemoData();
        data.setFile(new File(imagePath));

        // 用try-with-resource会提前关闭，setInputStream只是设置了一个引用
        // try (InputStream inputStream = new FileInputStream(imagePath)) {
        //     data.setInputStream(inputStream);
        // }
        // try (InputStream inputStream = Files.newInputStream(Paths.get(imagePath))) {
        //     data.setInputStream(inputStream);
        // }

        // File file = new File(imagePath);
        // InputStream inputStream = new FileInputStream(file);
        InputStream inputStream = new FileInputStream(imagePath);
        data.setInputStream(inputStream);

        byte[] byteArray = Files.readAllBytes(Paths.get(imagePath));
        data.setByteArray(byteArray);

        // 这个没成功
        // String base64Image = Base64.getEncoder().encodeToString(byteArray);
        // data.setString(base64Image);

        data.setUrl(new URL("https://fesod.apache.org/zh-cn/assets/images/imgWrite-3ef200f147e8e4f0cf2bb83c82256ce9.png"));
        list.add(data);

        FesodSheet.write(fileName, ImageDemoData.class)
                .sheet()
                .doWrite(list);
    }

}
