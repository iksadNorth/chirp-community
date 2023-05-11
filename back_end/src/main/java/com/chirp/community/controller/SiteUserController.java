package com.chirp.community.controller;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.SiteUserCreateRequest;
import com.chirp.community.model.request.SiteUserUpdateRequest;
import com.chirp.community.model.response.SiteUserReadResponse;
import com.chirp.community.service.SiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class SiteUserController {
    private final SiteUserService siteUserService;

    @PostMapping
    public SiteUserReadResponse create(@RequestBody SiteUserCreateRequest request) {
        SiteUserDto dto = siteUserService.create(request.email(), request.password(), request.nickname());
        return SiteUserReadResponse.of(dto);
    }

    @GetMapping("/{id}")
    public SiteUserReadResponse readById(@PathVariable Long id) {
        SiteUserDto dto = siteUserService.readById(id);
        return SiteUserReadResponse.of(dto);
    }

    @PatchMapping("/{id}")
    public SiteUserReadResponse updateById(@PathVariable Long id, @RequestBody SiteUserUpdateRequest request) {
        SiteUserDto dto = siteUserService.updateById(id, request.email(), request.password(), request.nickname(), request.role());
        return SiteUserReadResponse.of(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        siteUserService.deleteById(id);
    }
}
