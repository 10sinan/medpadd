package com.vtys.medpadd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "join_memberships")
@NoArgsConstructor
@AllArgsConstructor
public class JoinMemberships extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "content_creator_id", referencedColumnName = "id")
    private ContentCreators contentCreator;

    @ManyToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Media icon;

    @Column
    private String color;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

}
