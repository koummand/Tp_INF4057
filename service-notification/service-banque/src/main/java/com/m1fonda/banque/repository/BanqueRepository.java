package com.m1fonda.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.m1fonda.banque.model.Userbanque;

@RepositoryRestResource
public interface BanqueRepository extends JpaRepository<Userbanque, Integer> {

}