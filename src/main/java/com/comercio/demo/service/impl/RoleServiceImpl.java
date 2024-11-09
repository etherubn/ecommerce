package com.comercio.demo.service.impl;

import com.comercio.demo.entity.Role;
import com.comercio.demo.repository.RoleRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class RoleServiceImpl extends CrudServiceImpl<Role,Long> implements IRoleService  {

    private final RoleRepository roleRepository;

    @Override
    protected RepoGeneric<Role, Long> getRepo() {
        return roleRepository;
    }

}
