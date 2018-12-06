package com.charity.charityserver.Dao;

import com.charity.charityserver.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findItemsByUsernameAndStatus(String username, String status);
    List<Item> findItemsByUsernameAndItemidAndStatus(String username, String itemid, String status);
}
