package dio.santander.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.santander.domain.model.User;
import dio.santander.domain.repository.UserRepository;
import dio.santander.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public User create(User userToCreate) {
    if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
      throw new IllegalArgumentException("This account already exists.");
    }
    return userRepository.save(userToCreate);
  }
}
