package dev.udhayakumar.codegists.file;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {
}
