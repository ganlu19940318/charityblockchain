package com.charity.charityserver.Dao;

import com.charity.charityserver.Entity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInfoRepository extends JpaRepository<ItemInfo, Integer> {
    ItemInfo findItemInfoByItemid(String itemid);
}
