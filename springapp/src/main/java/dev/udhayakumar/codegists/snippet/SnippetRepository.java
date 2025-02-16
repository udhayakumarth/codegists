package dev.udhayakumar.codegists.snippet;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnippetRepository extends MongoRepository<Snippet,String> {
    List<Snippet> findByUserName(String userName);

    Snippet findByUserNameAndSnippetId(String userName, String snippetId);

    Snippet findBySnippetId(String snippetId);
}
