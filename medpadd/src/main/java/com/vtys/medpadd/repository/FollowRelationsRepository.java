package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.FollowRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FollowRelationsRepository extends JpaRepository<FollowRelations, UUID> {
    List<FollowRelations> findByContentCreatorsId(UUID contentCreatorId);

    List<FollowRelations> findByComContentCreatorRolesId(UUID roleId);
}
