package com.ayush.demo.api;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;


import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

public interface DemoService extends Service {
    
    ServiceCall<User, Done> insertUser();
    
    
    @Override
    default Descriptor descriptor() {
        return named("couchbase").withCalls(
                restCall(Method.POST, "/api/demo/insert ", this::insertUser)
        ).withAutoAcl(true);
    }
}
