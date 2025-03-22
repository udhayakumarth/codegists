package dev.udhayakumar.codegists.snippets;

import dev.udhayakumar.codegists.auth.AuthUtil;
import dev.udhayakumar.codegists.config.ErrorResponseDto;
import dev.udhayakumar.codegists.versions.SnippetVersion;
import dev.udhayakumar.codegists.versions.SnippetVersionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/snippets")
public class SnippetController {

    @Autowired
    private SnippetService snippetService;

    @Autowired
    private SnippetVersionService snippetVersionService;

    Logger log = LoggerFactory.getLogger(SnippetController.class);

    @Operation
    @PostMapping("/{userName}")
    @PreAuthorize("#userName == authentication.name")
    public ResponseEntity<?> saveSnippet(@PathVariable String userName,@RequestBody Snippet snippet) throws Exception {
        try{
            snippet.setUserName(userName);
            String snippetId = snippetService.saveSnippet(snippet);
            log.info("Snippet saved successfully with ID: {}", snippetId);

            String location = "/api/snippet/"+userName+"/"+snippetId;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(location));

            return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(null);
        } catch (Exception e) {
            log.error("Error occurred while saving snippet for user: {} - {}", userName, e.getMessage(), e);
            throw new Exception(e);
        }
    }

    @Operation
    @GetMapping("/{userName}")
    @PreAuthorize("#userName == authentication.name")
    public ResponseEntity<?> findSnippets(@PathVariable String userName) throws Exception {
        try{
            List<Snippet> snippets = snippetService.findSnippet(userName);
            if(snippets.isEmpty()){
                log.error("Snippet not found for userName: {}", userName);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            log.info("Snippet found successfully for userName: {}", userName);
            return ResponseEntity.status(HttpStatus.OK).body(snippets);

        } catch (Exception e) {
            log.error("Error occurred while finding all snippets for user: {} - {}", userName, e.getMessage(), e);
            throw new Exception(e);
        }
    }

    @Operation
    @GetMapping("/{userName}/{snippetId}")
    public ResponseEntity<?> findSnippet(@PathVariable String userName, @PathVariable String snippetId, HttpServletRequest request) throws Exception {
        String authUsername = AuthUtil.getAuthenticatedUsername();

        try {
            Optional<Snippet> snippet = snippetService.findSnippetById(snippetId);
            if(snippet.isEmpty()){
                log.error("Snippet not found successfully for snippetId: {}", snippetId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),"Not Fount","Snippet Not Found for id: "+snippetId, request.getRequestURI()));
            }
            log.info("Snippet found successfully for snippetId: {}", snippetId);

            if(userName.equals(authUsername) || snippet.get().getPublic()) {
                log.info("Snippet is public or owned by user. snippetId: {}", snippetId);
                return ResponseEntity.status(HttpStatus.OK).body(snippet);
            }
            log.info("Unauthorized access snippet is not public for snippetId: {}", snippetId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error occurred while finding snippet for snippetId: {} - {}", snippetId, e.getMessage(), e);
            throw new Exception(e);
        }
    }

    @Operation
    @PutMapping("/{userName}")
    @PreAuthorize("#userName == authentication.name")
    public ResponseEntity<?> editSnippet(@PathVariable String userName, @RequestBody SnippetVersion snippetVersion) throws Exception {
        String authUsername = AuthUtil.getAuthenticatedUsername();

        try {
            if (userName.equals(authUsername)) {
                String snippetIdAfterUpdate = snippetService.editSnippet(snippetVersion);
                log.info("Snippet edited successfully for snippetId: {}", snippetIdAfterUpdate);
                snippetVersion.setUserName(userName);
                String oldSnippetVersionId = snippetVersionService.save(snippetVersion);
                log.info("Snippet old version saved successfully with versionId: {}", oldSnippetVersionId);
                return ResponseEntity.status(HttpStatus.OK).body(snippetIdAfterUpdate);
            }
            log.warn("Unauthorized snippet edit attempt by user: {}", authUsername);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (Exception e) {
            log.error("Error occurred while editing snippet for user: {} - {}", userName, e.getMessage(), e);
            throw new Exception(e);
        }
    }

    @Operation
    @DeleteMapping("/{userName}/{snippetId}")
    @PreAuthorize("#userName == authentication.name")
    public ResponseEntity<?> deleteSnippet(@PathVariable String userName, @PathVariable String snippetId, HttpServletRequest request) throws Exception {
        log.error("Snippet not found successfully for snippetId:");
        try {
            Optional<Snippet> snippet = snippetService.findSnippetById(snippetId);
            if(snippet.isEmpty()){
                log.error("Snippet not found successfully for snippetId: {}", snippetId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),"Not Fount","Snippet Not Found for id: "+snippetId, request.getRequestURI()));
            }
            snippetService.deleteSnippet(snippetId);
            log.info("Snippet delete successfully for snippetId: {}", snippetId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            log.error("Error occurred while deleting snippet for user: {} - {}", userName, e.getMessage(), e);
            throw new Exception(e);
        }
    }
}
