package com.TD.BL_Monolith_TD.domain.repositories;

import com.TD.BL_Monolith_TD.domain.entities.Cookies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookiesRepository extends JpaRepository<Cookies, String> {

}
