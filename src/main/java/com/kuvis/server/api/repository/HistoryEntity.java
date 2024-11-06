package com.kuvis.server.api.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "history")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "query")
    private String query;

    public HistoryEntity(String query, String answer, PdfEntity pdf) {
        this.query = query;
        this.answer = answer;
        this.pdf = pdf;
        this.createdAt = LocalDateTime.now();
    }

    @Column(name = "answer", length = 5000)
    private String answer;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pdf_id", referencedColumnName = "id", nullable = false)
    private PdfEntity pdf;
}
