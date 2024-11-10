package com.m1fonda.demande.DemandeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.m1fonda.demande.model.Demande;

@RestResource
public interface DemandeRepository  extends JpaRepository<Demande, Integer>{

}
