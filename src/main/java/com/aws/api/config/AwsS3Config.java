package com.aws.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {

	
//	   Access key id will be read from the application.properties file during the
//	  application intialization.
	  
	  @Value("${aws.accessKey}") 
	  private String accessKey;
	  
//	  Secret access key will be read from the application.properties file during
//	  the application intialization.
	  
	  @Value("${aws.secretKey}") 
	  private String secretAccessKey;
	 
//	   Region will be read from the application.properties file during the
//	  application intialization.
	  
	  @Value("${aws.default_region}") 
	  private String region;
	 
	
	@Bean
	public AmazonS3 s3client() {

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretAccessKey);
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
		return amazonS3Client;
	}

}
