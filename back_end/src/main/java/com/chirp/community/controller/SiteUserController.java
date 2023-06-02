package com.chirp.community.controller;

import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.SiteUserCreateRequest;
import com.chirp.community.model.request.SiteUserUpdateRequest;
import com.chirp.community.model.response.ArticleReadMyPageRowResponse;
import com.chirp.community.model.response.SiteUserReadResponse;
import com.chirp.community.service.ArticleService;
import com.chirp.community.service.AuthService;
import com.chirp.community.service.SiteUserService;
import com.chirp.community.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class SiteUserController implements SiteUserDocs {
    private final SiteUserService siteUserService;
    private final ArticleService articleService;
    private final AuthService authService;

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

    @GetMapping("/me")
    public SiteUserReadResponse readByAuthToken(@AuthenticationPrincipal SiteUserDto principal) {
        return SiteUserReadResponse.of(principal);
    }

    @GetMapping("/{id}/article")
    public Page<ArticleReadMyPageRowResponse> readArticleById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault Pageable pageable
    ) {
        Page<ArticleDto> dtos = articleService.readBySiteUserId(id, keyword, pageable);
        return dtos.map(ArticleReadMyPageRowResponse::of);
    }

    @GetMapping("/me/article")
    public Page<ArticleReadMyPageRowResponse> readArticleByAuthToken(
            @AuthenticationPrincipal SiteUserDto principal,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault Pageable pageable
    ) {
        Page<ArticleDto> dtos = articleService.readBySiteUserId(principal.id(), keyword, pageable);
        return dtos.map(ArticleReadMyPageRowResponse::of);
    }

    @PatchMapping("/{id}")
    public SiteUserReadResponse updateById(@PathVariable Long id, @RequestBody SiteUserUpdateRequest request) {
        SiteUserDto dto = siteUserService.updateById(id, request.email(), request.password(), request.nickname());
        return SiteUserReadResponse.of(dto);
    }

    @PatchMapping("/me")
    public SiteUserReadResponse updateByAuthToken(@AuthenticationPrincipal SiteUserDto principal, @RequestBody SiteUserUpdateRequest request) {
        SiteUserDto dto = siteUserService.updateByAuthToken(principal, request.email(), request.password(), request.nickname());
        return SiteUserReadResponse.of(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        siteUserService.deleteById(id);
    }

    @PatchMapping("/{id}/role/user")
    public SiteUserReadResponse updateRoleToUser(@PathVariable Long id) {
        SiteUserDto dto = siteUserService.updateRoleTo(id, RoleType.USER);
        return SiteUserReadResponse.of(dto);
    }

    @PatchMapping("/{id}/role/board_admin")
    public SiteUserReadResponse updateRoleToBoardAdmin(@PathVariable Long id) {
        SiteUserDto dto = siteUserService.updateRoleTo(id, RoleType.BOARD_ADMIN);
        return SiteUserReadResponse.of(dto);
    }

    @PatchMapping("/{id}/role/user_verified_by_email")
    public SiteUserReadResponse updateRoleToUserVerifiedByEmail(@PathVariable Long id, @RequestParam("code") String code) {
        SiteUserDto dto = authService.verifyCodeWithEmail(id, code);
        return SiteUserReadResponse.of(dto);
    }
}
