package com.overstock.redissitesalecache.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.*;
import java.net.UnknownHostException;
import java.util.*;

import java.net.UnknownHostException;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;


    public class PrismCache {
        public static void main(String[] args) throws IOException {
            //Connecting to Redis server on localhost
            //Jedis jedis = new Jedis("redis.osp.test.ostk.com" , 65534);
            Jedis jedis = new Jedis("redis.osp.test.ostk.com" , 30000);
            jedis.auth("redis");
            //jedis.configSet("timeout", "1");

            System.out.println("Connection to server sucessfully");
            //set the data in redis string
            jedis.flushAll();

            List<String> path =new ArrayList<>();
            path.add("/Users/mvaradharajan/Downloads/osp/Safavieh-redis.csv");
            path.add("/Users/mvaradharajan/Downloads/osp/Nuloom-redis.csv");
            path.add("/Users/mvaradharajan/Downloads/osp/Nourison-redis.csv");
            path.add("/Users/mvaradharajan/Downloads/osp/LOLOI-redis.csv");
            int i = 1;
            for (String fplist : path) {
                File file = new File(fplist);
                BufferedReader rd = new BufferedReader(new FileReader((file)));
                Map<String, String> ky = new HashMap<>();
                String st;
                while ((st = rd.readLine()) != null) {
                    ky.put(st.split(",")[0], st.split(",")[1]);
                }
                String temp = null;
                for (Map.Entry<String, String> s1 : ky.entrySet()) {
                    if (fplist.contains("Safavieh")) {
                        temp = "{\"campaignId\":1,\"partnerId\":658,\"sponsoredProductId\":" + i + ",\"productId\":" + s1.getKey() + ",\"sku\":" + s1.getValue() + ",\"enabled\":true,\"maxBid\":0.85,\"budget\":{\"allocation_id\":1,\"startTime\":2019-03-25T12:33:18,\"endTime\":null,\"currency\":USD,\"allocated\":250.00,\"spent\":100.00,\"remaining\":150.00}}";
                    }else if (fplist.contains("Nuloom")) {
                        temp = "{\"campaignId\":2,\"partnerId\":84234,\"sponsoredProductId\":" + i + ",\"productId\":" + s1.getKey() + ",\"sku\":" + s1.getValue() + ",\"enabled\":true,\"maxBid\":1.50,\"budget\":{\"allocation_id\":2,\"startTime\":2019-03-26T12:33:18,\"endTime\":null,\"currency\":USD,\"allocated\":250.00,\"spent\":110.00,\"remaining\":140.00}}";
                    }else if(fplist.contains("Nourison")) {
                        temp = "{\"campaignId\":3,\"partnerId\":699,\"sponsoredProductId\":" + i + ",\"productId\":" + s1.getKey() + ",\"sku\":" + s1.getValue() + ",\"enabled\":true,\"maxBid\":0.80,\"budget\":{\"allocation_id\":3,\"startTime\":2019-03-27T12:33:18,\"endTime\":null,\"currency\":USD,\"allocated\":700.00,\"spent\":500.00,\"remaining\":200.00}}";
                    }else if(fplist.contains("LOLOI")) {
                        temp = "{\"campaignId\":4,\"partnerId\":84644,\"sponsoredProductId\":" + i + ",\"productId\":" + s1.getKey() + ",\"sku\":" + s1.getValue() + ",\"enabled\":true,\"maxBid\":0.87,\"budget\":{\"allocation_id\":4,\"startTime\":2019-03-28T12:33:18,\"endTime\":2019-03-30T12:33:18,\"currency\":USD,\"allocated\":466.00,\"spent\":200.00,\"remaining\":266.00}}";
                    }
                    if (!temp.equals(null)) {
                        jedis.set(s1.getKey(), temp);
                        i += 1;
                    }
                }
                String s123;
                s123= String.valueOf(i);
                System.out.println("Stored string in redis::");
            }
        }




        // Get the stored data and print it

        //}
    }






