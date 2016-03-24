package sendsmsservice;

import java.io.IOException;

import java.util.Properties;

public class Parameters {
    private static final String SMS_PROPERTIES_FILE="/sms.properties";
    private static final String FROM_PROPERTY = "from";
    private static final String API_KEY_PROPERTY = "api_key";
    private static final String API_SECRET = "api_secret";
    
    private String to;
    private String message;
    private String from;
    private String apiKey;
    private String apiSecret;
    
    
    public Parameters(String to, String message) throws IOException {
        this.to = to;
        this.message = message;
        
        Properties smsProps = new Properties();
        smsProps.load(getClass().getResourceAsStream(SMS_PROPERTIES_FILE));
        
        this.from = smsProps.getProperty(FROM_PROPERTY);
        this.apiKey = smsProps.getProperty(API_KEY_PROPERTY);
        this.apiSecret = smsProps.getProperty(API_SECRET);
    }
    
    @Override
    public String toString() {
        return "api_key="    + this.apiKey    + "&" +
               "api_secret=" + this.apiSecret + "&" +
               "to="         + this.to        + "&" +
               "from="       + this.from      + "&" +
               "text="       + this.message.replace(' ', '+');
    }
}