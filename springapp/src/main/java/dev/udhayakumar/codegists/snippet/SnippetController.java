package dev.udhayakumar.codegists.snippet;

import dev.udhayakumar.codegists.auth.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/snippet")
public class SnippetController {

    @Autowired
    SnippetService snippetService;

    Logger log = LoggerFactory.getLogger(SnippetController.class);

    @Operation(summary = "To Submit New Snippet")
    @PostMapping("/{userName}")
    public ResponseEntity<?> saveSnippet(@PathVariable String userName,@RequestBody Snippet snippet){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        log.info("Received request to save snippet for user: {}", userName);

        try{
            if(userName.equals(authUsername)){
                log.info("User authentication successful. Authenticated user: {}", authUsername);

                //setting userName in snippet
                snippet.setUserName(userName);
                String snippetId = snippetService.saveSnippet(snippet);
                log.info("Snippet saved successfully with ID: {}", snippetId);

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

    @Operation(summary = "To find Snippets")
    @GetMapping("/{userName}")
    public ResponseEntity<?> findSnippet(@PathVariable String userName){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        if(userName.equals(authUsername)){
            List<Snippet> snippets = snippetService.findSnippet(userName);
            if(!snippets.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(snippets);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @Operation(summary = "To find Snippet")
    @GetMapping("/{userName}/{snippetId}")
    public ResponseEntity<?> findSnippet(@PathVariable String userName, @PathVariable String snippetId){
        String authUsername = AuthUtil.getAuthenticatedUsername();
        Snippet snippet = snippetService.findSnippetById(userName,snippetId);
        if(!snippet.getPublic() && userName.equals(authUsername)){
            return ResponseEntity.status(HttpStatus.OK).body(snippet);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Operation
    @PutMapping("/{userName}/{snippetId}")
    public ResponseEntity<?> editSnippet(@PathVariable String userName, @PathVariable String snippetId){
        //To-do
        return null;
    }

    @Operation
    @DeleteMapping("/{userName}/{snippetId}")
    public ResponseEntity<?> deleteSnippet(@PathVariable String userName, @PathVariable String snippetId){
        //To-do
        return null;
    }
}
