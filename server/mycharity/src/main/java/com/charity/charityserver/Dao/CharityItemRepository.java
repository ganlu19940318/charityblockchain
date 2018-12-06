package com.charity.charityserver.Dao;

import com.charity.charityserver.Entity.CharityItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityItemRepository extends JpaRepository<CharityItem, Integer> {
    CharityItem findCharityItemByPoeid(String poeid);
}
