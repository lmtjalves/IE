Follow the next steps to configure the Weblogic server properly:

1. Goto localhost:7001/console and login with username: soa_user password: welcome1
2. In the left menu select Environment -> Servers
3. Select the Admin Server
4. Then go to Configuration -> Keystores
5. Confirm that in the Keystores option you have the "Demo Identity and Demo Trust" selected
6. Go to Configuration -> SSL
7. Select advanced
8. Set the Hostname Verification to NONE
9. Enable JSSE SSL
10. Finally go to configuration -> Server Start
11. Set the following values in the arguments:

-Djavax.net.ssl.trustStore=/oracle/fmwhome/wlserver_10.3/server/lib/DemoTrust.jks
-Djavax.net.ssl.trustStorePassword=DemoTrustKeyStorePassPhrase
-DUseSunHttpHandler=true
-Dssl.SocketFactory.provider=sun.security.ssl.SSLSocketFactoryImpl
-Dssl.ServerSocketFactory.provider=sun.security.ssl.SSLSocketFactoryImpl

12. Restart the Admin Server
