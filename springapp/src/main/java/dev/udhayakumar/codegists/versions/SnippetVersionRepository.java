package dev.udhayakumar.codegists.versions;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnippetVersionRepository extends MongoRepository<SnippetVersion, String> {
    List<SnippetVersion> findAllBySnippetId(String snippetId);

    SnippetVersion findByVersionId(String versionId);
}
