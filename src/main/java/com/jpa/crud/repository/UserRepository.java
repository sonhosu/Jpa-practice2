package com.jpa.crud.repository;

import com.jpa.crud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    //유저 id 로 조회
    @Query(value = "select user " +
            "from User user " +
            "where user.id = :id")
    User findByUserId(@Param("id") Long id);


    //


}
