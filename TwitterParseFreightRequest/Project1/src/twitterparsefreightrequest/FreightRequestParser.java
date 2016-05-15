package twitterparsefreightrequest;

import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import twitterparsefreightrequest.exceptions.FailParsingFieldException;

@WebService(portName = "FreightRequestParserSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
@XmlSeeAlso( { Response.class })
public class FreightRequestParser {
    private static final int NUM_FIELDS = 6;
    
    public Response parse(@WebParam(name="requestString") String requestString) {
        Response response = new Response();
        
        // Ignore all the spaces
        requestString = requestString.replace(" ", "");
        
        // Split into the different fields
        String[] fields = requestString.split(";");
        if(fields.length != NUM_FIELDS) {
            response.setValid(false);
            response.setCause("Missing fields.");
            return response;
        }
        
        // Try to parse each of the fields
        try {
            response.setEmail(parseEmail(fields[0]));
            
            response.setOriginLatitude(parseOriginLatitude(fields[1]));
            response.setOriginLongitude(parseOriginLatitude(fields[2]));
            response.setDestinationLatitude(parseOriginLatitude(fields[3]));
            response.setDestinationLongitude(parseOriginLatitude(fields[4]));
            
            response.setParcelWeight(parseParcelWeight(fields[5]));
            
            response.setValid(true);
        } catch(FailParsingFieldException e) {
            response.setValid(false);
            response.setCause(e.getMessage());
        }

        return response;
    }
    
    private String parseEmail(String email) throws FailParsingFieldException {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(email.matches(EMAIL_REGEX)) {
            return email;
        } else {
            throw new FailParsingFieldException("Invalid email address.");
        }
    }
    
    private double parseOriginLatitude(String coordinate) throws FailParsingFieldException {
        try {
            return parseCoordinate(coordinate);
        } catch(FailParsingFieldException e) {
            throw new FailParsingFieldException("Invalid origin latitude.");
        }
    }
    
    private double parseOriginLongitude(String coordinate) throws FailParsingFieldException {
        try {
            return parseCoordinate(coordinate);
        } catch(FailParsingFieldException e) {
            throw new FailParsingFieldException("Invalid origin longitude.");
        }
    }
    
    private double parseDestinationLatitude(String coordinate) throws FailParsingFieldException {
        try {
            return parseCoordinate(coordinate);
        } catch(FailParsingFieldException e) {
            throw new FailParsingFieldException("Invalid destionation latitude.");
        }
    }
    
    private double parseDestinationLongitude(String coordinate) throws FailParsingFieldException {
        try {
            return parseCoordinate(coordinate);
        } catch(FailParsingFieldException e) {
            throw new FailParsingFieldException("Invalid destination longitude.");
        }
    }
    
    private double parseCoordinate(String coordinate) throws FailParsingFieldException {
        try {
            return new Double(coordinate);
        } catch(Exception e) {
            throw new FailParsingFieldException(e.getMessage());
        }
    }
    
    private double parseParcelWeight(String weight) throws FailParsingFieldException {
        try {
            return new Double(weight);
        } catch(Exception e) {
            throw new FailParsingFieldException(e.getMessage());
        }
    }
}
