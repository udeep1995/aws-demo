package com.demo.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsClient {

	@Value("${access.key.id}")
	private String accessKeyId;
	@Value("${access.key.secret}")
	private String accessSecret;
	@Value("${s3.region.name}")
	private String s3RegionName;

	@Bean
	public AmazonS3 getAmazonS3Client() {
		final BasicAWSCredentials basicCredentials = new BasicAWSCredentials(accessKeyId, accessSecret);
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(basicCredentials))
				.withRegion(s3RegionName)
				.build();
	
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessSecret() {
		return accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}

	public String getS3RegionName() {
		return s3RegionName;
	}

	public void setS3RegionName(String s3RegionName) {
		this.s3RegionName = s3RegionName;
	}

}
