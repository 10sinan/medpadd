package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_subscription_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscriptionRelation {

    @EmbeddedId
    private UserSubscriptionRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subscriptionId")
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSubscriptionRelationId implements Serializable {
        private UUID userId;
        private UUID subscriptionId;
    }
}
