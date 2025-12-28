package com.vtys.medpadd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "content_creator_verifications_badges")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ContentCreatorVerificationsBadges extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Media icon;

    @Column(unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String color;
}
