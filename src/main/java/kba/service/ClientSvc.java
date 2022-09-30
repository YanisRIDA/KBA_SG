package kba.service;

import kba.model.Client;
import kba.model.dto.ClientDTO;
import kba.dao.ClientDAO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clientSvc")
public class ClientSvc {

    private final Logger LOG = LoggerFactory.getLogger(ClientSvc.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ClientDAO clientDAO;

    public ClientDTO createClient(ClientDTO clientDTO) {
        LOG.info("[KATA BANK] Creating client account {}", clientDTO);
        Client clientToAdd = modelMapper.map(clientDTO, Client.class);
        clientDAO.createClientAccount(clientToAdd);
        return clientDTO;
    }

}
