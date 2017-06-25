package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.BankAccountDTO;
import java.util.List;

/**
 * Service Interface for managing BankAccount.
 */
public interface BankAccountService {

    /**
     * Save a bankAccount.
     *
     * @param bankAccountDTO the entity to save
     * @return the persisted entity
     */
    BankAccountDTO save(BankAccountDTO bankAccountDTO);

    /**
     *  Get all the bankAccounts.
     *
     *  @return the list of entities
     */
    List<BankAccountDTO> findAll();

    /**
     *  Get the "id" bankAccount.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BankAccountDTO findOne(Long id);

    /**
     *  Delete the "id" bankAccount.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the bankAccount corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<BankAccountDTO> search(String query);
}
