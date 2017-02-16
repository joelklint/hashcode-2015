import java.util.LinkedList;
import java.util.Random;

public class DataLoader {
	int[][] rowsAndSlots;
	Server[] servers;

	public DataLoader(int[][] rowsAndSlots, Server[] servers) {
		this.rowsAndSlots = rowsAndSlots;
		this.servers = servers;
	}

	public int[][] buildMatrixFirstSolution() {

		Random r = new Random();

		//Copy servers to temporary server array
		LinkedList<Server> takenServers = new LinkedList<Server>();
		int i = 0;
		for (Server s : servers) {
			Server serverCopy = new Server(s.id, s.size, s.capacity, s.pool);
			takenServers.add(serverCopy);
			i++;
		}
		
		while (true) { //TODO: When matrix is full, break
			
			Server currentServer = takenServers.pop();
			
			for (int row = 0; row < 16; row++) {
				
				for (int slots = 0; slots < 100; slots += currentServer.size) {
					while (100 - slots > 0){}
					
				}
			}
		}

		return rowsAndSlots;
	}
}