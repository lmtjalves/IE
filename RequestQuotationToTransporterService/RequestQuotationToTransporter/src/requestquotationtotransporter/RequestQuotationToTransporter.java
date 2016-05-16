package requestquotationtotransporter;

import java.net.URL;

import java.net.URLConnection;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import javax.jws.WebService;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(portName = "RequestQuotationToTransporterSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class RequestQuotationToTransporter {
    /**
     * Performs a request to a transporter web service asking for the quotation
     * for a transportation.
     * @param url
     * @param distanceInKm
     * @param parcelWeight
     */
    @WebMethod
    public double doRequest(@WebParam(name = "url")
        String url, @WebParam(name = "distanceInKm")
        double distanceInKm, @WebParam(name = "parcelWeight")
        double parcelWeight) {
        try {
            // Creat a SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            // Send SOAP Message to the Web Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(distanceInKm, parcelWeight), url);
            
            // Close the Connection
            soapConnection.close();
            
            SOAPBody responseBody = soapResponse.getSOAPBody();
            
            return new Double(responseBody.getFirstChild().getFirstChild().getTextContent());
        } catch(Exception e) {
            return -1;
        }
    }
    
    private SOAPMessage createSOAPRequest(double distanceInKm, double parcelWeight) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPElement aPElement = envelope.addNamespaceDeclaration("ns1", "http://transporterrequest/");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyOffer   = soapBody.addChildElement("generateOffer", "ns1");
        SOAPElement soapBodyDistance = soapBodyOffer.addChildElement("distanceInKm");
        SOAPElement soapBodyWeight  = soapBodyOffer.addChildElement("parcelWeight");
        soapBodyDistance.addTextNode(String.valueOf(distanceInKm));
        soapBodyWeight.addTextNode(String.valueOf(parcelWeight));
        
        /*
        <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:ns1="http://transporterrequest/">
            <soap:Body>
                <ns1:generateOffer>
                    <distanceInKm>1234</distanceInKm>
                    <parcelWeight>12</parcelWeight>
                </ns1:generateOffer>
            </soap:Body>
        </soap:Envelope>
        */
        
        soapMessage.saveChanges();
        return soapMessage;
    }
}