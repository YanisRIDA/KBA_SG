package kba.service;

import kba.dao.ClientDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tokenSvc")
public class TokenSvc {

    private final Logger LOG = LoggerFactory.getLogger(TokenSvc.class);

    @Autowired
    ClientDAO clientDAO;

    public String generateToken(String mail, String password) {
        LOG.info("[KATA BANK] Getting Token for client {}", mail);
        return clientDAO.getToken(mail, password);
    }

}
