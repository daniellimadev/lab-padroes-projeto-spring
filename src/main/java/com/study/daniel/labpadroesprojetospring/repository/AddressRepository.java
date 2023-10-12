package com.study.daniel.labpadroesprojetospring.repository;

import com.study.daniel.labpadroesprojetospring.Model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
