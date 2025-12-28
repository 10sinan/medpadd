package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_join_membership_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinMembershipRelation {

    @EmbeddedId
    private UserJoinMembershipRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("joinMembershipId")
    @JoinColumn(name = "join_membership_id")
    private JoinMembership joinMembership;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserJoinMembershipRelationId implements Serializable {
        private UUID userId;
        private UUID joinMembershipId;
    }
}
