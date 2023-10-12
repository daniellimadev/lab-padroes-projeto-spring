package com.study.daniel.labpadroesprojetospring.service;


import com.study.daniel.labpadroesprojetospring.Model.Client;

public interface ClientService {
    Iterable<Client> searchAll();
    Client searchById(Long id);
    void insert(Client client);
    void update(Long id, Client client);
    void delete(Long id);

}
