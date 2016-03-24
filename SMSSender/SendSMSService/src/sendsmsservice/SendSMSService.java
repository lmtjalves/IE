package sendsmsservice;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import javax.net.ssl.X509TrustManager;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import sendsmsservice.exceptions.FailSendingSMSException;
import sendsmsservice.exceptions.SMSServiceCallException;

@WebService(serviceName = "SendSMSService", portName = "SendSMSServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class SendSMSService {
    private static final String ENDPOINT="https://rest.nexmo.com/sms/xml";
    private static final String CHARSET="UTF-8";

    
    public SendSMSService() {
        super();
    }
    
    /**
     * Send an SMS to a number, with the given message.
     * @param to
     * @param message
     */
    @WebMethod
    public boolean sendSMS(@WebParam(name="to") String to, @WebParam(name="message") String message) throws FailSendingSMSException{

        try{
            Parameters params = new Parameters(to, message);
            String responseString = callService(params);
            Response response = new Response(responseString);
            
            return response.isOk();
        } catch(Exception e) {
            throw new FailSendingSMSException(e);
        }
    }
    
    /**
     * Call the service to send the message with the given parameters.
     * @param params
     * @return
     * @throws SMSServiceCallException
     */
    private String callService(Parameters params) throws SMSServiceCallException {
        try {
            String url = SendSMSService.ENDPOINT + "?" + params.toString();
            
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", SendSMSService.CHARSET);
            connection.setRequestProperty( "Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "Accept", "*/*" );
            
            InputStream response = connection.getInputStream();

            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            // Validate the http response code
            if(httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new SMSServiceCallException("HTTP response code is not OK");

            BufferedReader rd = new BufferedReader(new InputStreamReader(response));

            StringBuilder responseBuilder = new StringBuilder();
            for(String inputLine = rd.readLine(); inputLine != null; inputLine = rd.readLine()) {
                responseBuilder.append(inputLine);
            }

            rd.close();

            // Return the data from the http response
            return responseBuilder.toString();

        } catch(IOException e) {
            throw new SMSServiceCallException(e);
        }
    }

}
