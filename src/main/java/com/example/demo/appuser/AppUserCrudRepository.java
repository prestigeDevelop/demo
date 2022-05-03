package com.example.demo.appuser;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserCrudRepository extends CrudRepository<AppUser,Long> {

    List<AppUser> findByEmail(String email);
    List<AppUser> findAll();
}
