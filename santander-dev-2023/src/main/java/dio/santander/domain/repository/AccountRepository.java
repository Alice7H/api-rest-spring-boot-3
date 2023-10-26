package dio.santander.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dio.santander.domain.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
