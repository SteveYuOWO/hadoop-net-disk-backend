package com.steveyu.hadoopnetdisk;

import com.steveyu.hadoopnetdisk.service.HadoopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HadoopTest {
    @Autowired
    HadoopService hadoopService;
    @Test
    public void testDownload() {
        hadoopService.download("/1/可行性分析.docx");
    }
}
