package dio.santander.service;

import dio.santander.domain.model.User;

public interface UserService {
  User findById(Long id);

  User create(User userToCreate);
}
