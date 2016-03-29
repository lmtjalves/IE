package addressvalidationservice;

import addressvalidationservice.exceptions.CTTServiceCallFailException;

import java.io.*;
import java.net.*;

import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * Service to interact with the CTT external service for address validation.
 */
@WebService(name = "AddressValidationService", serviceName = "AddressValidationService", portName = "AddressValidationServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class ExternalCTTService {
    private final static String ENDPOINT = "http://www.ctt.pt/pdcp/xml_pdcp";
    private final static String CHARSET  = "UTF-8";
    
    public ExternalCTTService() {
        super();
    }
    
    
    public boolean isAddressValid(@WebParam(name ="distrito")     String distrito, 
                                  @WebParam(name ="concelho")     String concelho, 
                                  @WebParam(name ="rua")          String rua, 
                                  @WebParam(name ="numPorta")     String numPorta, 
                                  @WebParam(name ="codigoPostal") String codigoPostal) throws CTTServiceCallFailException {
        try {
            ServiceParameters params = new ServiceParameters(distrito, concelho, rua, numPorta, codigoPostal);
            String responseString = callCTTService(params);
            ServiceResponse cttResponse = new ServiceResponse(responseString);

            return cttResponse.isOK();
        } catch(Exception e) {
            throw new CTTServiceCallFailException(e);
        }
    }
    
    private String callCTTService(ServiceParameters params) throws CTTServiceCallFailException {
        String url = ExternalCTTService.ENDPOINT + "?" + params.toString();
        
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", ExternalCTTService.CHARSET);
            InputStream response = connection.getInputStream();
            
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            // Validate the http response code
            if(httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) 
                throw new CTTServiceCallFailException("HTTP response code is not OK");
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            
            StringBuilder responseBuilder = new StringBuilder();
            for(String inputLine = rd.readLine(); inputLine != null; inputLine = rd.readLine()) {
                responseBuilder.append(inputLine);
            }
            
            rd.close();
            
            // Return the data from the http response
            return responseBuilder.toString();
        } catch(IOException e) {
            throw new CTTServiceCallFailException(e);
        }
    }
}
