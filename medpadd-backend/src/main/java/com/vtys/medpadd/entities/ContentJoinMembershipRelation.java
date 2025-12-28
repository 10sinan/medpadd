package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "content_join_membership_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentJoinMembershipRelation {

    @EmbeddedId
    private ContentJoinMembershipRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("joinMembershipId")
    @JoinColumn(name = "join_membership_id")
    private JoinMembership joinMembership;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentId")
    @JoinColumn(name = "content_id")
    private Content content;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentJoinMembershipRelationId implements Serializable {
        private UUID joinMembershipId;
        private UUID contentId;
    }
}
