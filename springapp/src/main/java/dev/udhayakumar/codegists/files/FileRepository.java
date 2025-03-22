package dev.udhayakumar.codegists.files;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends MongoRepository<File, String> {
    List<File> findAllBySnippetId(String snippetId);
}
