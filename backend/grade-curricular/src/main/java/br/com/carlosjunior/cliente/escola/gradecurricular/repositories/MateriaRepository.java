package br.com.carlosjunior.cliente.escola.gradecurricular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlosjunior.cliente.escola.gradecurricular.entities.MateriaEntity;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {

}