package com.charity.charityserver.Dao;

import com.charity.charityserver.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

}
