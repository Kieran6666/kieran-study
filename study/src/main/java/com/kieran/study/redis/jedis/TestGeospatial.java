package com.kieran.study.redis.jedis;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 重复的member会被后一个覆盖，可以用zset的命令来查询
 */
public class TestGeospatial extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());
        jedis.geoadd("j:geospatial", 118.796877d, 32.060255d, "nanjing");
        jedis.geoadd("j:geospatial", 121.473701d, 31.230416d, "shanghai");
        jedis.geoadd("j:geospatial", 121.473701d, 31.230216d, "shanghai");

        List<GeoCoordinate> shanghai = jedis.geopos("j:geospatial", "shanghai");
        System.err.println(shanghai.toString());

        List<String> zrange = jedis.zrange("j:geospatial", 0, -1);
        System.err.println(zrange);

        long zcard = jedis.zcard("j:geospatial");
        System.err.println(zcard);

        jedis.close();
    }
}
