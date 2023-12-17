package com.GraduationProject.test.repositories;

import com.GraduationProject.test.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    static Optional<AppUser> findOneByNameAndPassword(String name, String password) {
        return null;
    }

    static AppUser findByName(String name) {
        return null;
    }
}
