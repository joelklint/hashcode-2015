import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class RatioAlgorithm {
	
	LinkedList<Server> servers = new LinkedList<Server>();
	ServerHall serverHall;
	public Pool[] pools = new Pool[45];
	
	
	public RatioAlgorithm(Reader reader) {
		
		Server[] servers = reader.servers;
		Arrays.sort(servers);
		//this.servers = (LinkedList<Server>) Arrays.asList(servers);
		Collections.addAll(this.servers, servers);
		this.serverHall = new ServerHall(reader.unavailable, pools);
	}
	
	
	public void performAlgorithm() {
		
		//for(Server server : servers) {
		//	System.out.println(server.ratio);
		//}
		
		//LinkedList<Server> rejected = new LinkedList<Server>();
		
		// servers come pre sorted
		
		int row = 0;
		
		while(!serverHall.full && !servers.isEmpty()) {
			serverHall.placeServer(servers.pop(), row%16);
			row++;
		}
		
		// servers are kinda well placed among rows
		
		//Pools are placed in serverhall class
		
		
		serverHall.print();
		
	}
	//sortera alla servrar efter storlek
	
	//forloopa servrarna - lägg första på rad 1, går inte det så försök med nästa, dvs. stanna på raden tills vi placerat en server där.
 

	
}
