import java.util.LinkedList;
import java.util.Random;

public class DataLoader {
	int[][] rowsAndSlots;
	Server[] servers;

	public DataLoader(int[][] rowsAndSlots, Server[] servers) {
		this.rowsAndSlots = rowsAndSlots;
		this.servers = servers;
	}

	public int buildMatrixFirstSolution() {

		
		
		Random r = new Random();
		
		//Randomly place pools
		int[] filled = new int[45];
		for(Server s : servers){
			int randomPool = r.nextInt(45);
			filled[randomPool] = 1;
			s.pool = randomPool;
		}
		
		
		// Copy servers to temporary server array
		LinkedList<Server> takenServers = new LinkedList<Server>();
		int i = 0;
		for (Server s : servers) {
			Server serverCopy = new Server(s.id, s.size, s.capacity, s.pool, -1);
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
					
					if(nbrOfTries > 2000) {
						
						rowIndex = skipUntilNegativeEnd(row, rowIndex);
						nbrOfTries = 0;
					}
				}

			}
			
			Pool[] pools = new Pool[45];
			for(int index = 0; index < pools.length; index++) {
				pools[index] = new Pool();
			}
			for(Server server : servers) {
				pools[server.pool].servers.add(server);
			}
					
		int score = ScoreCalculator.getScore(16, 45, pools);
		//System.out.println(score);
		return score;
	}
	
	private int slotsLeft(int row, int col) {
		int slots = 0;
		for (int i = col; i < 99; i++) {
			if (rowsAndSlots[row][i] == -1) {
				break;
			}
			slots++;
		}
		return slots;
	}
	
	private boolean skipNegatives(int row, int col) {
		if (rowsAndSlots[row][col] == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private int skipUntilNegativeEnd(int row, int rowIndex) {
		while(rowsAndSlots[row][rowIndex] != -1 && rowIndex < 99) {
			rowIndex++;
		}
		while(rowsAndSlots[row][rowIndex] == -1 && rowIndex < 99) {
			rowIndex++;
		}
		return rowIndex;
	}
}