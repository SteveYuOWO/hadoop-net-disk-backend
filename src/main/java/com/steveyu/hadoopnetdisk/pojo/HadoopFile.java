package com.steveyu.hadoopnetdisk.pojo;

public class HadoopFile {
    private String name, path;
    private Long size, lastModificationTime;

    public String getName() {
        return name;
    }

    public HadoopFile setName(String name) {
        this.name = name;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public HadoopFile setSize(Long size) {
        this.size = size;
        return this;
    }

    public Long getLastModificationTime() {
        return lastModificationTime;
    }

    public HadoopFile setLastModificationTime(Long lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
        return this;
    }

    public String getPath() {
        return path;
    }

    public HadoopFile setPath(String path) {
        this.path = path;
        return this;
    }
}
