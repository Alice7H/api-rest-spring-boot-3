package dio.santander.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.santander.domain.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
