package com.aws.api.model;

import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Mobile {

	@DynamoDBAttribute(attributeName = "mobile_type")
	private String type;
	
	@DynamoDBAttribute(attributeName = "country_code")
	private String countryCode;
	
	@Pattern(regexp = "[0-9]+", message = "allows only numeric values")
	@DynamoDBAttribute(attributeName = "mobile_number")
	private Long mobileNumber;
}
