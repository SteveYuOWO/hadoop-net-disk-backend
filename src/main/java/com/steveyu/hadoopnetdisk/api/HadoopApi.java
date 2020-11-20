package com.steveyu.hadoopnetdisk.api;

import com.steveyu.hadoopnetdisk.pojo.HadoopFile;
import com.steveyu.hadoopnetdisk.service.HadoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("hadoop")
public class HadoopApi {
    @Autowired
    HadoopService hadoopService;

    @PostMapping("upload/{courseId}/{filename}")
    public String upload(@RequestParam("file") MultipartFile file,
                         @PathVariable("courseId") String courseId,
                         @PathVariable("filename") String filename) {
        return hadoopService.upload(file, "/" + courseId + "/" + filename);
    }

    @GetMapping("download")
    public ResponseEntity<FileSystemResource> download(@RequestParam("path") String path) {
        return hadoopService.download(path);
    }
    @GetMapping("list")
    public List<HadoopFile> listFiles(@RequestParam("path") String path) {
        return hadoopService.listFiles(path);
    }

    @DeleteMapping
    public String deleteFile(@RequestParam("path") String path) {
        return hadoopService.deleteFile(path);
    }
}
