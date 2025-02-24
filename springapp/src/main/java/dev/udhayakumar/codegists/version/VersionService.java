package dev.udhayakumar.codegists.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {

    @Autowired
    VersionRepository snippetVersionRepository;

    public String save(Version snippetVersion) {
        return snippetVersionRepository.save(snippetVersion).getVersionId();
    }

    public List<Version> findAllSnippetVersion(String snippetId) {
        return snippetVersionRepository.findAllBySnippetId(snippetId);
    }

    public Version findSnippetVersion(String versionId) {
        return snippetVersionRepository.findByVersionId(versionId);
    }
}
