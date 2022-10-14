package com.aws.api.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.ObjectMetadata;

public interface S3BucketService {

	public ObjectMetadata uploadFile(String fileName,String bucketName, MultipartFile file) throws Exception;

	public Object createBucket(String bucketName);

	public Collection<Bucket> getAllBucketsFromConsole();

	public String removeBucketFromS3(String bucketName);

	public byte[] download(String bucketName, String filePath) throws IOException;

	public String removeObjectFromBucket(String bucketName, String fileName) throws IOException;

	public String removeMultipleObjectsFromBucket(String bucketName,List<KeyVersion> fileNames);
}
