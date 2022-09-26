package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository rr;
    @Override
    public Role getRoleAdmin() {
        return this.rr.getRoleAdmin();
    }

    @Override
    public Role getRoleClient() {
        return null;
    }
}
