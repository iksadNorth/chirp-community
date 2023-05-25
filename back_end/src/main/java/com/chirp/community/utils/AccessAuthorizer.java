package com.chirp.community.utils;

import com.chirp.community.configuration.JpaAuditingConfig;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.type.RoleType;

import java.util.Optional;

public class AccessAuthorizer {
    public static boolean ownershipCheck(Long idWhomPrincipalWannaAccess) {
        Optional<SiteUserDto> optionalWhoWillProvide = JpaAuditingConfig.principal();

        return optionalWhoWillProvide.isPresent() && optionalWhoWillProvide.get().id().equals(idWhomPrincipalWannaAccess);
    }

    public static boolean isAdmin(RoleType roleTypeOfWhomWannaAccess) {
        boolean result = false;
        result |= roleTypeOfWhomWannaAccess != null && roleTypeOfWhomWannaAccess.equals(RoleType.PRIME_ADMIN);
        return result;
    }

    public static boolean isAdmin() {
        RoleType roleType = JpaAuditingConfig.principal()
                .map(SiteUserDto::role)
                .orElse(null);
        return isAdmin(roleType);
    }
}
