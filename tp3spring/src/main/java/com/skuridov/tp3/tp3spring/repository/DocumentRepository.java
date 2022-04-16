package com.skuridov.tp3.tp3spring.repository;

import com.skuridov.tp3.tp3spring.model.Document.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> getDocumentById(long id);
}
