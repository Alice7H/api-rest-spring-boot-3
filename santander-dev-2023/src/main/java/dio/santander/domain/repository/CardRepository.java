package dio.santander.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.santander.domain.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
