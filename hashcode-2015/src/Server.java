
public class Server implements Comparable<Server> {
	public int id;
	public int size;
	public int capacity;
	public int pool;
	public int ratio;
	public int row;
	
	
	public Server(int id, int size, int capacity, int pool, int ratio, int row){	
		this.id = id;
		this.size = size;
		this.capacity = capacity;
		this.pool = pool;
		this.ratio = ratio;
		this.row = row;
	}


	@Override
	public int compareTo(Server o) {
		
		return o.ratio - ratio;
	}
	
}
