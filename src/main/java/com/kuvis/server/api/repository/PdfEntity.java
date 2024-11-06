package com.kuvis.server.api.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pdf")
public class PdfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "pdf", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoryEntity> historyEntities;

    public PdfEntity(String filename, String name) {
        this.filename = filename;
        this.name = name;
    }
}
