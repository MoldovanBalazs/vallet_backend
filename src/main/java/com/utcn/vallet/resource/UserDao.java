package com.utcn.vallet.resource;

import com.utcn.vallet.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
