package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "content_tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private Media icon;

    @Column(nullable = false, unique = true)
    private String name;
}
