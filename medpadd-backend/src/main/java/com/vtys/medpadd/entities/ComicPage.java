package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "comic_pages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComicPage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_id")
    private ComicContent comic;

    @Column(name = "page_url", nullable = false)
    private String pageUrl;

    @Column(name = "page_number")
    private Integer pageNumber;
}
