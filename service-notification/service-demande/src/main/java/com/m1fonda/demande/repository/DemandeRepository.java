package com.m1fonda.demande.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.m1fonda.demande.model.Userbanque;

@RepositoryRestResource
public interface DemandeRepository extends JpaRepository<Userbanque, Integer> {

}
