package dio.santander.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.santander.domain.model.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
