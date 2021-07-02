#!/usr/bin/env sh

echo " [*] CREDENTIAL = $CREDENTIAL"


# Scripting for automating sample data creation through
# the rest API
#
# Note: The CPF (Cadastro de Pessoas Fisicas), Brazilian equivalent of
# American's SSN (Social Security Number), were generate using the
# the online tool:
#   + https://theonegenerator.com/generators/documents/cpf-generator/
#
#  Note: Before using this script set the environment variable.
#
# $ export API_KEY=<API-KEY>
#
#---------------------------------------------------------------------#


curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   { "firstName": "João"
     , "lastName": "Moreira Silva Junior"
     , "cpf": "370.329.298-93"
     , "dateOfBirth": "1990-02-25"  
     , "phones": [
          { "type":  "HOME", "phone": "9-922-2459"}
     ]
   }
EOF

# exit 0

curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Amelia"
     , "lastName": "Wolfgang Strauss"
     , "cpf": "891.655.479-93"
     , "dateOfBirth": "2001-09-24"  
     , "phones": [
          { "type":  "HOME", "phone": "9-926-2159"}
       ]

   }
EOF

curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "João"
     , "lastName":  "Mozart Franco Dias"
     , "cpf": "478.361.889-57"
     , "dateOfBirth": "1980-11-20"  
     , "phones": [
           { "type":  "MOBILE",     "phone": "9-823-2251"}
          ,{ "type":  "COMMERCIAL", "phone": "9-867-1269"}
       ]

   }
EOF

curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "João"
     , "lastName":  "Candido Francico"
     , "cpf": "450.315.402-81"
     , "dateOfBirth": "1982-05-12"  
     , "phones": [
           { "type":  "MOBILE",     "phone": "9-821-2452"}
       ]

   }
EOF



curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Mosche"
     , "lastName":  "Aaaron Cohen"
     , "cpf": "506.526.827-40"
     , "dateOfBirth": "1979-06-11"  
     , "phones": [
           { "type":  "COMMERCIAL",     "phone": "9-261-2122"}
       ]

   }
EOF



curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Youssef"
     , "lastName":  "Adib Khan"
     , "cpf": "977.284.337-41"
     , "dateOfBirth": "1986-12-01"  
     , "phones": [
           { "type":  "HOME",     "phone": "9-165-2596"}
       ]

   }
EOF


curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Cohen"
     , "lastName":  "Sacha Borat"
     , "cpf": "505.686.277-09"
     , "dateOfBirth": "1975-02-22"  
     , "phones": [
           { "type":  "HOME",  "phone": "9-961-2598"}
       ]

   }
EOF

curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Moche"
     , "lastName":  "Levy Cohen"
     , "cpf": "623.435.360-66"
     , "dateOfBirth": "1982-09-12"  
     , "phones": [
           { "type":  "HOME",  "phone": "9-961-2598"}
       ]

   }
EOF


curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Marisol"
     , "lastName":  "Garcia Alvarez"
     , "cpf": "094.852.048-50"
     , "dateOfBirth": "1998-10-11"  
     , "phones": [
           { "type":  "HOME",    "phone": "9-921-2118"}
          ,{ "type":  "MOBILE",  "phone": "9-221-2108"}
       ]

   }
EOF


curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Maria"
     , "lastName":  "Augusta de Souza Hernandez Gonzales"
     , "cpf": "816.171.878-44"
     , "dateOfBirth": "2000-11-01"  
     , "phones": [
           { "type":  "HOME",    "phone": "9-125-1198"}
          ,{ "type":  "MOBILE",  "phone": "9-253-5218"}
       ]

   }
EOF


curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "Laura"
     , "lastName":  "Gomes Antunes de Assuncion"
     , "cpf": "107.166.659-21"
     , "dateOfBirth": "2011-05-01"  
     , "phones": [
           { "type":  "HOME",    "phone": "9-626-2918"}
       ]

   }
EOF

curl -H "X-AUTH-KEY: $API_KEY" -0 -v POST http://localhost:9056/api/v1/people \
     -H 'Content-Type: application/json; charset=utf-8' \
     --data-binary @- << EOF
   {   "firstName": "John"
     , "lastName":  "Von Neumman"
     , "cpf": "336.802.108-76"
     , "dateOfBirth": "1970-12-21"  
     , "phones": [
           { "type":  "HOME",    "phone": "2-134-2116"}
       ]

   }
EOF


