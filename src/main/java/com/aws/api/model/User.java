package com.aws.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "user")
public class User {
	
	@DynamoDBHashKey
	@DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
	private String id;
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Please use alphabetic letter for names")
	@DynamoDBAttribute(attributeName = "user_name")
	private String name;
	
	@Email(message = "Email is not valid", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
	@DynamoDBAttribute(attributeName = "user_emailId")
	private String emailId;
	
	@DynamoDBAttribute(attributeName = "user_gender")
	@DynamoDBTypeConvertedEnum
	private Gender gender;
	
	
	@DynamoDBAttribute(attributeName = "user_mobile")
	private Mobile mobile;

}
