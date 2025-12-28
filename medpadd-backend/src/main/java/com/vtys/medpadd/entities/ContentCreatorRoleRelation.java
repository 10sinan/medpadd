package com.vtys.medpadd.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "content_creator_roles_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentCreatorRoleRelation {

    @EmbeddedId
    private ContentCreatorRoleRelationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentCreatorRoleId")
    @JoinColumn(name = "content_creator_role_id")
    private ContentCreatorRole contentCreatorRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentCreatorId")
    @JoinColumn(name = "content_creator_id")
    private ContentCreator contentCreator;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentCreatorRoleRelationId implements Serializable {
        private UUID contentCreatorRoleId;
        private UUID contentCreatorId;
    }
}
