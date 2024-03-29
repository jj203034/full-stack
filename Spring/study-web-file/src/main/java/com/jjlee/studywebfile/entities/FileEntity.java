package com.jjlee.studywebfile.entities;

import java.util.Date;
import java.util.Objects;

public class FileEntity {
    private int index;
    private Date createdAt;
    private String clientIp;
    private String clientUa;
    private String name;
    private int size;
    private String contentType;
    private byte[] data;

    public int getIndex() {
        return index;
    }

    public FileEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public FileEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public FileEntity setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getClientUa() {
        return clientUa;
    }

    public FileEntity setClientUa(String clientUa) {
        this.clientUa = clientUa;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getSize() {
        return size;
    }

    public FileEntity setSize(int size) {
        this.size = size;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public FileEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public FileEntity setData(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
