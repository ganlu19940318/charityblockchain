package com.charity.charityserver.Dao;

import com.charity.charityserver.Entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer> {
    List<Farm> findFarmsByUsername(String username);
    Farm findFarmByUsernameAndNumber(String username, int number);
}
