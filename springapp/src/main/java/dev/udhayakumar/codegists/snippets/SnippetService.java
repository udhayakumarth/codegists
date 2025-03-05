package dev.udhayakumar.codegists.snippets;

import dev.udhayakumar.codegists.versions.FileVersion;
import dev.udhayakumar.codegists.versions.SnippetVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetService {

    @Autowired
    SnippetRepository snippetRepository;

    public String saveSnippet(Snippet snippet) {
        return snippetRepository.save(snippet).getSnippetId();
    }

    public List<Snippet> findSnippet(String userName) {
        return snippetRepository.findByUserName(userName);
    }

    public Snippet findSnippetById(String snippetId) {
        return snippetRepository.findBySnippetId(snippetId);
    }

    public String editSnippet(SnippetVersion snippetVersion) {
        Snippet snippet = snippetRepository.findBySnippetId(snippetVersion.getSnippetId());

        if(snippetVersion.getDescription() != null)
            snippet.setDescription(snippetVersion.getDescription());
        if(snippetVersion.getPublic() != null)
            snippet.setPublic(snippetVersion.getPublic());
        if(snippetVersion.getFiles() != null){
            for (FileVersion fileVersion: snippetVersion.getFiles()){
                switch (fileVersion.getType()) {
                    case "new" -> {
                        File file = new File(
                                fileVersion.getFileName(),
                                fileVersion.getFileContent(),
                                fileVersion.getLanguage()
                        );
                        snippet.addFile(file);
                    }
                    case "update" -> {
                        File file = snippet.getFileById(fileVersion.getFileId());
                        int fileIndex = snippet.getFileIndexByFileId(fileVersion.getFileId());
                        file.setFileName(fileVersion.getFileName());
                        file.setFileContent(fileVersion.getFileContent());
                        file.setLanguage(fileVersion.getLanguage());
                        snippet.getFiles().set(fileIndex,file);
                    }
                    case "delete" -> {
                        int fileIndex = snippet.getFileIndexByFileId(fileVersion.getFileId());
                        snippet.getFiles().remove(fileIndex);
                    }
                }
            }
        }
        snippetRepository.save(snippet);
        return snippetRepository.save(snippet).getSnippetId();
    }

    public void deleteSnippet(String snippetId) {
        snippetRepository.delete(findSnippetById(snippetId));
    }
}
