package management;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.Placement;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3Class {
	
	public static AmazonEC2 ec2;
	public static AmazonS3Client s3;
	public static AWSCredentials credentials;
	public static String bucketName = "ningxia91-bucket";
	
	public S3Class(){
		//AWSCredentials credentials;
		try {
			credentials = new PropertiesCredentials(
					S3Class.class.getResourceAsStream("AwsCredentials.properties"));
		
		ec2 = new AmazonEC2Client(credentials);
		s3 = new AmazonS3Client(credentials);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static String createS3(){
		List<Bucket> bucketList = s3.listBuckets();
		if (bucketList != null && bucketList.size() > 0) {
			for (Bucket bucket : bucketList) {
				if (bucket.getName().equals(bucketName))
				{
					System.out.println("The bucket exists in S3, bucketName: " + bucketName);
					return bucketName;
				}
			}
		}
		
		s3.createBucket(bucketName);
        System.out.println("A new bucket has been created: " + bucketName);
        return bucketName;
		 
	}
	
	public static String getInstance() {
        System.out.println("4 Describe Current Instances");
        DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();
        List<Reservation> reservations = describeInstancesRequest.getReservations();
        Set<Instance> instances = new HashSet<Instance>();
        // add all instances to a Set.
        for (Reservation reservation : reservations) {
        	instances.addAll(reservation.getInstances());
        }
        
        if (instances != null && instances.size()>0) {
            for (Instance ins : instances){
            	
            	// instance id
            	String instanceId = ins.getInstanceId();
            	
            	// instance state
            	InstanceState is = ins.getState();
            	System.out.println(instanceId+" "+is.getName());
            	
            	return instanceId;
            }
        }
        
        return createInstance("ami-3275ee5b");

	}
	
	public static String createInstance(String ami) {
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
				.withInstanceType("t1.micro")
				.withImageId(ami)
				.withMinCount(1).withMaxCount(1)
				.withSecurityGroupIds("sg-cca4ada4")
				.withKeyName("ning1991")
				.withPlacement(new Placement("us-east-1a"));
		
		RunInstancesResult result = ec2.runInstances(runInstancesRequest);
		List<Instance> resultInstance = result.getReservation().getInstances();
		String createdInstanceId = null;
		for (Instance ins : resultInstance) {
			createdInstanceId = ins.getInstanceId();
			System.out.println("New instance has been created: "
					+ ins.getInstanceId());
		}
		//System.out.println(createdInstanceId);

		return createdInstanceId;

	}
}
