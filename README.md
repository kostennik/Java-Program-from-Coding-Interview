Search-Car-From-Location application API

Actual API-documentation are always here:
src/main/asciidoc

1.For access to data You need to send your username and password and also clientId, clientSecret


    POST /oauth/token HTTP/1.1
    Authorization: Basic Y2xpZW50T25lOnNlY3JldA==
    Host: localhost:8080
    Content-Type: application/x-www-form-urlencoded
    
    grant_type=password&username=myUsername&password=myPassword
    
2.Get Access Token into response from Authentication Server:
    
    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 896
    
    {
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJteVVzZXJuYW1lIiwic2NvcGUiOlsicmVhZCJdLCJvcmdhbml6YXRpb24iOiJteVVzZXJuYW1leXptViIsImV4cCI6MTU5OTczMTg4NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjJmYmQ3MmM1LTI3MjYtNGUwMC1hNTExLTZjY2QzZjI3YWFlYSIsImNsaWVudF9pZCI6ImNsaWVudE9uZSJ9.4H49LqS96PRecX5Q6zMJ6W0hTTG9uwE2d0do7_UQKN0",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJteVVzZXJuYW1lIiwic2NvcGUiOlsicmVhZCJdLCJvcmdhbml6YXRpb24iOiJteVVzZXJuYW1leXptViIsImF0aSI6IjJmYmQ3MmM1LTI3MjYtNGUwMC1hNTExLTZjY2QzZjI3YWFlYSIsImV4cCI6MTU5OTc3MTQ4NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjMzZDUzYTkxLTY1MzktNGYwNS1hMWY0LWI3NDE0MTFmNDNjYiIsImNsaWVudF9pZCI6ImNsaWVudE9uZSJ9.c8lw4u-GW1aJ1_ba5907qD-HoR407vJWsGzaPsOwlMo",
    "expires_in": 3599,
    "scope": "read",
    "organization": "myUsernameyzmV",
    "jti": "2fbd72c5-2726-4e00-a511-6ccd3f27aaea"
    }

3.Done! You have access to the data. Use Yours Access Token:

    http GET 'http://localhost:8080/getCars/53.9/20.88/100' \
        'Authorization:Bearer <your_token>' \
        'Accept:application/json'

4.Get Your data:

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 510
    
    {
      "0" : {
        "carId" : "5b8812a5-0e1b-4bda-adc6-208157beb1ec",
        "coordinate" : {
          "latitude" : 53.8999766066216,
          "longitude" : 20.879609098658
        }
      },
      "1" : {
        "carId" : "29859e75-a3fc-4683-8123-106ddb56ba87",
        "coordinate" : {
          "latitude" : 53.9005019030705,
          "longitude" : 20.881138343039
        }
      },
      "2" : {
        "carId" : "3f11f70e-249a-4a02-a580-bc15bf5e14fe",
        "coordinate" : {
          "latitude" : 53.9001368781674,
          "longitude" : 20.8811688161785
        }
      }
    }
