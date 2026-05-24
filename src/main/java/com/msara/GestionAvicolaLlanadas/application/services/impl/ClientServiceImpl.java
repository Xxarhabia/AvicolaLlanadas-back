package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.RecordClientRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.ClientResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.application.exceptions.BusinessException;
import com.msara.GestionAvicolaLlanadas.application.services.ClientService;
import com.msara.GestionAvicolaLlanadas.domain.entities.ClientEntity;
import com.msara.GestionAvicolaLlanadas.domain.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public GeneralResponse recordClient(RecordClientRequest request) {
        if (clientRepository.findByDocument(request.document()).isPresent()) {
            throw new BusinessException("DOCUMENTO_DUPLICADO",
                    "Ya existe un cliente con el documento: " + request.document());
        }

        ClientEntity client = ClientEntity.builder()
                .fullName(request.fullName())
                .document(request.document())
                .phone(request.phone())
                .address(request.address())
                .build();

        clientRepository.save(client);
        return new GeneralResponse("00", "Cliente registrado correctamente", true, toResponse(client));
    }

    @Override
    @Transactional(readOnly = true)
    public GeneralResponse getClients() {
        List<ClientResponse> clients = clientRepository.findByIsActiveTrue()
                .stream().map(this::toResponse).toList();
        return new GeneralResponse("00", "Listado de clientes", true, clients);
    }

    @Override
    @Transactional(readOnly = true)
    public GeneralResponse getClientById(Long id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente #" + id + " no encontrado"));
        return new GeneralResponse("00", "Cliente encontrado", true, toResponse(client));
    }

    private ClientResponse toResponse(ClientEntity e) {
        return new ClientResponse(
                e.getClientId(), e.getFullName(), e.getDocument(),
                e.getPhone(), e.getAddress(), e.getIsActive(), e.getCreatedAt()
        );
    }
}
