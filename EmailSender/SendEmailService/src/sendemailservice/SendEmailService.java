package sendemailservice;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.net.ssl.internal.ssl.Provider;

import exceptions.FailSendEmailException;

import java.io.FileInputStream;

import java.io.IOException;

import java.security.Security;

import java.util.Date;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.naming.InitialContext;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(serviceName = "SendEmailService", portName = "SendEmailServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class SendEmailService {
    private static final String SMTP_PROPERTIES_FILE  = "/smtp.properties";
    private static final String EMAIL_PROPERTIES_FILE = "/email.properties";
    private static final String EMAIL_PROPERTY_NAME = "email";
    private static final String PASSWORD_PROPERTY_NAME = "password";
    private static final String HOST_PROPERTY_NAME = "mail.smtp.host";
    private static final String MESSAGE_ENCODING = "utf-8";
    
    public SendEmailService() {
        super();
    }
    
    /**
     * Sends an email.
     * @param to
     * @param subject
     * @param message
     * @throws FailSendEmailException
     */
    public void sendEmail(@WebParam(name = "to") String to,
                          @WebParam(name="subject") String subject,
                          @WebParam(name="message") String message) 
    throws FailSendEmailException {
        try {        
            // Load smtp properties
            Properties smtpProps = new Properties();
            smtpProps.load(getClass().getResourceAsStream(SMTP_PROPERTIES_FILE));
            
            // Load from email properties
            Properties emailProps = new Properties();
            emailProps.load(getClass().getResourceAsStream(EMAIL_PROPERTIES_FILE));
            
            // We could setup a mail session from the Weblogic server
            Session session = Session.getInstance(smtpProps, null);
            
            // Create a new message
            MimeMessage msg = new MimeMessage(session);
                                     
            // Set the from and to
            msg.setFrom(new InternetAddress(emailProps.getProperty(EMAIL_PROPERTY_NAME)));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            
            // Message content
            msg.setSubject(subject);
            msg.setText(message, MESSAGE_ENCODING);

            // Send the email
            String host     = smtpProps.getProperty(HOST_PROPERTY_NAME);
            String username = emailProps.getProperty(EMAIL_PROPERTY_NAME);
            String password = emailProps.getProperty(PASSWORD_PROPERTY_NAME);
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, 587, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch(Exception e) {
            throw new FailSendEmailException(e);
        }
    }
}

