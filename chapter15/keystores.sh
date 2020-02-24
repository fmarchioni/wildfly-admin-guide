if [[ -z "${JBOSS_HOME}" ]]; then
  echo "You need to set JBOSS_HOME"
  exit
fi

keytool -genkeypair -alias localhost -keyalg RSA -keysize 2048 -validity 365 -keystore server.keystore -dname "cn=Server Administrator,o=Acme,c=GB" -keypass secret -storepass secret

cp server.keystore $JBOSS_HOME/standalone/configuration

keytool -genkeypair -alias client -keyalg RSA -keysize 2048 -validity 365 -keystore client.keystore -dname "CN=client" -keypass secret -storepass secret

keytool -exportcert  -keystore server.keystore -alias localhost -keypass secret -storepass secret -file server.crt
 
keytool -exportcert  -keystore client.keystore -alias client -keypass secret -storepass secret -file client.crt

keytool -importcert -keystore server.truststore -storepass secret -alias client -trustcacerts -file client.crt -noprompt
 
keytool -importcert -keystore client.truststore -storepass secret -alias localhost -trustcacerts -file server.crt -noprompt

cp client.truststore $JBOSS_HOME/standalone/configuration