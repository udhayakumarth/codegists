package dev.udhayakumar.codegists.files;

import java.time.Instant;
import java.util.Date;

public class FileResponseDto {
    private String fileId;
    private String fileName;
    private String fileContent;
    private String language;
    private Instant createdAt;
    private Instant updatedAt;

    public FileResponseDto(String fileId, String fileName, String fileContent, String language, Instant createdAt, Instant updatedAt) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
