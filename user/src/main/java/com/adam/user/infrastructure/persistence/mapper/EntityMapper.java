package com.adam.user.infrastructure.persistence.mapper;

import com.adam.user.domain.Account;
import com.adam.user.infrastructure.persistence.entity.AccountEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    public AccountEntity toEntity(Account domainAccount);

    @AfterMapping default void setProfileAccountId(
            Account account,
            @MappingTarget AccountEntity accountEntity) {
        if (accountEntity.getProfile() != null) {
            accountEntity.getProfile().setAccountId(account.getId());
        }
    }
}
