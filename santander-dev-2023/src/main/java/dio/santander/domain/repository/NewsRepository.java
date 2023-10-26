package dio.santander.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.santander.domain.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
