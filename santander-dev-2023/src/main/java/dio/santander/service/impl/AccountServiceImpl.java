package dio.santander.service.impl;

import static java.util.Optional.ofNullable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dio.santander.domain.model.Account;
import dio.santander.domain.repository.AccountRepository;
import dio.santander.service.AccountService;
import dio.santander.service.exception.BusinessException;
import dio.santander.service.exception.NotFoundException;

@Service
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Transactional(readOnly = true)
  public List<Account> findAll() {
    return this.accountRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Account findById(Long id) {
    return this.accountRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Transactional
  public Account create(Account entity) {
    ofNullable(entity).orElseThrow(() -> new BusinessException("Account to create must not be null."));
    ofNullable(entity.getAgency()).orElseThrow(() -> new BusinessException("Account agency must not be null."));
    ofNullable(entity.getNumber()).orElseThrow(() -> new BusinessException("Account number must not be null."));

    return accountRepository.save(entity);
  }

  @Transactional
  public Account update(Long id, Account entity) {
    Account dbAccount = this.findById(id);
    if (!dbAccount.getId().equals(entity.getId())) {
      throw new BusinessException("Update ID must be the same");
    }
    dbAccount.setBalance(entity.getBalance());
    dbAccount.setLimit(entity.getLimit());
    dbAccount.setAgency(entity.getAgency());
    dbAccount.setNumber(entity.getNumber());

    return this.accountRepository.save(dbAccount);
  }

  @Transactional
  public void delete(Long id) {
    Account dbAccount = this.findById(id);
    this.accountRepository.delete(dbAccount);
  }
}
