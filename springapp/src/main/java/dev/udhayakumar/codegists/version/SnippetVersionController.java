package dev.udhayakumar.codegists.version;

import dev.udhayakumar.codegists.auth.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/snippet")
public class SnippetVersionController {

    @Autowired
    SnippetVersionService snippetVersionService;

    Logger log = LoggerFactory.getLogger(SnippetVersionController.class);

    @GetMapping("/{userName}/{snippetId}/version")
    public ResponseEntity<?> findAllSnippetVersion(@PathVariable String userName, @PathVariable String snippetId){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to find all snippet version for snippetId: {}", snippetId);

        try{
            if(userName.equals(authUsername)){
                List<SnippetVersion> snippetVersions = snippetVersionService.findAllSnippetVersion(snippetId);
                log.info("SnippetVersions found successfully for snippetId: {}", snippetId);
                return ResponseEntity.status(HttpStatus.OK).body(snippetVersions);
            }

            log.info("Unauthorized access to snippet version attempt by user: {}", authUsername);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error occurred while finding all snippet versions for snippetId: {} - {}", snippetId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userName}/{snippetId}/{versionId}")
    public ResponseEntity<?> findSnippetVersion(@PathVariable String userName, @PathVariable String snippetId, @PathVariable String versionId){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to find snippet version for versionId: {}", versionId);

        try{
            if(userName.equals(authUsername)){
                SnippetVersion snippetVersion = snippetVersionService.findSnippetVersion(versionId);
                log.info("SnippetVersion found successfully for versionId: {}", versionId);
                return ResponseEntity.status(HttpStatus.OK).body(snippetVersion);
            }

            log.info("Unauthorized access to snippet versions attempt by user: {}", authUsername);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error occurred while finding SnippetVersion for versionId: {} - {}", versionId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
