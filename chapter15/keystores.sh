# Create Server keystore
keytool -genkey -keyalg RSA -keystore server.keystore -storepass secret -keypass secret -storetype pkcs12 -validity 365  -dname "cn=Server Administrator,o=Acme,c=GB"

# Create Client keystore	
keytool -genkey -keystore client.keystore -storepass secret -validity 365 -keyalg RSA -keysize 2048 -storetype pkcs12 -dname "cn=Desktop user,o=Acme,c=GB"

# Export Client keystore
keytool -exportcert -keystore client.keystore  -storetype pkcs12 -storepass secret -keypass secret -file client.crt

# Import the client certificate into the truststore of the client. 
keytool -import -file client.crt -alias quickstartUser -keystore client.truststore -storepass secret
 
 
# Export client certificate to pkcs12 format.
# Not need anymore if we use -storetype pkcs12 
#keytool -importkeystore -srckeystore client.keystore -srcstorepass secret -destkeystore clientCert.p12 -srcstoretype PKCS12 -deststoretype PKCS12 -deststorepass secret
