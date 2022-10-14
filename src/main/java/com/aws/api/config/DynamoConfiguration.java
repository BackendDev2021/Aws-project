package com.aws.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoConfiguration {

	// Aws account endpoint url
	private String endPoint;

	// Aws account region url
	private String region;

	// Aws account access key value
	private String accessKey;

	// Aws account secret key value
	private String secretKey;

	/**
	 * Building Dynamo Mapper operations
	 * 
	 * @return
	 */
	@Bean
	public DynamoDBMapper dynamoMapper() {
		return new DynamoDBMapper(buildDynamoConfig());
	}

	/**
	 * We are addressing endpoint , region and credentials to access the account by
	 * access key , secret key
	 * 
	 * @return
	 */
	private AmazonDynamoDB buildDynamoConfig() {
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, region))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();
	}
}
