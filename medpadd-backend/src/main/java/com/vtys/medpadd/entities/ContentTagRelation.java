package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "content_tag_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentTagRelation {

    @EmbeddedId
    private ContentTagRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private ContentTag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentId")
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentTagRelationId implements Serializable {
        private UUID tagId;
        private UUID contentId;
    }
}
