package com.vtys.medpadd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "content_creator_roles_relations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ContentCreatorRolesRelations extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private ContentCreatorRoles contentCreatorsRoles;

    @ManyToOne
    @JoinColumn(name = "content_creator_id", referencedColumnName = "id")
    private ContentCreators contentCreators;
}
