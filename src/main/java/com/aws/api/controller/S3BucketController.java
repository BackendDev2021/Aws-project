package com.aws.api.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.aws.api.service.S3BucketService;

/**
 * 
 * @author Mohanlal
 *
 */
@RestController
@RequestMapping("/sample-dynamo-application")
public class S3BucketController {

	@Autowired
	S3BucketService service;

	@PostMapping("/bucket")
	public Object createBucket(@RequestParam String bucketName) {
		return service.createBucket(bucketName);
	}

	@GetMapping("/buckets")
	public Collection<Bucket> getAllBucketsFromConsole() {
		return service.getAllBucketsFromConsole();
	}

	@DeleteMapping("/bucket")
	public String removeBucketFromS3(@RequestParam String bucketName) {
		return service.removeBucketFromS3(bucketName);
	}

	@PostMapping("/bucket/upload")
	public ObjectMetadata uploadFile(@RequestParam String fileName, String bucketName, MultipartFile file)
			throws Exception {
		return service.uploadFile(fileName, bucketName, file);
	}

	@GetMapping("/bucket/download")
	public byte[] download(@RequestParam String bucketName, String filePath) throws IOException {
		return service.download(bucketName, filePath);
	}

	@DeleteMapping("/bucket/object")
	public String removeObjectFromBucket(@RequestParam String bucketName, String fileName) throws IOException {
		return service.removeObjectFromBucket(bucketName, fileName);
	}

	@DeleteMapping("/bucket/objects")
	public String removeMultipleObjectsFromBucket(@RequestParam String bucketName,
			@RequestBody List<KeyVersion> fileNames) {
		return service.removeMultipleObjectsFromBucket(bucketName, fileNames);
	}
}
