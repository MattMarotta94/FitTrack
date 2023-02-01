package com.nashss.se.fittrack.dynamodb;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;


public class DynamoDbClientProvider {
    /**
     * Returns DynamoDB client using default region.
     * @return AmazonDynamoDB
     */
    public static AmazonDynamoDB getDynamoDBClient() {
        return getDynamoDBClient(Regions.US_EAST_2);
    }

    /**
     * Returns DynamoDB client using default region.
     * @param region If present, will be used as the region for the DynamoDB client
     * @return AmazonDynamoDB
     */
    public static AmazonDynamoDB getDynamoDBClient(Regions region) {
        if (null == region) {
            throw new IllegalArgumentException("region cannot be null");
        }

        return AmazonDynamoDBClientBuilder
                .standard()
                // this will use multiple providers to look for AWS credentials
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                // This should be the same region the CloudFormation stack with tables was deployed in
                .withRegion(region)
                .build();
    }
}
