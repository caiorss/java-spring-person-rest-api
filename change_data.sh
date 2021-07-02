#!/usr/bin/env sh 

# Note: Before using this script set the environment variable.
# $ export API_KEY=<API-KEY>
#

curl -0 -v -H "X-AUTH-KEY: $API_KEY" --request PUT http://localhost:9056/api/v1/people/1 \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   { 
       "firstName": "Marcus"
     , "lastName": "Augustus Caesar"
     , "cpf": "370.329.298-93"
     , "dateOfBirth": "1992-12-15"  
     , "phones": [
          { "type":  "HOME", "phone": "9-926-2159"}
       ]
   }
EOF

#exit 0

curl -0 -v -H "X-AUTH-KEY: $API_KEY" --request PUT http://localhost:9056/api/v1/people/2  \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Maria"
     , "lastName": "Soledad Herrera"
     , "cpf": "891.655.479-93"
     , "dateOfBirth": "2001-09-24"  
     , "phones": [
          { "type":  "HOME", "phone": "9-926-2159"}
       ]

   }
EOF
