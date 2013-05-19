package management;

import model.Email;
import java.io.IOException;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.auth.PropertiesCredentials;

public class EmailManage {
	public Email email;
	public PropertiesCredentials credentials;
	public Destination destination;
	public Content subject;
	public Content textBody;
	public Body body;
	public Message message;
	public SendEmailRequest request;
	public AmazonSimpleEmailServiceClient client;
	
	//public EmailManage(){};
	public EmailManage() throws IOException {
		credentials = new PropertiesCredentials(EmailManage.class.getResourceAsStream("AwsCredentials.properties"));
		credentials.getAWSAccessKeyId();
		credentials.getAWSSecretKey();
		System.out.println("Need Credential information: "+ credentials.getAWSAccessKeyId() + " " + credentials.getAWSSecretKey());
		destination = new Destination();
		subject = new Content();
		textBody = new Content();
		body = new Body();
		message = new Message();
		request = new SendEmailRequest();
		client = new AmazonSimpleEmailServiceClient(credentials);
	}
	
	public void sendEmail(Email email){
		destination.withToAddresses(new String[]{email.getToAddr()});
		subject.withData(email.getSub());
		textBody.withData(email.getBody());
		body.withText(textBody);
		message.withSubject(subject).withBody(body);
		request.withSource(email.getFromAddr()).withDestination(destination).withMessage(message);
		System.out.println("Email Test");
		System.out.println(email.getFromAddr());
		System.out.println(email.getToAddr());
		
		try{
			System.out.println("Attenmpting to send an email.....");
			client.sendEmail(request);
			System.out.println("Email Sent");
		}
		catch (Exception ex){
			System.out.println("The email was not sent...");
			System.out.println("Error Message: " + ex.getMessage());
		}
	}
	
	
	
}
