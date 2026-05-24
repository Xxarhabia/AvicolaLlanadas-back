package com.msara.GestionAvicolaLlanadas.domain.repositories;

import com.msara.GestionAvicolaLlanadas.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findByIsActiveTrue();
}
