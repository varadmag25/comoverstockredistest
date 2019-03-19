
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


    public class Search2cache {
        public static void main(String[] args) throws IOException {
            //Connecting to Redis server on localhost
            Jedis jedis = new Jedis("redis.osp.test.ostk.com" , 65514);
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
            Date manu;
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("GMT"));
            cal.add(Calendar.DATE, -2);
            Random random = new Random();
            random.nextBoolean();
            for (String fplist : path) {
                File file = new File(fplist);
                BufferedReader rd = new BufferedReader(new FileReader((file)));
                Map<String, String> ky = new HashMap<>();
                String st;
                while ((st = rd.readLine()) != null) {
                    ky.put(st.split(",")[0], st.split(",")[1]);
                }
                String temp = null;
                int sec=1;
                for (Map.Entry<String, String> s1 : ky.entrySet()) {
                    temp = "{\"productId\":" + s1.getKey() + ",\"osp\":"+ random.nextBoolean() +"}";
                    if (!temp.equals(null)) {
                        cal.add(Calendar.MILLISECOND, sec+=100);
                        manu=cal.getTime();
                        long unixdateformat=manu.getTime()/1000;
                        jedis.set(s1.getKey()+":v1", temp);
                        //jedis.set(String.valueOf(unixdateformat), s1.getKey());
                        System.out.println((unixdateformat));
                        jedis.zadd("ospchangeset", unixdateformat, s1.getKey() );
                        i += 1;
                    }
                }
                String s123;
                s123= String.valueOf(i);
                System.out.println("Stored string in redis:: "+ jedis.get("tutorial-name"));
            }


//            String st1;
//            BufferedReader rd123 = new BufferedReader(new FileReader(("/Users/hravi/Downloads/ops/allprodcuts.csv")));
//            while ((st1 = rd123.readLine()) != null) {
//                System.out.println(jedis.get(st1 + ":v1"));
//            }

        }


        // Get the stored data and print it

        //}
    }


