package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "join_membership")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private Media icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_creator_id")
    private ContentCreator contentCreator;

    @Column(nullable = false)
    private String name;

    private String description;

    @Builder.Default
    @Column(precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private OffsetDateTime updatedAt;
}
