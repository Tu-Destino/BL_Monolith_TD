package com.TD.BL_Monolith_TD.domain.repositories;

import com.TD.BL_Monolith_TD.domain.entities.PostDiscover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDiscoverRepository extends JpaRepository<PostDiscover,String> {
}
