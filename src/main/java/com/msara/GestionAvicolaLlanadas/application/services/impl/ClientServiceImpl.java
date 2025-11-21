package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordClientRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.services.ClientService;
import com.msara.GestionAvicolaLlanadas.domain.entities.ClientEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public GeneralResponse recordClient(RecordClientRequest request) {
        ClientEntity clientEntity = ClientEntity.builder()
                .fullName(request.fullName())
                .address(request.address())
                .phone(request.phone())
                .document(request.document())
                .build();

        clientRepository.save(clientEntity);
        return new GeneralResponse("00", "Cliente creado correctamente", true, clientEntity);
    }

    @Override
    public GeneralResponse getClients() {
        List<ClientEntity> clientEntity = clientRepository.findAll();
        return new GeneralResponse("00", "Listado de clientes", true, clientEntity);
    }
}
