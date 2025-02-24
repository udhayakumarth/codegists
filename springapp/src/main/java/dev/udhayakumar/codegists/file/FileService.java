package dev.udhayakumar.codegists.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    public void saveAll(List<File> files) {
        fileRepository.saveAll(files);
    }
}
