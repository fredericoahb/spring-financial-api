package br.com.fredericoahb.financial.model.enumeration;

import ch.qos.logback.core.util.Duration;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

public enum APIUsagePlansEnum {
	
	FREE(20),
    BASIC(40),
    PROFESSIONAL(100);

    private int bucketCapacity;
    
    private APIUsagePlansEnum (int bucketCapacity) {
        this.bucketCapacity = bucketCapacity;
    }
    
    public Bandwidth getLimit() {
        return Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofHours(1)));
    }
    
    public int bucketCapacity() {
        return bucketCapacity;
    }
    
    public static APIUsagePlansEnum resolvePlanFromApiKey(String apiKey) {
        
    	if (apiKey == null || apiKey.isEmpty()) {
            return FREE;
        
        } else if (apiKey.startsWith("PX001-")) {
            return PROFESSIONAL;
            
        } else if (apiKey.startsWith("BX001-")) {
            return BASIC;
        }
        return FREE;
    }

}