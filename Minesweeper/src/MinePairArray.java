public class MinePairArray {
	
	private int length;
	private MinePair[] pairArray;
	private int numCount = 0;
	
	public MinePairArray(int length) {
		this.length = length;
		pairArray = new MinePair[length];
	}
	
	public int getNumCount() {
		return numCount;
	}
	
	public MinePair[] getPairArray() {
		return pairArray;
	}
	
	public boolean isMember(MinePair pair) {
		if (numCount > 0) {
			for (int i = 0; i < numCount; i++) {
				if (pair.isEqual(pairArray[i])) { return true; }
			}
		}
		return false;
	}
	
	public void addPair(MinePair pair) {
		if (!isMember(pair)) {
			if (numCount < length) {
				pairArray[numCount] = pair;
				numCount++;
			} else {
				int newLength = length*2;
				MinePair[] newArray = new MinePair[newLength];
				for (int i = 0; i < numCount; i++) {
					newArray[i] = pairArray[i];
				}
				newArray[numCount] = pair;
				numCount++;
				this.pairArray = newArray;
				this.length = newLength;
			}
			
		}
	}
	
	public MinePairArray union(MinePairArray pArray) {
		for (int i=0; i < pArray.getNumCount(); i++) {
			MinePair pair = pArray.getPairArray()[i];
			this.addPair(pair);
		}
		return this;
	}
	

}