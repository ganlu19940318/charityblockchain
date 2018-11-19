package com.example.demo.dao;

import com.example.demo.pojo.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {

    List<Item> findItemsByPoeid(String poeid);
    List<Item> findItemsByState(String state);
    List<Item> findItemsByAddress(String address);
}
