package com.ayush.demo.impl;

import akka.Done;
import akka.NotUsed;
import com.ayush.demo.api.DemoService;
import com.ayush.demo.api.User;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class DemoServiceImpl implements DemoService {
    
    private Parser parser = new Parser();
    private Bucket bucket;
    private ObjectMapper mapper = new ObjectMapper();
    
    public DemoServiceImpl(){
        Cluster cluster = parser.couchbaseConnector();
        bucket = parser.loadBucket(cluster);
    }
    
    @Override
    public ServiceCall<User, Done> insertUser() {
        /*return req -> {
            String data;
            JsonNode jsonNode = null;
            try{
                data = mapper.writeValueAsString(req);
                jsonNode = mapper.readTree(data);
            
            }catch (IOException ex){
                ex.getMessage();
            }
        
            JsonObject jsonObject = JsonObject.fromJson(jsonNode.toString());
            bucket.upsert(JsonDocument.create("id", jsonObject));
        
            return CompletableFuture.completedFuture(Done.getInstance());
        };*/
        return null;
    }
}
