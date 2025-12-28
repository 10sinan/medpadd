package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "content_creator_verification_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentCreatorVerificationRelation {

    @EmbeddedId
    private ContentCreatorVerificationRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("verificationBadgeId")
    @JoinColumn(name = "verification_badge_id")
    private ContentCreatorVerificationBadge verificationBadge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_creator_id")
    @MapsId("contentCreatorId")
    private ContentCreator contentCreator;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentCreatorVerificationRelationId implements Serializable {
        private UUID verificationBadgeId;
        private UUID contentCreatorId;
    }
}
