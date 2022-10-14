package com.aws.api.serviceImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.aws.api.service.S3BucketService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3BucketServiceImpl implements S3BucketService {

	@Autowired
	private AmazonS3 amazonS3Client;

//	@Value("${aws.s3bucket.name}")
//	private String bucketName;

	@SuppressWarnings("deprecation")
	@Override
	public Object createBucket(String bucketName) {
		if (amazonS3Client.doesBucketExist(bucketName)) {
			log.warn("Bucket name is not available.Try again with a different Bucket name");
		} 
		return amazonS3Client.createBucket(bucketName);

	}

	@Override
	public ObjectMetadata uploadFile(String fileName, String bucketName, MultipartFile file) throws Exception {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		return amazonS3Client.putObject(bucketName, fileName, file.getInputStream(), metadata).getMetadata();
	}

	@Override
	public Collection<Bucket> getAllBucketsFromConsole() {
		return amazonS3Client.listBuckets().stream().sorted().collect(Collectors.toList());
	}

	@Override
	public String removeBucketFromS3(String bucketName) {
		try {
			amazonS3Client.deleteBucket(bucketName);
			return "Bucket removed successfully - " + bucketName;
		} catch (AmazonServiceException ex) {
			log.error("Error occured while deleting bucket" + bucketName + ex);
		}
		return null;
	}

	@Override
	public byte[] download(String bucketName, String filePath) throws IOException {
		try {
			S3Object s3object = amazonS3Client.getObject(bucketName, filePath);
			S3ObjectInputStream inputStream = s3object.getObjectContent();
			return com.amazonaws.util.IOUtils.toByteArray(inputStream);
		} catch (AmazonServiceException | IOException ex) {
			log.error("Error occured while downloading");
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public String removeObjectFromBucket(String bucketName, String fileName) throws IOException {
		try {
			amazonS3Client.deleteObject(bucketName, fileName);
			return "Object removed  successfully - " + fileName;
		} catch (AmazonClientException ex) {
			log.error("Error occuring while removing object");
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public String removeMultipleObjectsFromBucket(String bucketName,List<KeyVersion> fileNames) {
		try {
			DeleteObjectsRequest deleteObjects = new DeleteObjectsRequest(bucketName).withKeys(fileNames);
			return "Object removed  successfully from the bucket - " + deleteObjects.getBucketName();
		} catch (AmazonClientException ex) {
			log.error("Error occuring while removing object");
			throw new IllegalStateException(ex);
		}
	}

}
