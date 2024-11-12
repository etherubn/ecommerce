package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreatePermissionDto;
import com.comercio.demo.entity.Permission;
import com.comercio.demo.repository.PermissionRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.IPermissionService;
import com.comercio.demo.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
public class PermissionServiceImpl extends CrudServiceImpl<Permission,Long, CreatePermissionDto> implements IPermissionService  {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(MapperUtil mapperUtil, PermissionRepository permissionRepository) {
        super(mapperUtil);
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected RepoGeneric<Permission, Long> getRepo() {
        return permissionRepository;
    }

    @Override
    protected Class<Permission> getEntityClass() {
        return Permission.class;
    }

    @Override
    protected Class<CreatePermissionDto> getDtoClass() {
        return CreatePermissionDto.class;
    }

}
