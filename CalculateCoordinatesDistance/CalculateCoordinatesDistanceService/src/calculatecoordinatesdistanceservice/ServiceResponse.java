package calculatecoordinatesdistanceservice;

import calculatecoordinatesdistanceservice.exceptions.FailParsingResponseException;

import java.io.IOException;
import java.io.StringReader;

import oracle.xml.parser.v2.DOMParser;
import oracle.xml.parser.v2.XMLDocument;


import oracle.xml.parser.v2.XMLNode;

import oracle.xml.parser.v2.XSLException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ServiceResponse {
    private final String RESULT_PATH   = "/DistanceMatrixResponse/row/element/distance/value";
    private String resultString;
       
    public ServiceResponse(String responseXMLString) throws FailParsingResponseException {
        try {
            DOMParser domParser = new DOMParser();
            domParser.parse(new InputSource(new StringReader(responseXMLString)));
            XMLDocument document = domParser.getDocument();
            XMLNode node = (XMLNode) document.selectSingleNode(RESULT_PATH);
            this.resultString = node.getText();
            
        } catch(IOException e) {
            throw new FailParsingResponseException(e);
        } catch(SAXException e) {
            throw new FailParsingResponseException(e);
        } catch (XSLException e) {
            throw new FailParsingResponseException(e);
        }
    }
    
    public double getResult() {
        return (Double.parseDouble(this.resultString)) / 1000;
    }
}
