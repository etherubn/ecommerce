package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreateRoleDto;
import com.comercio.demo.entity.Role;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.repository.RoleRepository;
import com.comercio.demo.service.IRoleService;
import com.comercio.demo.util.MapperUtil;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl extends CrudServiceImpl<Role,Long, CreateRoleDto> implements IRoleService  {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(MapperUtil mapperUtil, RoleRepository roleRepository) {
        super(mapperUtil);
        this.roleRepository = roleRepository;
    }

    @Override
    protected RepoGeneric<Role, Long> getRepo() {
        return roleRepository;
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    protected Class<CreateRoleDto> getDtoClass() {
        return CreateRoleDto.class;
    }

    @Override
    protected void setEntityId(Role entity, Long id) {
        entity.setIdRole(id);
    }
}
