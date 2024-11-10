package com.m1.fonda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.m1.fonda.model.Compte;

@RestResource
public interface CompteRepository extends JpaRepository<Compte, String>{

}
