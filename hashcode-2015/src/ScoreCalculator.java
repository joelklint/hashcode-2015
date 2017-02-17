public class ScoreCalculator {
	
	public static int getScore(Pool[] pools) {
		
		int rowCount = 16;
		int poolCount = 45;
		
		int[] lowestPoolCapacity = new int[poolCount];
		for(int i = 0; i < poolCount; i++) {
			lowestPoolCapacity[i] = Integer.MAX_VALUE;
		}
		
		for(int brokenRow = 0; brokenRow < rowCount; brokenRow++) {
		
			for(int poolIndex = 0; poolIndex < pools.length; poolIndex++) {
				Pool pool = pools[poolIndex];
				int poolCapacity = 0;
				for(Server server : pool.servers) {
					if(server.row != brokenRow) {
						poolCapacity += server.capacity;
					}
				}
				
				if(poolCapacity < lowestPoolCapacity[poolIndex]) {
					lowestPoolCapacity[poolIndex] = poolCapacity;
				}
				
			}
		}
		
		int lowestCapacity = Integer.MAX_VALUE;
		for(int capacity : lowestPoolCapacity) {
			if(capacity < lowestCapacity) {
				lowestCapacity = capacity;
			}
		}
		
		return lowestCapacity;
	}
}
