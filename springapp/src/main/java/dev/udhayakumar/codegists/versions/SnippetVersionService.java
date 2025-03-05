package dev.udhayakumar.codegists.versions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetVersionService {

    @Autowired
    SnippetVersionRepository snippetVersionRepository;

    public String save(SnippetVersion snippetVersion) {
        return snippetVersionRepository.save(snippetVersion).getVersionId();
    }

    public List<SnippetVersion> findAllSnippetVersion(String snippetId) {
        return snippetVersionRepository.findAllBySnippetId(snippetId);
    }

    public SnippetVersion findSnippetVersion(String versionId) {
        return snippetVersionRepository.findByVersionId(versionId);
    }
}
