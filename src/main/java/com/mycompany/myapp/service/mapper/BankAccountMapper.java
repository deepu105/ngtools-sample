package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BankAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BankAccount and its DTO BankAccountDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface BankAccountMapper extends EntityMapper <BankAccountDTO, BankAccount> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    BankAccountDTO toDto(BankAccount bankAccount); 

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "operations", ignore = true)
    BankAccount toEntity(BankAccountDTO bankAccountDTO); 
    default BankAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(id);
        return bankAccount;
    }
}
