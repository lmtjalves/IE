package passwordgeneratorservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(portName = "PasswordGeneratorSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class PasswordGenerator {
    private static final String ALPHANUMERIC_SET = "abcdefghijklmtopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
   
    public PasswordGenerator() {
        super();
    }
    
    /**
     * Generates a random password with size characters.
     * @param size
     * @return
     */
    @WebMethod
    public String generatePassword(@WebParam(name = "size") int size) {
        StringBuilder sb = new StringBuilder();
                        
        for(;size > 0; size--) {
            int pos = (int)(Math.random() * ALPHANUMERIC_SET.length());
            sb.append(ALPHANUMERIC_SET.charAt(pos));
        }
                        
        return sb.toString();
    }
}
