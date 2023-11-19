package com.example.demo.service;

import com.example.demo.dto.AccountDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface UserService {
//	User save(UserRegistrationDto registrationDto);
//	List<User> findAllUsers();

	User findByConfirmationToken(String confirmationToken);

	User saveUserForMember(User user);

	User saveUserForAdmin(AccountDto dto);

	User findById(long id);

	User findByDeleted(int deleted);

	Page<User> getUserByRole(Set<Role> role, int page);

	List<User> getUserByRole(Set<Role> role);

	User findByEmail(String email);

	void deleteById(long id);

	void softDeleteById(long id);

	void restoreAccountById(long id);

	void deleteBlacklistAccountById(long id);

	List<User> listBlacklistAccount();

	void changePass(User nd, String newPass);

	User updateUser(User user);
}
