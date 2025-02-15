package dev.udhayakumar.codegists.snippet;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class File {
    @Id
    private String fileId;
    private String fileName;
    private String fileContent;
    private String language;

    public File(String fileId) {
        this.fileId = new ObjectId().toHexString();
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
}
