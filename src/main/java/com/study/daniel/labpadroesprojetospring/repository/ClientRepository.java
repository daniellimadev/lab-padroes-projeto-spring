package com.study.daniel.labpadroesprojetospring.repository;

import com.study.daniel.labpadroesprojetospring.Model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long > {
}
