package com.hrms.repository;

import com.hrms.core.entities.User;
import com.hrms.dtos.userDtos.GetUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT new com.hrms.dtos.userDtos.GetUserDto(u.id, u.emailAddress, u.password) FROM User u ORDER BY u.id")
    List<GetUserDto> getUserDto();

    @Query("FROM User u WHERE emailAddress=:email")
    User getByEmailAddress(String email);
}
