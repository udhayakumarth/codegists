package dev.udhayakumar.codegists.snippets;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SnippetRepository extends MongoRepository<Snippet,String> {
    List<Snippet> findByUserName(String userName);

    Snippet findByUserNameAndSnippetId(String userName, String snippetId);

    Optional<Snippet> findBySnippetId(String snippetId);
}
