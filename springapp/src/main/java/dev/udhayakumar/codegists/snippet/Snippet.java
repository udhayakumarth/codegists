package dev.udhayakumar.codegists.snippet;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "snippet")
public class Snippet {
    @Id
    private String snippetId;
    private String description;
    private Boolean isPublic;
    private List<File> files;
    private String userName;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    public Snippet(String snippetId, String description, Boolean isPublic, String userName) {
        this.snippetId = snippetId;
        this.description = description;
        this.isPublic = isPublic;
        this.userName = userName;
    }

    public String getSnippetId() {
        return snippetId;
    }

    public void setSnippetId(String snippetId) {
        this.snippetId = snippetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addFile(File file){
        files.add(file);
    }

    public File getFileById(String fileId) {
        return files.stream()
                .filter(file -> file.getFileId().equals(fileId))
                .findFirst()
                .orElse(null); // Return null if not found
    }

    public int getFileIndexByFileId(String fileId){
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getFileId().equals(fileId)) {
                return i;
            }
        }
        return -1;
    }
}
