package com.charity.charityserver.Dao;

import com.charity.charityserver.Entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {
    Block findBlockByUsername(String username);
    Block findBlockByPoeid(String poeid);
}
