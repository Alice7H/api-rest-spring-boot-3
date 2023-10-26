package dio.santander.service.impl;

import static java.util.Optional.ofNullable;
import java.util.List;

import org.springframework.stereotype.Service;

import dio.santander.domain.model.Feature;
import dio.santander.domain.repository.FeatureRepository;
import dio.santander.service.FeatureService;
import dio.santander.service.exception.BusinessException;
import dio.santander.service.exception.NotFoundException;

@Service
public class FeatureServiceImpl implements FeatureService {
  private final FeatureRepository featureRepository;

  public FeatureServiceImpl(FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  @Override
  public List<Feature> findAll() {
    return this.featureRepository.findAll();
  }

  @Override
  public Feature findById(Long id) {
    return featureRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public Feature create(Feature entity) {
    ofNullable(entity).orElseThrow(() -> new BusinessException("Feature to create must not be null."));
    ofNullable(entity.getIcon()).orElseThrow(() -> new BusinessException("Feature icon must not be null."));
    ofNullable(entity.getDescription())
        .orElseThrow(() -> new BusinessException("Feature description must not be null."));

    return featureRepository.save(entity);
  }

  @Override
  public Feature update(Long id, Feature entity) {
    Feature dbFeature = this.findById(id);
    if (!dbFeature.getId().equals(entity.getId())) {
      throw new BusinessException("Update ID must be the same");
    }
    dbFeature.setIcon(entity.getIcon());
    dbFeature.setDescription(entity.getDescription());
    return this.featureRepository.save(dbFeature);
  }

  @Override
  public void delete(Long id) {
    Feature dbFeature = this.findById(id);
    this.featureRepository.delete(dbFeature);
  }
}
