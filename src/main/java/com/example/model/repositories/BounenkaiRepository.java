package com.example.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.model.entities.bounenkai2;

@Repository
public interface BounenkaiRepository extends JpaRepository<bounenkai2, Long>, JpaSpecificationExecutor<bounenkai2> {

}
