import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class computes a shortest path from the UWO store cell to the customer cell.
 * Each map cell has up to 6 neighboring cells with index from 0 to 5.
 * @author gaoge
 */
public class FindShortestPath {
	
	/**
	 * Instance variable cityMap, representing a map of city.
	 */
	Map cityMap;
	
	/**
	 * Constructor. Create a new Map object.
	 * @param filename
	 * @throws InvalidMapException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public FindShortestPath(String filename) throws InvalidMapException, FileNotFoundException, IOException
	{
		cityMap = new Map(filename);
	}
	
	/**
	 * Interference method return true if any adjacent cell the the current cell is a tower cell.
	 * @param cell
	 * @return true if adjacent cell is a tower cell and false otherwise.
	 */
	private boolean interference(MapCell cell)
	{
		for (int i = 0; i < 6; i++)
		{
			MapCell neighbour = cell.getNeighbour(i);
			if (neighbour != null && neighbour.isTower())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Main method find the shortest path for a drone to move from UWO to the customer's house.
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (args.length != 1)  //Throw a exception if the argument is illegal.
		{
			throw new IllegalArgumentException("Provide the name of the file with the input map!");
		}
		
		try
		{
			String mapFileName = args[0];
			FindShortestPath map = new FindShortestPath(mapFileName);
			
			DLPriorityQueue<MapCell> queue = new DLPriorityQueue<MapCell>();
			MapCell initialcell = map.cityMap.getUWOstore();  //Get the initial cell.
			queue.enqueue(initialcell, 0);  //Add it in the queue.
			initialcell.markEnqueued();  //Marked.
			MapCell current = initialcell;  //A reference to represent the current cell.
			
			while (!queue.isEmpty() && !current.isCustomer())
			{
				 MapCell result = queue.getSmallest();  //Remove the cell with smallest priority from the queue.
				 result.markDequeued();
				 if (result.isCustomer())
				 {
					 break;  //Get out of the while loop.
				 }
				 if (map.interference(result))
				 {
					 continue;  //Ignore the rest and loop from the beginning again.
				 } else {
					 for (int i = 0; i < 6; i++)
					 {
						 MapCell neighbourcell = result.getNeighbour(i);
						 if (neighbourcell != null && !neighbourcell.isNoFlying() && !neighbourcell.isMarkedDequeued())
						 {
							 int distance = 1 + result.getDistanceToStart();  //distance from the initial cell.
							 if (neighbourcell.getDistanceToStart() > distance)
							 {
								 neighbourcell.setDistanceToStart(distance);
								 neighbourcell.setPredecessor(result);
								 double overallLength = neighbourcell.getDistanceToStart() + neighbourcell.euclideanDistToDest(map.cityMap);  //An estimate of overall length.
								 if (neighbourcell.isMarkedEnqueued() && overallLength < queue.getPriority(neighbourcell))
								 {
									 queue.changePriority(neighbourcell, overallLength);
								 }
								 if (!neighbourcell.isMarkedEnqueued())
								 {
									 queue.enqueue(neighbourcell, overallLength);
									 neighbourcell.markEnqueued();
								 }
							 }
						 }
					 }
				 }
			}
			if (!queue.isEmpty())
			{
				System.out.println("The destination is reached through a path of minimum distance " + (queue.numItems()+1) + ".");
			} else {
				System.out.println("The destination is not reached.");
			}
		} catch(Exception IllegalArgumentException) {
			System.out.println("File not found!");
		}
	}
	
}
