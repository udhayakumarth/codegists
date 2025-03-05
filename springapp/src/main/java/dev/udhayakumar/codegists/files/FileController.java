package dev.udhayakumar.codegists.files;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files/{username}")
public class FileController {

    @GetMapping("/{snippetId}")
    public ResponseEntity<JsonNode> getFilesBySnippetId(){
        return null;
    }

    @GetMapping("/{snippetId}/{fileId}")
    public ResponseEntity<JsonNode> getFileById(){
        return null;
    }

    @GetMapping("/{snippetId}/versions")
    public ResponseEntity<JsonNode> getFileVersionsBySnippetId(){
        return null;
    }

    @GetMapping("/{snippetId}/{versionId}")
    public ResponseEntity<JsonNode> getFileByVersionId(){
        return null;
    }

    @PostMapping("/{snippetId}")
    public ResponseEntity<JsonNode> postFile(){
        return null;
    }

    @PutMapping("/{snippetId}")
    public ResponseEntity<JsonNode> putFile(){
        return null;
    }
}
