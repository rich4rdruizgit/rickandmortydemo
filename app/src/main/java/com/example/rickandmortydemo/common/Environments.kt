package com.example.rickandmortydemo.common

 class Environments(debug: Boolean = false,
                    url: String = URL_TEST,
                    grantType: String = GRANT_TYPE_TEST,
                    clientId: String = CLIENT_ID_TEST,
                    scope: String = SCOPE_TEST,
                    clientSecret: String = CLIENT_SECRET_TEST,
                    subscriptionKey: String = SUBSCRIPTION_KEY_TEST){

        val debug: Boolean = debug

        val url: String = url
               get() { if(field.isEmpty()) throw ExampleException("url mustn't be empty") else return  field }
        val grantType: String = grantType
               get() {if(field.isEmpty()) throw ExampleException("grantType mustn't be empty") else return  field}
        val clientId: String = clientId
               get() {if(field.isEmpty()) throw ExampleException("clientId mustn't be empty") else return  field}
        val scope: String = scope
               get() {if(field.isEmpty()) throw ExampleException("scope mustn't be empty") else return  field}
        val clientSecret: String = clientSecret
               get() {if(field.isEmpty()) throw ExampleException("clientSecret mustn't be empty") else return  field}
        val subscriptionKey: String = subscriptionKey
               get() {if(field.isEmpty()) throw ExampleException("subscriptionKey mustn't be empty") else return  field}

 }



