package twittersendmessage;

import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import twitter4j.auth.AccessToken;

@WebService(portName = "TwitterSendMessageSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class TwitterSendMessage {
    public TwitterSendMessage() {
        super();
    }
    
    /**
     * Sends a message to another user. Returns true if it succeeds, false otherwise.
     * @param text
     * @param recepientId
     * @return
     */
    public boolean sendMessage(@WebParam(name="text")String text, @WebParam(name="recepientId")long recepientId) {
        Twitter twitter = new TwitterFactory().getInstance();
        /*try {
            twitter.setOAuthConsumer("M20c9yedrhQl2sPNHc0u4g4mt", "OusBg5NBFlkBVBtV42Cu55HNA5RJH35w2y6XlEkEV3UrX93aAE"); 
        } catch(Exception e) {
            
        }*/
        AccessToken accessToken = new AccessToken("731472959760650243-YVzh36McGNSHvQvswItI1mVaXi4EQAS", "sBxn2UAsRpWxDy9Wg37zZCdjJ3qTkOwbDkV3PqKVGeYLh");

        twitter.setOAuthAccessToken(accessToken);
        
        try {
            twitter.sendDirectMessage(recepientId, text);
        } catch(TwitterException e) {
            return false;
        }
        
        return true;
    }
}
