#!\bin\bash
#copy this file to your cerbot output directory
openssl pkcs12 -export -chain -inkey privkey.pem -CAfile fullchain.pem -in cert.pem -out myp12file.p12 -name your_alias_name
