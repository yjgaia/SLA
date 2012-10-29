import redis.clients.jedis.Jedis;



public class RedisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis=new Jedis("1.234.80.226");
		jedis.auth("ekfrrhrl0");
		System.out.println(jedis.get("test"));
		jedis.del("cFc");
	}

}
