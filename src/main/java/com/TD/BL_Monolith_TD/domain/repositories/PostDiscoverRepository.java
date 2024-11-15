package com.TD.BL_Monolith_TD.domain.repositories;

import com.TD.BL_Monolith_TD.domain.entities.PostDiscover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDiscoverRepository extends JpaRepository<PostDiscover,String> {
    @Query("SELECT p.tags FROM postDiscover p")
    List<String> findAllTags();

    @Query("SELECT p.urlImg FROM postDiscover p WHERE p.title = :title ORDER BY CASE WHEN p.user.id = :userId THEN 0 ELSE 1 END, p.user.id")
    List<String> findAllUrlImgOrderByUserId(String title,  String userId) ;
}
