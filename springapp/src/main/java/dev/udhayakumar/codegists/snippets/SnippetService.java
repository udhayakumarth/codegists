package dev.udhayakumar.codegists.snippets;

import dev.udhayakumar.codegists.versions.FileVersion;
import dev.udhayakumar.codegists.versions.SnippetVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Snippet> findSnippetById(String snippetId) {
        return snippetRepository.findBySnippetId(snippetId);
    }

    public String editSnippet(SnippetVersion snippetVersion){
        try{
            Optional<Snippet>  optionalSnippet = snippetRepository.findBySnippetId(snippetVersion.getSnippetId());
            if(optionalSnippet.isPresent()){
                Snippet snippet = optionalSnippet.get();
                if(snippetVersion.getDescription() != null)
                    snippet.setDescription(snippetVersion.getDescription());
                if(snippetVersion.getPublic() != null)
                    snippet.setPublic(snippetVersion.getPublic());

                snippetRepository.save(snippet);
                return snippetRepository.save(snippet).getSnippetId();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSnippet(String snippetId) {
        Optional<Snippet> snippet = findSnippetById(snippetId);
        snippet.ifPresent(value -> snippetRepository.delete(value));

    }
}
