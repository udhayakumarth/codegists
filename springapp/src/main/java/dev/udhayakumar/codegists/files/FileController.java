package dev.udhayakumar.codegists.files;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/files/{userName}")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/{snippetId}")
    public ResponseEntity<?> getFilesBySnippetId(@PathVariable String snippetId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getFilesBySnippetId(snippetId));
    }

    @GetMapping("/{snippetId}/{fileId}")
    public ResponseEntity<?> getFileById(@PathVariable String userName, @PathVariable String snippetId, @PathVariable String fileId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getFileById(fileId));
    }

    @GetMapping("/{snippetId}/versions")
    public ResponseEntity<?> getFileVersionsBySnippetId(){
        return null;
    }

    @GetMapping("/{snippetId}/versions/{versionId}")
    public ResponseEntity<?> getFileByVersionId(){
        return null;
    }

    @PostMapping("/{snippetId}")
    public ResponseEntity<?> postFile(@PathVariable String userName, @PathVariable String snippetId, @RequestBody FileRequestDto fileRequestDto) throws IOException {
        String fileId = fileService.postFile(snippetId, fileRequestDto);

        String location = "/api/snippet/"+userName+"/"+snippetId+"/"+fileId;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(location));

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(null);
    }

    @PutMapping("/{snippetId}")
    public ResponseEntity<?> putFile(){
        return null;
    }
}
