package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "content_creator_tag_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentCreatorTagRelation {

    @EmbeddedId
    private ContentCreatorTagRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private ContentCreatorTag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentCreatorId")
    @JoinColumn(name = "content_creator_id")
    private ContentCreator contentCreator;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentCreatorTagRelationId implements Serializable {
        private UUID tagId;
        private UUID contentCreatorId;
    }
}
