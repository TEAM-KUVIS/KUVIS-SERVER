package com.kuvis.server.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findByPdfId(Long pdfId);
}
