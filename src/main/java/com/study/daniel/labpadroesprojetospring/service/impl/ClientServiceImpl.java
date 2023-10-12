package com.study.daniel.labpadroesprojetospring.service.impl;

import com.study.daniel.labpadroesprojetospring.Model.Address;
import com.study.daniel.labpadroesprojetospring.Model.Client;
import com.study.daniel.labpadroesprojetospring.repository.AddressRepository;
import com.study.daniel.labpadroesprojetospring.repository.ClientRepository;
import com.study.daniel.labpadroesprojetospring.service.ClientService;
import com.study.daniel.labpadroesprojetospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClientService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 * @author falvojr
 */
@Service
public class ClientServiceImpl implements ClientService {

    // Singleton: Inject Spring components with @Autowired.
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implement the methods defined in interface.
    // Facade: Abstract integrations with subsystems, providing a simple interface.

    @Override
    public Iterable<Client> searchAll() {
        // Search all Customers.
        return clientRepository.findAll();
    }

    @Override
    public Client searchById(Long id) {
        // Search Customer by ID.
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void insert(Client client) {
        saveCustomerWithZipCode(client);
    }



    @Override
    public void update(Long id, Client client) {

        // Search Customer by ID, if applicable:
            Optional<Client> clientid = clientRepository.findById(id);
            if (clientid.isPresent()) {
                saveCustomerWithZipCode(client);
            }
    }

    @Override
    public void delete(Long id) {

        // Delete Customer by ID.
        clientRepository.deleteById(id);
    }

    private void saveCustomerWithZipCode(Client client) {

        // Check if the Customer Address already exists (by zip code).
        String cep = client.getAddress().getCep();
        Address endereco = addressRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Address novoEndereco = viaCepService.consultarCep(cep);
            addressRepository.save(novoEndereco);
            return novoEndereco;
        });
        client.setAddress(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clientRepository.save(client);
    }

}
