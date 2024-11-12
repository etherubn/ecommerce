package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateRoleDto;
import com.comercio.demo.dto.response.ResponseRoleDto;
import com.comercio.demo.service.IRoleService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final IRoleService roleService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ResponseRoleDto>> getAll() {
        List<ResponseRoleDto> roleDtos = mapperUtil.mapList(roleService.findAll(), ResponseRoleDto.class);
        return new ResponseEntity<>(roleDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseRoleDto> create(@Valid @RequestBody CreateRoleDto createRoleDto) {
        CreateRoleDto roleDto = roleService.create(createRoleDto);
        return new ResponseEntity<>(mapperUtil.map(roleDto,ResponseRoleDto.class),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseRoleDto> update(@PathVariable Long id, @Valid @RequestBody CreateRoleDto createRoleDto) {
        CreateRoleDto roleDto =  roleService.update(id,createRoleDto);
        return new ResponseEntity<>(mapperUtil.map(roleDto,ResponseRoleDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRoleDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(roleService.getById(id),ResponseRoleDto.class),HttpStatus.OK);
    }
}
