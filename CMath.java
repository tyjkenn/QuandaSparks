import java.util.*;

class CMath {
	
	public void CMath() {
	}
	
	public static List<Integer> mode(List<Integer> list) {
		
		Hashtable<Integer, Integer> occurances = new Hashtable<Integer, Integer>();
		Integer count;

		// Build a hashtable that maps each number from the list with the number of times it occurs.
		for (Integer d : list) {
			count = occurances.get(d);
			if (count == null) {
				count = 0;
			}
	
			count += 1;
			occurances.put(d, count);
		}
		
		List<Integer> results = new ArrayList<Integer>();
		int highestCount = 0;
		for (Integer d : occurances.keySet()) {
			if (occurances.get(d) > highestCount) {
				highestCount = occurances.get(d);
				results.clear();
				results.add(d);
			} else if (occurances.get(d) == highestCount) {
				results.add(d);
			}
			
		}
		return results;
	}
}
