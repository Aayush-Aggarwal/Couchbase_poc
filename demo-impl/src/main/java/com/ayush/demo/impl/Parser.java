package com.ayush.demo.impl;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketType;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.DefaultBucketSettings;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Parser {
    
    public BucketSettings getBucketSettings() {
        Config conf = ConfigFactory.load("application.conf");
        
        //Create your bucket.....
        BucketSettings sampleBucket = new DefaultBucketSettings.Builder()
                .type(BucketType.COUCHBASE)
                .name(conf.getString("bucket-name"))
                .password("")
                .quota(500) // megabytes
                .replicas(0)
                .indexReplicas(true)
                .enableFlush(true)
                .build();
        
        return sampleBucket;
    }
    
    public Bucket loadBucket(Cluster cluster) {
        
        Config conf = ConfigFactory.load("application.conf");
        
        cluster.clusterManager(conf.getString("couchbase.cluster.username"), conf.getString("couchbase.cluster.password"))
                .insertBucket(getBucketSettings());
        Bucket bucket = cluster.openBucket(conf.getString("bucket-name"));
        return bucket;
    }
    
    public Cluster couchbaseConnector() {
        Config configuration = ConfigFactory.load("application.conf");
        Cluster cluster;
        try {
            // Initialize the Connection
            cluster = CouchbaseCluster.create(configuration.getString("couchbase_contact_point_one"));
            cluster.authenticate(configuration.getString("couchbase.cluster.username"),
                    configuration.getString("couchbase.cluster.password"));
            return cluster;
        } catch (CouchbaseException ex) {
            return null;
        }
    }
}
