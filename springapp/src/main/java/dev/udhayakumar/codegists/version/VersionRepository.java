package dev.udhayakumar.codegists.version;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepository extends MongoRepository<Version, String> {
    List<Version> findAllBySnippetId(String snippetId);

    Version findByVersionId(String versionId);
}
