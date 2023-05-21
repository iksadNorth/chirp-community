package com.chirp.community.utils;

import com.chirp.community.configuration.JpaAuditingConfig;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.type.RoleType;

import java.util.Optional;

public class AccessAuthorizer {
    public static boolean ownershipCheck(Long idWhoWannaAccess) {
        Optional<SiteUserDto> optionalWhoWillProvide = JpaAuditingConfig.principal();

        return optionalWhoWillProvide.isPresent() && optionalWhoWillProvide.get().id().equals(idWhoWannaAccess);
    }

    public static boolean isAdmin(RoleType roleTypeOfWhomWannaAccess) {
        boolean result = false;
        result |= roleTypeOfWhomWannaAccess.equals(RoleType.PRIME_ADMIN);
        return result;
    }
}
