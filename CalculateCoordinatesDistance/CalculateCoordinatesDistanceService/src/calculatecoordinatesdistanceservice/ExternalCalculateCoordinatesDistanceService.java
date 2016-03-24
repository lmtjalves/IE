package calculatecoordinatesdistanceservice;

import java.io.*;
import java.net.*;

import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import oracle.xml.parser.v2.*;
import org.xml.sax.InputSource;

@WebService(serviceName = "ExternalCalculateCoordinatesDistanceService", portName = "ExternalCalculateCoordinatesDistanceServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class ExternalCalculateCoordinatesDistanceService {
    public ExternalCalculateCoordinatesDistanceService() {
        super();
    }
    
    public String getCoordinatesDistance(String origin, String destination) {
            
        String apiID = "AIzaSyC8HaO-oxekXp0MsJtdjfQne133-6vi0kg";
        String endpoint = "https://maps.googleapis.com/maps/api/distancematrix/xml?";
        StringBuilder responseString = null;
        String returnString="";
        String xpathResult=null;
        String charset = "UTF-8";
        
        String url = endpoint + "origins=" + origin + "&destinations=" + destination + "&key=" + apiID;
        System.out.println(url);
        
        try {
            URLConnection connection = new URL(url).openConnection();
            xpathResult = "qualidade";
            connection.setRequestProperty("Accept-Charset", charset);
             xpathResult = "qualidade2";
            InputStream response = connection.getInputStream();
             xpathResult = "qualidade3";
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
             xpathResult = "qualidade4";
            int status = httpConnection.getResponseCode();
            System.out.println("Status: " + status);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            responseString = new StringBuilder();
            String inputLine;
            while ((inputLine = rd.readLine()) != null)
                responseString.append(inputLine);
            rd.close();
            System.out.println("Response:" + responseString);
         } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            DOMParser domParser = new DOMParser();
            domParser.parse(new InputSource(new StringReader(responseString.toString())));
            XMLDocument document = domParser.getDocument();
            //XMLNode node = (XMLNode)document.selectSingleNode("/DistanceMatrixResponse/row/element/distance/value");
            //xpathResult = node.getText();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // Converting temperature to Celsius
        //Integer distance = new Integer(xpathResult);
        
        //distance = (distance + 500) / 1000;
        
        //returnString = distance.toString();
        System.out.println(xpathResult);
        return "teste" + xpathResult;
    }
    
}
