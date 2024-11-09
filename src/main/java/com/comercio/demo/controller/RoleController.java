package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateRoleDto;
import com.comercio.demo.dto.response.ResponsePermissionDto;
import com.comercio.demo.dto.response.ResponseRoleDto;
import com.comercio.demo.entity.Permission;
import com.comercio.demo.entity.Role;
import com.comercio.demo.repository.PermissionRepository;
import com.comercio.demo.service.IPermissionService;
import com.comercio.demo.service.IRoleService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoleController {
    private final IRoleService roleService;
    private final MapperUtil mapperUtil;
    private final IPermissionService permissionService;

    @GetMapping("/roles")
    public ResponseEntity<List<ResponseRoleDto>> getAll() {
        List<ResponseRoleDto> roleDtos = mapperUtil.mapList(roleService.findAll(), ResponseRoleDto.class);
        return new ResponseEntity<>(roleDtos,HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<ResponseRoleDto> create(@Valid @RequestBody CreateRoleDto createRoleDto) {
        Role role = mapperUtil.map(createRoleDto, Role.class);
        Set<Permission> permissions = createRoleDto.getPermissions().stream().map(
                permission -> permissionService.getById(permission.getIdPermission())
        ).collect(Collectors.toSet());

        role.setPermissions(permissions);

        Role role1 =  roleService.create(role);

        return new ResponseEntity<>(mapperUtil.map(role1,ResponseRoleDto.class),HttpStatus.CREATED);
    }


    @PutMapping("roles/{id}")
    public ResponseEntity<ResponseRoleDto> update(@PathVariable Long id, @Valid @RequestBody CreateRoleDto createRoleDto) {
        createRoleDto.setIdRole(id);
        Role role =  roleService.update(id,mapperUtil.map(createRoleDto, Role.class));
        return new ResponseEntity<>(mapperUtil.map(role,ResponseRoleDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("roles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<ResponseRoleDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(roleService.getById(id),ResponseRoleDto.class),HttpStatus.OK);
    }
}
