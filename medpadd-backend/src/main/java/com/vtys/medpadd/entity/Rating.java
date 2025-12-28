package com.vtys.medpadd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)// bu entity'nin BaseEntity'den miras aldığını belirtir
public class Rating extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Double rating;

    @ManyToOne // her bir rating bir kullanıcıya aittir
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne // her bir rating bir içeriğe aittir
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private Content content;
}
