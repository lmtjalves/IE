package calculatecoordinatesdistanceservice;

import calculatecoordinatesdistanceservice.exceptions.CalculateCoordinatesDistanceServiceCallFailException;

import java.io.*;
import java.net.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(name = "CalculateCoordinatesDistanceService", serviceName = "CalculateCoordinatesDistanceService", portName = "CalculateCoordinatesDistanceServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class ExternalCalculateCoordinatesDistanceService {
    
    private final static String ENDPOINT = "https://maps.googleapis.com/maps/api/distancematrix/xml";
    private final static String CHARSET  = "UTF-8";
    private final static String KEY = "AIzaSyC8HaO-oxekXp0MsJtdjfQne133-6vi0kg";
            
    public ExternalCalculateCoordinatesDistanceService() {
        super();
    }
    
    @WebMethod
    public double getCoordinatesDistance(@WebParam(name ="origin_latitude") String origin_latitude,
                                         @WebParam(name ="origin_longitude") String origin_longitude,
                                         @WebParam(name ="destination_latitude") String destination_latitude,
                                         @WebParam(name ="destination_longitude") String destination_longitude
                                         ) throws CalculateCoordinatesDistanceServiceCallFailException {
            
        try {
            ServiceParameters params = new ServiceParameters(origin_latitude,
                                                             origin_longitude,
                                                             destination_latitude,
                                                             destination_longitude);
            String responseString = callCalculateCoordinatesDistanceService(params);
            ServiceResponse response = new ServiceResponse(responseString);
            return response.getResult();
            
        } catch (Exception e){
            throw new CalculateCoordinatesDistanceServiceCallFailException(e);
        }
    }
    
    private String callCalculateCoordinatesDistanceService(ServiceParameters params)
    throws CalculateCoordinatesDistanceServiceCallFailException {
        
        String url = ExternalCalculateCoordinatesDistanceService.ENDPOINT
                     + "?" + params.toString()
                     + ExternalCalculateCoordinatesDistanceService.KEY;
            
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset",
                                          ExternalCalculateCoordinatesDistanceService.CHARSET);
            InputStream response = connection.getInputStream();
                
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            // Validate the http response code
            if(httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) 
                throw new CalculateCoordinatesDistanceServiceCallFailException
                    ("HTTP response code is not OK");
                
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
                
            StringBuilder responseBuilder = new StringBuilder();
            for(String inputLine = rd.readLine(); inputLine != null; inputLine = rd.readLine()) {
                responseBuilder.append(inputLine);
            }
                
            rd.close();
                
            // Return the data from the http response
             return responseBuilder.toString();
        } catch(IOException e) {
            throw new CalculateCoordinatesDistanceServiceCallFailException(e);
        }
    }
    
}
