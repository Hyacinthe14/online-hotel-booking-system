package rw.reservation.hotelbooking.service;

import rw.reservation.hotelbooking.dto.UserDto;
import rw.reservation.hotelbooking.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<User> findAllUsers();

    UserDto getUserById(Long id);

    void updateUser(UserDto userDto);

    void deleteUser(Long id);
}