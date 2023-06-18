package com.tecnocampus.banktcm.persistence;

import com.tecnocampus.banktcm.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByUserId(String usedId);
}