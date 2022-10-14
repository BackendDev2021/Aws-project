package com.aws.api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.api.model.AppResponse;
import com.aws.api.model.User;
import com.aws.api.service.DynamoService;

@Service
public class DynamoServiceImpl implements DynamoService {

	@Autowired
	private DynamoDBMapper dynamoDB;

	@Override
	public AppResponse<String> saveUser(User user) {
		dynamoDB.save(user);
		return new AppResponse<String>(HttpStatus.CREATED, 201, "Saved successfully");
	}

	@Override
	public AppResponse<User> getUserByEmail(String emailId) {
		return new AppResponse<User>(HttpStatus.OK, 200, dynamoDB.load(User.class, emailId));
	}

	@Override
	public AppResponse<String> updateUser(String id, User user) {
		dynamoDB.save(user, new DynamoDBSaveExpression().withExpectedEntry("id",
				new ExpectedAttributeValue(new AttributeValue().withS(id))));
		return new AppResponse<String>(HttpStatus.ACCEPTED, 406, "Updated successfully");
	}

	@Override
	public AppResponse<String> removeUser(String id) {
		dynamoDB.delete(dynamoDB.load(User.class, id));
		return new AppResponse<String>(HttpStatus.NO_CONTENT, 204, "Removed successfully");
	}

}
