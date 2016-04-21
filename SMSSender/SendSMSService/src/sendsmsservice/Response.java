package sendsmsservice;

import java.io.IOException;
import java.io.StringReader;

import oracle.xml.parser.v2.DOMParser;

import oracle.xml.parser.v2.XMLDocument;

import oracle.xml.parser.v2.XMLNode;

import oracle.xml.parser.v2.XSLException;

import org.xml.sax.InputSource;

import org.xml.sax.SAXException;

import sendsmsservice.exceptions.FailParsingResponseException;

public class Response {
    private static final String STATUS_ELEMENT_XPATH = "/mt-submission-response/messages/message/status";
    private static final String SUCCESS = "0";
    
    private XMLDocument document;
    
    public Response(String responseXMLString) throws FailParsingResponseException {
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
    public boolean isOk() throws FailParsingResponseException {
        try {
            XMLNode statusNode = (XMLNode) this.document.selectSingleNode(STATUS_ELEMENT_XPATH);
        
            return statusNode.getText().equals(SUCCESS);
        } catch(XSLException e) {
            throw new FailParsingResponseException(e);
        }
    }
}
