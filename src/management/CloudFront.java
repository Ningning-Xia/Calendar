package management;

import org.jets3t.service.CloudFrontService;
import org.jets3t.service.CloudFrontServiceException;
import org.jets3t.service.model.cloudfront.Distribution;
import org.jets3t.service.model.cloudfront.DistributionConfig;
import org.jets3t.service.model.cloudfront.LoggingStatus;
import org.jets3t.service.model.cloudfront.S3Origin;
import org.jets3t.service.model.cloudfront.StreamingDistribution;

import com.amazonaws.auth.AWSCredentials;

public class CloudFront {
	public static String bucketName = "ningxia91-bucket.s3.amazonaws.com";
	//public static String bucketName = "assign2-bucket.s3.amazonaws.com";
	public static CloudFrontService cloudFrontService = null;
	
	public CloudFront() {
		init();
	}
	
	public static void init() {
		try {
			S3Class s3 = new S3Class();
			AWSCredentials Credentials = s3.credentials;
			String awsAccessKey = Credentials.getAWSAccessKeyId();
			String awsSecretKey = Credentials.getAWSSecretKey();

			org.jets3t.service.security.AWSCredentials awsCredentials = new org.jets3t.service.security.AWSCredentials(
					awsAccessKey, awsSecretKey);
		
			cloudFrontService = new CloudFrontService(awsCredentials);
		} catch (CloudFrontServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("error here");
			e.printStackTrace();
			
		}
	}

	public static void main(String[] args) {
		String streaming = getStreaming();
		System.out.println("Streaming " + streaming);
		
		String download = getDownload();
		System.out.println("Download " + download);

	}
	
