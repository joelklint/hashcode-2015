import java.util.LinkedList;
import java.util.Random;

public class RandomPoolsAlgorithm extends RandomAlgorithm {
	
	public RandomPoolsAlgorithm(int[][] rowsAndSlots, Server[] servers) {
		super(rowsAndSlots, servers);
		placeServers();
	}

	@Override
	public void performAlgorithm() {
		Random r = new Random();
		
		// Randomly place pools
		int[] filled = new int[45];
		for(Server s : servers){
			int randomPool = r.nextInt(45);
			filled[randomPool] = 1;
			s.pool = randomPool;
		}
		
		// Arrange servers into their specific pool
		pools = new Pool[45];
		for(int index = 0; index < pools.length; index++) {
			pools[index] = new Pool();
		}
		for(Server server : servers) {
			pools[server.pool].servers.add(server);
		}
	}
	
	private void placeServers() {
		Random r = new Random();
		// Copy servers to temporary server array
		LinkedList<Server> takenServers = new LinkedList<Server>();
		int i = 0;
		for (Server s : servers) {
			Server serverCopy = new Server(s.id, s.size, s.capacity, s.pool, 0, -1);
			takenServers.add(serverCopy);
			i++;
		}

		//Add to each row
		for (int row = 0; row < 16; row++) {
			
			int nbrOfTries = 0;
			int slotsLeft = 99;
			int rowIndex = 0;

			while (slotsLeft > 0 && rowIndex < 99) {
				
				// Random number to fetch server
				int randIndex = r.nextInt(takenServers.size() - 1);
				Server server = takenServers.get(randIndex);
				
				// try if the server fits
				if (server.size < slotsLeft(row, rowIndex)) {
					server.row = row;
					for (i = rowIndex; i < rowIndex + server.size; i++) {
						rowsAndSlots[row][i] = server.id;
					}
					takenServers.remove(randIndex);
					rowIndex += server.size;
					
					while (skipNegatives(row, rowIndex)) {
						rowIndex ++;
					}
				}
				
				nbrOfTries++;
				
				// We give up trying and skipping to next available space
				if(nbrOfTries > 100) {
					rowIndex = skipUntilNegativeEnd(row, rowIndex);
					nbrOfTries = 0;
				}
			}
		}
	}
}