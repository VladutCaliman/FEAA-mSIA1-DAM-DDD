package com.adam.user.infrastructure.persistence.mapper;

import com.adam.user.domain.Account;
import com.adam.user.infrastructure.persistence.entity.AccountEntity;
import com.adam.user.infrastructure.persistence.entity.EmailEmbeddable;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    @Mapping(target = "profile", source = "profile")
    public AccountEntity toEntity(Account domainAccount);

    @Mapping(target = "profile", source = "profile")
    public Account toDomain(AccountEntity accountEntity);

    //region Additional setup
    @AfterMapping default void setProfileAccountId(
            Account account,
            @MappingTarget AccountEntity accountEntity) {
        if (accountEntity.getProfile() != null) {
            accountEntity.getProfile().setAccountId(account.getId());
        }
    }

    default String map(EmailEmbeddable email) {
        return email == null ? null : email.getValue();
    }
    //endregion
}
