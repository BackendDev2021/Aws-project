package com.aws.api.service;

import com.aws.api.model.AppResponse;
import com.aws.api.model.User;

public interface DynamoService {

	public AppResponse<String> saveUser(User user);

	public AppResponse<User> getUserByEmail(String emailId);

	public AppResponse<String> updateUser(String id,User user);

	public AppResponse<String> removeUser(String id);

}
