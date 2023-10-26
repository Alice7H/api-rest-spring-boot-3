package dio.santander.service.impl;

import static java.util.Optional.ofNullable;
import java.util.List;

import org.springframework.stereotype.Service;

import dio.santander.domain.model.News;
import dio.santander.domain.repository.NewsRepository;
import dio.santander.service.NewsService;
import dio.santander.service.exception.BusinessException;
import dio.santander.service.exception.NotFoundException;

@Service
public class NewsServiceImpl implements NewsService {
  private final NewsRepository newsRepository;

  public NewsServiceImpl(NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }

  @Override
  public List<News> findAll() {
    return this.newsRepository.findAll();
  }

  @Override
  public News findById(Long id) {
    return newsRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public News create(News entity) {
    ofNullable(entity).orElseThrow(() -> new BusinessException("News to create must not be null."));
    ofNullable(entity.getIcon()).orElseThrow(() -> new BusinessException("News icon must not be null."));
    ofNullable(entity.getDescription())
        .orElseThrow(() -> new BusinessException("News description must not be null."));

    return newsRepository.save(entity);
  }

  @Override
  public News update(Long id, News entity) {
    News dbNews = this.findById(id);
    if (!dbNews.getId().equals(entity.getId())) {
      throw new BusinessException("Update ID must be the same");
    }
    dbNews.setIcon(entity.getIcon());
    dbNews.setDescription(entity.getDescription());
    return this.newsRepository.save(dbNews);
  }

  @Override
  public void delete(Long id) {
    News dbNews = this.findById(id);
    this.newsRepository.delete(dbNews);
  }
}
