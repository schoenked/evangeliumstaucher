#!\bin\bash

openssl pkcs12 -export -chain -inkey privkey.pem -CAfile fullchain.pem -in cert.pem -out myp12file.p12 -name your_alias_name -password pass:your_pass
