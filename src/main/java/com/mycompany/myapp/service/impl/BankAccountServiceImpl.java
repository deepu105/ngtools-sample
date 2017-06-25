package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BankAccountService;
import com.mycompany.myapp.domain.BankAccount;
import com.mycompany.myapp.repository.BankAccountRepository;
import com.mycompany.myapp.repository.search.BankAccountSearchRepository;
import com.mycompany.myapp.service.dto.BankAccountDTO;
import com.mycompany.myapp.service.mapper.BankAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing BankAccount.
 */
@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService{

    private final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final BankAccountRepository bankAccountRepository;

    private final BankAccountMapper bankAccountMapper;

    private final BankAccountSearchRepository bankAccountSearchRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper, BankAccountSearchRepository bankAccountSearchRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
        this.bankAccountSearchRepository = bankAccountSearchRepository;
    }

    /**
     * Save a bankAccount.
     *
     * @param bankAccountDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BankAccountDTO save(BankAccountDTO bankAccountDTO) {
        log.debug("Request to save BankAccount : {}", bankAccountDTO);
        BankAccount bankAccount = bankAccountMapper.toEntity(bankAccountDTO);
        bankAccount = bankAccountRepository.save(bankAccount);
        BankAccountDTO result = bankAccountMapper.toDto(bankAccount);
        bankAccountSearchRepository.save(bankAccount);
        return result;
    }

    /**
     *  Get all the bankAccounts.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankAccountDTO> findAll() {
        log.debug("Request to get all BankAccounts");
        return bankAccountRepository.findAll().stream()
            .map(bankAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one bankAccount by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BankAccountDTO findOne(Long id) {
        log.debug("Request to get BankAccount : {}", id);
        BankAccount bankAccount = bankAccountRepository.findOne(id);
        return bankAccountMapper.toDto(bankAccount);
    }

    /**
     *  Delete the  bankAccount by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankAccount : {}", id);
        bankAccountRepository.delete(id);
        bankAccountSearchRepository.delete(id);
    }

    /**
     * Search for the bankAccount corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankAccountDTO> search(String query) {
        log.debug("Request to search BankAccounts for query {}", query);
        return StreamSupport
            .stream(bankAccountSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(bankAccountMapper::toDto)
            .collect(Collectors.toList());
    }
}
