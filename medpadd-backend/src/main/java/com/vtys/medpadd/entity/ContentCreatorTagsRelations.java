package com.vtys.medpadd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "content_creator_tags_relations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ContentCreatorTagsRelations extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "content_creator_id", referencedColumnName = "id")
    private ContentCreators contentCreator;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private ContentCreatorTags tag;
}
