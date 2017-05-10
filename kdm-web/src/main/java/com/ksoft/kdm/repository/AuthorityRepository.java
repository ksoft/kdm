package com.ksoft.kdm.repository;


import com.ksoft.kdm.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
