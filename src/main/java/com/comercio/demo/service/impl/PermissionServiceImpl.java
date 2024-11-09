package com.comercio.demo.service.impl;


import com.comercio.demo.entity.Permission;
import com.comercio.demo.repository.PermissionRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PermissionServiceImpl extends CrudServiceImpl<Permission,Long> implements IPermissionService  {

    private final PermissionRepository permissionRepository;

    @Override
    protected RepoGeneric<Permission, Long> getRepo() {
        return permissionRepository;
    }

}
