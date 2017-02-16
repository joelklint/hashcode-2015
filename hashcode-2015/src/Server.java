
public class Server implements Comparable<Server> {
	public int id;
	public int size;
	public int capacity;
	public int pool;
	public int ratio;
	
	
	public Server(int id, int size, int capacity, int pool, int ratio){
		this.id = id;
		this.size = size;
		this.capacity = capacity;
		this.pool = pool;
		this.ratio = ratio;
	}


	@Override
	public int compareTo(Server o) {
		
		return o.ratio - ratio;
	}
	
}
