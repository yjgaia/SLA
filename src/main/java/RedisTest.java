import redis.clients.jedis.Jedis;



public class RedisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis=new Jedis("yog.io");
		jedis.auth("ekfrrhrl0");
		System.out.println(jedis.get("test"));
		jedis.del("cFc");
	}

}
