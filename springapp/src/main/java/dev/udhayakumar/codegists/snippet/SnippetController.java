package dev.udhayakumar.codegists.snippet;

import dev.udhayakumar.codegists.auth.AuthUtil;
import dev.udhayakumar.codegists.file.File;
import dev.udhayakumar.codegists.file.FileService;
import dev.udhayakumar.codegists.version.VersionService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/snippet")
public class SnippetController {

    @Autowired
    private SnippetService snippetService;

    @Autowired
    private VersionService snippetVersionService;

    @Autowired
    private FileService fileService;

    Logger log = LoggerFactory.getLogger(SnippetController.class);

    @Operation
    @PostMapping("/{userName}")
    public ResponseEntity<?> saveSnippet(@PathVariable String userName,@RequestBody SnippetRequestDto snippetRequestDto){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to save snippet for user: {}", userName);

        try{
            if(userName.equals(authUsername)){

                Snippet snippet = new Snippet(
                        snippetRequestDto.getName(),
                        snippetRequestDto.getDescription(),
                        snippetRequestDto.getPublic(),
                        userName
                );
                String snippetId = snippetService.saveSnippet(snippet);
                log.info("Snippet saved successfully with ID: {}", snippetId);

                List<File> files = snippetRequestDto.getFiles();
                files.forEach(file -> file.setSnippetId(snippetId));
                fileService.saveAll(files);
                log.info("Files saved successfully");

                //creating URI and setting in header for the created Snippet
                String location = "/api/snippet/"+userName+"/"+snippetId;
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(URI.create(location));

                return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(null);
            }
            log.warn("Unauthorized snippet submission attempt by user: {}", authUsername);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        } catch (Exception e) {
            log.error("Error occurred while saving snippet for user: {} - {}", userName, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation
    @GetMapping("/{userName}")
    public ResponseEntity<?> findSnippet(@PathVariable String userName){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to find all snippet for user: {}", userName);

        try{
            if(userName.equals(authUsername)){
                List<Snippet> snippets = snippetService.findSnippet(userName);
                if(!snippets.isEmpty()){
                    return ResponseEntity.status(HttpStatus.OK).body(snippets);
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (Exception e) {
            log.error("Error occurred while finding all snippets for user: {} - {}", userName, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation
    @GetMapping("/{userName}/{snippetId}")
    public ResponseEntity<?> findSnippet(@PathVariable String userName, @PathVariable String snippetId){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to find snippet for snippetId: /{}/{}", userName,snippetId);

        try {
            Snippet snippet = snippetService.findSnippetById(snippetId);
            log.info("Snippet found successfully for snippetId: {}", snippetId);
            if(!snippet.getUserName().equals(userName))
                log.info("Snippet belongs to different user. snippetId: {}", snippetId);
            if(userName.equals(authUsername) || snippet.getPublic())
                return ResponseEntity.status(HttpStatus.OK).body(snippet);

            log.info("Unauthorized access snippet is not public for snippetId: {}", snippetId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error occurred while finding snippet for snippetId: {} - {}", snippetId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation
    @PutMapping("/{userName}")
    public ResponseEntity<?> editSnippet(@PathVariable String userName, @RequestBody SnippetVersion snippetVersion){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to edit snippet for user: {}", userName);

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation
    @DeleteMapping("/{userName}/{snippetId}")
    public ResponseEntity<?> deleteSnippet(@PathVariable String userName, @PathVariable String snippetId){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to delete snippet for snippetId: {}", snippetId);

        try {
            log.info("userName.equals(authUsername):{},{}",userName,authUsername);
            if (userName.equals(authUsername)) {
                snippetService.deleteSnippet(snippetId);
                log.info("Snippet delete successfully for snippetId: {}", snippetId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            log.warn("Unauthorized snippet delete attempt by user: {}", authUsername);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (Exception e) {
            log.error("Error occurred while deleting snippet for user: {} - {}", userName, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
