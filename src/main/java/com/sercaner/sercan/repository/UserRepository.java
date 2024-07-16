package com.sercaner.sercan.repository;

import com.sercaner.sercan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
