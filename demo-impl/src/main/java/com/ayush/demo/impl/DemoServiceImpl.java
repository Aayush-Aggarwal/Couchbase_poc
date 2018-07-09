package com.ayush.demo.impl;

import akka.Done;
import com.ayush.demo.api.DemoService;
import com.ayush.demo.api.User;
import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketType;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.DefaultBucketSettings;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class DemoServiceImpl implements DemoService {
    
    //private Cluster cluster;
    //private Bucket bucket;
    private ObjectMapper mapper = new ObjectMapper();
    Parser parser = new Parser();
    
    
    public DemoServiceImpl() {
        //Parser parser = new Parser();
        Cluster cluster = parser.couchbaseConnector();
        parser.loadBucket(cluster);
       // cluster = couchbaseConnector();
       // loadBucket(cluster);
    }
    
    @Override
    public ServiceCall<User, Done> insertUser() {
        return req -> {
            String data;
            JsonNode jsonNode = null;
            try {
                data = mapper.writeValueAsString(req);
                jsonNode = mapper.readTree(data);
                
            } catch (IOException ex) {
                ex.getMessage();
            }
            
            JsonObject jsonObject = JsonObject.fromJson(jsonNode.toString());
            //bucket.upsert(JsonDocument.create("id", jsonObject));
            parser.loadDocument(jsonObject);
            return CompletableFuture.completedFuture(Done.getInstance());
        };
    }
   
}
