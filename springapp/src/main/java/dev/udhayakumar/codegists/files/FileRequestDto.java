package dev.udhayakumar.codegists.files;

public class FileRequestDto {
    private String fileName;
    private String fileContent;
    private String language;

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

    @Override
    public String toString() {
        return "FileRequestDto{" +
                "fileName='" + fileName + '\'' +
                ", fileContent='" + fileContent + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
