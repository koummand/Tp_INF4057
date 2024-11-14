package com.m1.fonda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.m1.fonda.model.Compte;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte, String>{

}
