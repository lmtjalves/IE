package requestquotationtransporterzws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(name = "RequestQuotationTransporterZService", serviceName = "RequestQuotationTransporterZService", portName = "RequestQuotationTransporterZServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class RequestQuotationTransporterZService {
    
    public RequestQuotationTransporterZService() {
        super();
    }
    
    @WebMethod
    public double generateOffer(@WebParam(name ="distanceInKm") double distanceInKm, @WebParam(name ="parcelWeight") double parcelWeight){
        
        // generate a random number between 5 and 25
        int randomNumber = 5 + (int)(Math.random() * ((25 - 5) + 1));
        
        double profitPercentage = 1 + (randomNumber / 100.0);
        
        wait30Seconds();
        
        return distanceInKm * parcelWeight * profitPercentage;
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
