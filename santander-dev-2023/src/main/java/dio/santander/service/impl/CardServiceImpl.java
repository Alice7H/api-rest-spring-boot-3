package dio.santander.service.impl;

import java.util.List;
import static java.util.Optional.ofNullable;
import org.springframework.stereotype.Service;

import dio.santander.domain.model.Card;
import dio.santander.domain.repository.CardRepository;
import dio.santander.service.CardService;
import dio.santander.service.exception.BusinessException;
import dio.santander.service.exception.NotFoundException;

@Service
public class CardServiceImpl implements CardService {
  private final CardRepository cardRepository;

  public CardServiceImpl(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  @Override
  public List<Card> findAll() {
    return this.cardRepository.findAll();
  }

  @Override
  public Card findById(Long id) {
    return this.cardRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public Card create(Card entity) {
    ofNullable(entity).orElseThrow(() -> new BusinessException("Card to create must not be null."));
    ofNullable(entity.getNumber()).orElseThrow(() -> new BusinessException("Card number must not be null."));
    ofNullable(entity.getLimit()).orElseThrow(() -> new BusinessException("Card limit must not be null."));

    return this.cardRepository.save(entity);
  }

  @Override
  public Card update(Long id, Card entity) {
    Card dbCard = this.findById(id);
    if (!dbCard.getId().equals(entity.getId())) {
      throw new BusinessException("Update ID must be the same");
    }
    dbCard.setLimit(entity.getLimit());
    dbCard.setNumber(entity.getNumber());
    return this.cardRepository.save(dbCard);
  }

  @Override
  public void delete(Long id) {
    Card dbCard = this.findById(id);
    this.cardRepository.delete(dbCard);
  }
}
