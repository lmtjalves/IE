package addressvalidationservice;

import addressvalidationservice.exceptions.FailParsingResponseException;

import java.io.IOException;
import java.io.StringReader;

import oracle.xml.parser.v2.DOMParser;
import oracle.xml.parser.v2.XMLDocument;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ServiceResponse {
    private static final String STATUS_OK   = "OK";
    private XMLDocument document;
    
    public ServiceResponse(String responseXMLString) throws FailParsingResponseException {
        try {
            DOMParser domParser = new DOMParser();
            domParser.parse(new InputSource(new StringReader(responseXMLString)));
            this.document = domParser.getDocument();
            
            // No need to validate the document for now, since we only 
            // want to know it's status
        } catch(IOException e) {
            throw new FailParsingResponseException(e);
        } catch(SAXException e) {
            throw new FailParsingResponseException(e);
        }
    }
    
    /**
     * Returns true if the response from the service is "OK", or false if it's "Erro".
     * @return
     */
    public boolean isOK() {
        return this.document.getDocumentElement()
                            .getTagName()
                            .equals(ServiceResponse.STATUS_OK);
    }
}   