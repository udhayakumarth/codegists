package dev.udhayakumar.codegists.files;

import dev.udhayakumar.codegists.config.R2ObjectStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    Logger log = LoggerFactory.getLogger(FileService.class);

    @Autowired
    FileRepository fileRepository;

    @Autowired
    R2ObjectStorageService r2ObjectStorageService;

    public String postFile(String snippetId, FileRequestDto fileRequestDto){
        log.info("Invoked FileService: [Method: postFile]");
        try {
            String fileId = UUID.randomUUID().toString();
            String r2Location = r2ObjectStorageService.uploadFile(fileId, fileRequestDto.getFileContent());
            log.info("File upload successful with r2Location: {}", r2Location);
            File file = new File(
                    snippetId,
                    fileRequestDto.getFileName(),
                    r2Location,
                    fileRequestDto.getLanguage()
            );
            String fileIdInDb = fileRepository.save(file).getFileId();
            log.info("File saved in database with id: {}", fileIdInDb);
            return fileIdInDb;

        } catch (Exception e) {
            log.error("Error uploading/saving file: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public List<File> getFilesBySnippetId(String snippetId) {
        return fileRepository.findAllBySnippetId(snippetId);
    }
}
