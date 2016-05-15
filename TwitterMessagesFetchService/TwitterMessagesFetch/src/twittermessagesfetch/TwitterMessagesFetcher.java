package twittermessagesfetch;

import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;

import twitter4j.conf.ConfigurationBuilder;

@WebService(portName = "TwitterMessagesFetcherSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
@XmlSeeAlso( { Message.class })
public class TwitterMessagesFetcher {
    public TwitterMessagesFetcher() throws Exception {
        super();
    }
    
    public Message[] getMessages() throws Exception {
        Twitter twitter = new TwitterFactory().getInstance();
        /*try {
            twitter.setOAuthConsumer("M20c9yedrhQl2sPNHc0u4g4mt", "OusBg5NBFlkBVBtV42Cu55HNA5RJH35w2y6XlEkEV3UrX93aAE"); 
        } catch(Exception e) {
            
        }*/
        AccessToken accessToken = new AccessToken("731472959760650243-YVzh36McGNSHvQvswItI1mVaXi4EQAS", "sBxn2UAsRpWxDy9Wg37zZCdjJ3qTkOwbDkV3PqKVGeYLh");

        twitter.setOAuthAccessToken(accessToken);
        
        ResponseList<DirectMessage> messages = twitter.getDirectMessages();
        Message[] response = new Message[messages.size()];
        int i = 0;
        for(DirectMessage message : messages) {
            twitter.destroyDirectMessage(message.getId());
            Message m = new Message();
            m.setText(message.getText());
            m.setSenderId(message.getSenderId());
            response[i] = m;
            i++;
        }
        
        return response;
    }
}
