package requestquotationtransporteraws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(name = "RequestQuotationTransporterAService", serviceName = "RequestQuotationTransporterAService", portName = "RequestQuotationTransporterAServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class RequestQuotationTransporterAService {
    
    public RequestQuotationTransporterAService() {
        super();
    }
    
    @WebMethod
    public double generateOffer(@WebParam(name ="distanceInKm") double distanceInKm){
        
        // generate a random number between 5 and 25
        int pricePerKm = 5 + (int)(Math.random() * ((25 - 5) + 1));
        
        wait30Seconds();
        
        // multiply the random number by the distance
        //return String.valueOf(Double.parseDouble(distance) * pricePerKm);
        return distanceInKm * pricePerKm;
    }

    @WebMethod(exclude = true)
    public void wait30Seconds(){
        try {
            Thread.sleep(30*1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
