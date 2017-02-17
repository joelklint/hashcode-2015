public class ServerHall {
	
	public int[][] rowsAndSlots = new int[16][100];
	public boolean full = false;
	public int pool = 0;
	
	private int[] nextIndex = new int[16];
	private Pool[] pools;
	private int poolIndex = 0;
	
	public ServerHall(int[][] initial, Pool[] pools) {
		this.rowsAndSlots = initial;
		this.pools = pools;
	}
	
	public boolean placeServer(Server server, int row) {
		if(full) {
			return false;
		}
		
		// Is it possible to place?
		for(int rowIndex = nextIndex[row]; rowIndex < nextIndex[row] + server.size; rowIndex++) {
			
			// Prevent index out of bounds
			if(rowIndex > 99) {
				return false;
			}
			
			//Jump impossible slots
			if(rowsAndSlots[row][rowIndex] == -1) {
				nextIndex[row] = rowIndex + 1;
			}
			
			if(rowsAndSlots[row][rowIndex] == -1) {
				return false;
			}
		}
		
		//Place server
		for(int rowIndex = nextIndex[row]; rowIndex < nextIndex[row] + server.size; rowIndex++) {
			rowsAndSlots[row][rowIndex] = server.id;
		}
		server.row = row;
		
		
		server.pool = this.pool%45;
		pools[poolIndex%45].servers.add(server);
		
		poolIndex++;
		
		nextIndex[row] += server.size;
		
		return true;
	}
}
