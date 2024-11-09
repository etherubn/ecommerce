package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreatePermissionDto;
import com.comercio.demo.dto.response.ResponsePermissionDto;
import com.comercio.demo.entity.Permission;
import com.comercio.demo.service.IPermissionService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PermissionController {
    private final IPermissionService permissionService;
    private final MapperUtil mapperUtil;

    @GetMapping("/permissions")
    public ResponseEntity<List<ResponsePermissionDto>> getAll() {
        List<ResponsePermissionDto> permissionDtos = mapperUtil.mapList(permissionService.findAll(), ResponsePermissionDto.class);
        return new ResponseEntity<>(permissionDtos,HttpStatus.OK);
    }

    @PostMapping("/permissions")
    public ResponseEntity<ResponsePermissionDto> create(@Valid @RequestBody CreatePermissionDto createPermissionDto) {
        Permission permission = permissionService.create(mapperUtil.map(createPermissionDto, Permission.class));

        return new ResponseEntity<>(mapperUtil.map(permission,ResponsePermissionDto.class),HttpStatus.CREATED);
    }


    @PutMapping("permissions/{id}")
    public ResponseEntity<ResponsePermissionDto> update(@PathVariable Long id, @Valid @RequestBody CreatePermissionDto createPermissionDto) {
        createPermissionDto.setIdPermission(id);
        Permission permission =  permissionService.update(id,mapperUtil.map(createPermissionDto, Permission.class));
        return new ResponseEntity<>(mapperUtil.map(permission,ResponsePermissionDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("permissions/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("permissions/{id}")
    public ResponseEntity<ResponsePermissionDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(permissionService.getById(id),ResponsePermissionDto.class),HttpStatus.OK);
    }
}
