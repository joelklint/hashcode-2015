import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class RatioAlgorithm extends Algorithm {
	
	private Pool[] pools = new Pool[45];
	
	private ServerHall serverHall;
	private LinkedList<Server> servers = new LinkedList<Server>();
	
	public RatioAlgorithm(Reader reader) {
		Server[] servers = reader.servers;
		Arrays.sort(servers);
		
		Collections.addAll(this.servers, servers);
		for(int i = 0; i < 45; i++) {
			pools[i] = new Pool();
		}
		
		this.serverHall = new ServerHall(reader.unavailable, pools);
	}
	
	
	public void performAlgorithm() {
		LinkedList<Server> rejected = new LinkedList<Server>();
		
		// servers come pre sorted
		
		int row = 0;
		while(!serverHall.full && !servers.isEmpty()) {
			
			boolean success = false;
			while(!success ) {
				int bellend = 0;
				Server server = null;
				try {
					server = servers.pop();
				} catch (Exception e) {
					serverHall.full = true;
					break;
				}

				success = serverHall.placeServer(server, row%16);
				
				if(!success) {
					rejected.addLast(server);
				}
			}
			
			// Server was successfully placed
			// move rejected back to servers
			while(!rejected.isEmpty()) {
				servers.addFirst(rejected.removeLast());
			}
			
			row++;
		}
		
		// servers are kinda well placed among rows
		
		//Pools are placed in serverhall class
		
		//serverHall.print();
		
	}
	//sortera alla servrar efter storlek


	@Override
	public Pool[] getPools() {
		return pools;
	}
	
	//forloopa servrarna - lägg första på rad 1, går inte det så försök med nästa, dvs. stanna på raden tills vi placerat en server där.
}
