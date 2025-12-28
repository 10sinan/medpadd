package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "follow_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRelation {

    @EmbeddedId
    private FollowRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentCreatorId")
    @JoinColumn(name = "content_creator_id")
    private ContentCreator contentCreator;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FollowRelationId implements Serializable {
        private UUID userId;
        private UUID contentCreatorId;
    }
}
