package dio.santander.service.impl;

import static java.util.Optional.ofNullable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dio.santander.domain.model.User;
import dio.santander.domain.repository.UserRepository;
import dio.santander.service.UserService;
import dio.santander.service.exception.BusinessException;
import dio.santander.service.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService {
  private static final Long UNCHANGEABLE_USER_ID = 1L;
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Transactional(readOnly = true)
  public List<User> findAll() {
    return this.userRepository.findAll();
  }

  @Transactional
  public User create(User entity) {
    ofNullable(entity).orElseThrow(() -> new BusinessException("User to create must not be null."));
    ofNullable(entity.getAccount()).orElseThrow(() -> new BusinessException("User account must not be null."));
    ofNullable(entity.getCard()).orElseThrow(() -> new BusinessException("User card must not be null."));

    this.validateChangeableId(entity.getId(), "created");

    if (userRepository.existsByAccountNumber(entity.getAccount().getNumber())) {
      throw new BusinessException("This account already exists.");
    }
    if (userRepository.existsByCardNumber(entity.getCard().getNumber())) {
      throw new BusinessException("This card number already exists.");
    }
    return userRepository.save(entity);
  }

  @Transactional
  public User update(Long id, User entity) {
    this.validateChangeableId(id, "updated");
    User dbUser = this.findById(id);
    if (!dbUser.getId().equals(entity.getId())) {
      throw new BusinessException("Update ID must be the same");
    }

    dbUser.setName(entity.getName());
    dbUser.setAccount(entity.getAccount());
    dbUser.setCard(entity.getCard());
    dbUser.setFeatures(entity.getFeatures());
    dbUser.setNews(entity.getNews());

    return this.userRepository.save(dbUser);
  }

  @Transactional
  public void delete(Long id) {
    this.validateChangeableId(id, "deleted");
    User dbUser = this.findById(id);
    this.userRepository.delete(dbUser);
  }

  private void validateChangeableId(Long id, String operation) {
    if (UNCHANGEABLE_USER_ID.equals(id)) {
      throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
    }
  }
}
