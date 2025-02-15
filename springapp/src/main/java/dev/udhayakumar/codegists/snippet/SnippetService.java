package dev.udhayakumar.codegists.snippet;

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

    public Snippet findSnippetById(String userName, String snippetId) {
        return snippetRepository.findByUserNameAndSnippetId(userName,snippetId);
    }
}
