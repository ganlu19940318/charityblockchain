package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findAllByUsername(String username);
    List<User> findAllByDtype(String type);
    List<User> findAllByAddress(String address);
    List<User> findAllByPoeid(String poeid);
    List<User> findAllByDtypeAndStateIsNotContaining(String type, String state);
}
