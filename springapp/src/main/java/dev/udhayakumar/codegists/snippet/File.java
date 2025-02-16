package dev.udhayakumar.codegists.snippet;

import org.bson.types.ObjectId;

public class File {
    private String fileId;
    private String fileName;
    private String fileContent;
    private String language;

    public File() {
        this.fileId = new ObjectId().toHexString();
    }

    public File(String fileName, String fileContent, String language) {
        this.fileId = new ObjectId().toHexString();
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.language = language;
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
