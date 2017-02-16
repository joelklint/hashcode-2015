
public class ServerHall {
	
	int[][] serverHall = new int[16][100];
	int[] nextIndex = new int[16];
	public boolean full = false;
	public int pool = 0;
	Pool[] pools;
	int poolIndex = 0;
	
	public ServerHall(int[][] initial, Pool[] pools) {
		this.serverHall = initial;
		this.pools = pools;
	}
	
	public boolean placeServer(Server server, int row) {
		// Is it possible to place?
		for(int rowIndex = nextIndex[row]; rowIndex < nextIndex[row] + server.size; rowIndex++) {
			// Prevent index out of bounds
			if(rowIndex > 99) {
				return false;
			}
			
			// Mark serverhall as full
			//if(row == 15 && rowIndex == 99) {
			//	full = true;
			//	return false;
			//}
			
			//Jump impossible slots
			if(serverHall[row][rowIndex] == -1) {
				nextIndex[row] = rowIndex + 1;
			}
			
			if(serverHall[row][rowIndex] == -1) {
				return false;
			}
		}
		
		//Place server
		for(int rowIndex = nextIndex[row]; rowIndex < nextIndex[row] + server.size; rowIndex++) {
			serverHall[row][rowIndex] = server.id;			
		}
		server.pool = this.pool%45;
		pools[poolIndex%45].servers.add(server);
		poolIndex++;
		
		
		nextIndex[row] += server.size;
		
		return true;
	}
	
	public void print() {
		for (int i = 0; i < serverHall.length; i++) {
		    for (int j = 0; j < serverHall[i].length; j++) {
		        System.out.print(serverHall[i][j] + " ");
		    }
		    System.out.println();
		}
	}

}
