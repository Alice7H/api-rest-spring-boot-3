package dio.santander.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dio.santander.domain.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
