package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_badge_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBadgeRelation {

    @EmbeddedId
    private UserBadgeRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("badgeId")
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserBadgeRelationId implements Serializable {
        private UUID userId;
        private UUID badgeId;
    }
}