	public static StreamingDistribution[] getStreamingDistributions() {
		StreamingDistribution[] bucketDistributions = null;
		try {
			bucketDistributions = cloudFrontService.listStreamingDistributions();
			
			for (int i = 0; i < bucketDistributions.length; i++) {
				System.out.println("Bucket distribution " + (i + 1) + ": "
						+ bucketDistributions[i]);
			}
		} catch (CloudFrontServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bucketDistributions;
	}

	
	public static Distribution[] getDistributions() {
		Distribution[] bucketDistributions = null;
		try {
			bucketDistributions = cloudFrontService.listDistributions();

			for (int i = 0; i < bucketDistributions.length; i++) {
				System.out.println("Bucket distribution " + (i + 1) + ": "
						+ bucketDistributions[i]);
			}
		} catch (CloudFrontServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bucketDistributions;

	}
	
	public static String getStreaming() {
		init();
		String domainName = null;
		StreamingDistribution[] distributionList = getStreamingDistributions();
		if (distributionList != null && distributionList.length > 0) {
			for (int i = 0; i < distributionList.length; i++) {
				System.out.println("Bucket distribution " + (i + 1) + ": "
						+ distributionList[i]);
				
				if (distributionList[i].isStreamingDistribution() 
						&& distributionList[i].isDeployed()
						&& distributionList[i].isEnabled()) {
					domainName = distributionList[i].getDomainName();
					return domainName;
				}
			}
		} 
		return createStreaming();
	}
	
	public static String createStreaming() {
		String domainName = null;
		try {
			String originBucket = bucketName;
			StreamingDistribution newDistribution = cloudFrontService
					.createStreamingDistribution(new S3Origin(originBucket),
							"" + System.currentTimeMillis(), // Caller reference - a unique string value
							new String[] { "assign2.cloudstreaming.com" }, // CNAME aliases for distribution
							"Testing", // Comment
							true, // Distribution is enabled?
							null // Logging status of distribution (null means disabled)
					);
			System.out.println("New Distribution: " + newDistribution);
			System.out.println("DomainName: " + newDistribution.getDomainName());
			System.out.println("DefaultRootObject " + newDistribution.getConfig().getDefaultRootObject());
			System.out.println("RequiredProtocols " + newDistribution.getConfig().getRequiredProtocols());
			domainName = newDistribution.getDomainName();
		} catch (CloudFrontServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return domainName;
	}
	
	public static String getDownload() {
		init();
		String domainName = null;
		Distribution[] distributionList = getDistributions();
		if (distributionList != null && distributionList.length > 0) {
			for (int i = 0; i < distributionList.length; i++) {
				System.out.println("Bucket distribution " + (i + 1) + ": "
						+ distributionList[i]);
				
				if ( distributionList[i].isDeployed()
						&& distributionList[i].isEnabled()) {
					domainName = distributionList[i].getDomainName();
					return domainName;
				}
			}
		} 
		return createDownload();
	}
	
	public static String createDownload() {
		String domainName = null;
		try {
			String originBucket = bucketName;
			Distribution newDistribution = cloudFrontService
					.createDistribution(new S3Origin(originBucket),
							"" + System.currentTimeMillis(), // Caller reference - a unique string value
							new String[] { "assign2.clouddownload.com" }, // CNAME aliases for distribution
							"Testing", // Comment
							true, // Distribution is enabled?
							null // Logging status of distribution (null means disabled)
					);
			System.out.println("New Distribution: " + newDistribution);
			System.out.println("DomainName: " + newDistribution.getDomainName());
			System.out.println("DefaultRootObject " + newDistribution.getConfig().getDefaultRootObject());
			System.out.println("RequiredProtocols " + newDistribution.getConfig().getRequiredProtocols());
			domainName = newDistribution.getDomainName();
		} catch (CloudFrontServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return domainName;
	}
	
	
	public static void getCloudFront() {

		try {
			S3Class s3 = new S3Class();
			AWSCredentials Credentials = s3.credentials;
			String awsAccessKey = Credentials.getAWSAccessKeyId();
			String awsSecretKey = Credentials.getAWSSecretKey();

			org.jets3t.service.security.AWSCredentials awsCredentials = new org.jets3t.service.security.AWSCredentials(
					awsAccessKey, awsSecretKey);

			CloudFrontService cloudFrontService = new CloudFrontService(
					awsCredentials);

			Distribution[] bucketDistributions = cloudFrontService
					.listDistributions("bucketName");
			for (int i = 0; i < bucketDistributions.length; i++) {
				System.out.println("Bucket distribution " + (i + 1) + ": "
						+ bucketDistributions[i]);
			}

			// Create a new public distribution

			String originBucket = bucketName;
			Distribution newDistribution = cloudFrontService
					.createDistribution(new S3Origin(originBucket),
							"" + System.currentTimeMillis(), // Caller reference - a unique string value
							new String[] { "test1.ningningxia.com" }, // CNAME aliases for distribution
							"Testing", // Comment
							true, // Distribution is enabled?
							null // Logging status of distribution (null means disabled)
					);
			System.out.println("New Distribution: " + newDistribution);

			// The ID of the new distribution we will use for testing
			String testDistributionId = newDistribution.getId();

			// List information about a distribution

			Distribution distribution = cloudFrontService
					.getDistributionInfo(testDistributionId);
			System.out.println("Distribution: " + distribution);

			// List configuration information about a distribution

			DistributionConfig distributionConfig = cloudFrontService
					.getDistributionConfig(testDistributionId);
			System.out.println("Distribution Config: " + distributionConfig);

			// Update a distribution's configuration to add an extra CNAME alias
			// and enable logging.

			DistributionConfig updatedDistributionConfig = cloudFrontService
					.updateDistributionConfig(
							testDistributionId,
							null, // origin -- null for no changes
							new String[] { "test1.jamesmurty.com",
									"test2.jamesmurty.com" }, // CNAME aliases
																// for
																// distribution
							"Another comment for testing", // Comment
							true, // Distribution enabled?
							new LoggingStatus("log-bucket.s3.amazonaws.com",
									"log-prefix/") // Distribution logging
					);
			System.out.println("Updated Distribution Config: "
					+ updatedDistributionConfig);

			// Update a distribution's configuration to require secure HTTPS
			// connections, using the RequiredProtocols feature.

			updatedDistributionConfig = cloudFrontService
					.updateDistributionConfig(
							testDistributionId,
							null, // origin -- null for no changes
							new String[] { "test1.jamesmurty.com",
									"test2.jamesmurty.com" }, // CNAME aliases
																// for
																// distribution
							"HTTPS Only!", // Comment
							true, // Distribution enabled?
							new LoggingStatus("log-bucket.s3.amazonaws.com",
									"log-prefix/"), // Distribution logging
							false, // URLs self-signing disabled
							null, // No other AWS users can sign URLs
							new String[] { "https" }, // RequiredProtocols with
														// HTTPS protocol
							"index.html" // Default Root Object
					);

			System.out.println("HTTPS only distribution Config: "
					+ updatedDistributionConfig);

			// Disable a distribution, e.g. so that it may be deleted.
			// The CloudFront service may take some time to disable and deploy
			// the distribution.

			DistributionConfig disabledDistributionConfig = cloudFrontService
					.updateDistributionConfig(testDistributionId, null,
							new String[] {}, "Deleting distribution", false,
							null);
			System.out.println("Disabled Distribution Config: "
					+ disabledDistributionConfig);

			// Check whether a distribution is deployed

			Distribution distribution1 = cloudFrontService
					.getDistributionInfo(testDistributionId);
			System.out.println("Distribution is deployed? "
					+ distribution1.isDeployed());

			// Convenience method to disable a distribution prior to deletion

			cloudFrontService
					.disableDistributionForDeletion(testDistributionId);

			// Delete a distribution (the distribution must be disabled and
			// deployed first)

			cloudFrontService.deleteDistribution(testDistributionId);
		} catch (CloudFrontServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
