package com.steveyu.hadoopnetdisk.service;

import com.steveyu.hadoopnetdisk.pojo.HadoopFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HadoopService {
    private Configuration conf = null;
    private FileSystem fs = null;

    public HadoopService() {
        init();
    }

    /**
     * init user's params
     */
    private void init() {
        try {
            conf=new Configuration(true);
            conf.set("dfs.client.use.datanode.hostname", "true");
            fs=FileSystem.get(URI.create("hdfs://node01:9000/"), conf,"root");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String upload(MultipartFile file, String dest) {
        try {
            InputStream is= new BufferedInputStream(file.getInputStream());
            Path path=new Path(dest);
            FSDataOutputStream outFile = fs.create(path);
            IOUtils.copyBytes(is, outFile, conf,true);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "上传成功";
    }

    private final static String serverBaseDir = "src/main/resources/upload/";

    public ResponseEntity<FileSystemResource> download(String path) {
        // get local dir uri
        String[] splits = path.split("/");
        String filename = splits[splits.length - 1];
        String localDir = serverBaseDir + filename;

        // download from hdfs
        try {
            System.out.println(path);
            System.out.println(localDir);
            fs.copyToLocalFile(new Path(path), new Path(localDir));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // local file return
        File retFile = new File(localDir);
        if (retFile == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(retFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(retFile));
    }

    public List<HadoopFile> listFiles(String path) {
        List<HadoopFile> li = new ArrayList<>();
        try {
            RemoteIterator<LocatedFileStatus> it = fs.listFiles(new Path(path), false);
            List<LocatedFileStatus> lfList = new ArrayList<>();
            while(it.hasNext()) {
                LocatedFileStatus next = it.next();
                li.add(new HadoopFile()
                        .setName(next.getPath().getName())
                        .setSize(next.getLen())
                        .setLastModificationTime(next.getModificationTime())
                        .setPath(next.getPath().toString())
                        );
            }
            return li;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String path) {
        try {
            fs.delete(new Path(path), true);
        } catch (IOException e) {
            return "删除失败";
        }
        return "删除成功";
    }
}
