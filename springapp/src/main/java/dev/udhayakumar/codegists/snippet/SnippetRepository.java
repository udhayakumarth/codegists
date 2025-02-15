package dev.udhayakumar.codegists.snippet;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SnippetRepository extends MongoRepository<Snippet,String> {
    List<Snippet> findByUserName(String userName);

    Snippet findByUserNameAndSnippetId(String userName, String snippetId);
}
