package datastructure;
public class Array <K, V> {

	private final HashElement <K, V>[] hashMap;
	private final int m;
	private int spaceUsed;
	
	@SuppressWarnings("rawtypes")
	public Array( Class<HashElement> class1,int m ) {
		@SuppressWarnings("unchecked")
		HashElement <K,V> [] newInstance = (HashElement <K,V> []) java.lang.reflect.Array.newInstance(class1, m);
		this.hashMap = newInstance;
		spaceUsed=0;
		this.m=m;
	}
	
	// get element at objArray[i]
    HashElement<K,V> get(int i) {
        return hashMap[i];
    }
    // assign e to objArray[i]
    void set(int i, HashElement<K,V> e) {
        hashMap[i] = e;
        spaceUsed++;
    }
    
    // return the length of the array
    int length() {
    	return m;
    }
    
    // delete an e element to array[i]
    boolean delete(int index, K key) {
    	boolean deleted=false;
    	
    	if(hashMap[index]!=null) {
    		HashElement <K,V> aux= hashMap[index];
    		if(aux.getKey().equals(key)) { 	 		///// First Node to be deleting
				if(aux.getNext()!=null) {
					hashMap[index]=hashMap[index].getNext();
					aux.getNext().setPrev(null);
					spaceUsed--;
					return true;
				}else {
					hashMap[index]=null;
					spaceUsed--;
					return true;
				}
			}else {								     ///// When the node is not the first
				while(aux.getNext()!=null && !deleted) {
					deleted= ((aux.getKey().equals(key))?true:false);
					aux=aux.getNext();
				}
				if(deleted==true) {
					if(aux.getNext()!=null) { 	     ////Verifies if the node is the last one or if it is in the middle
						aux.getPrev().setNext(aux.getNext());
						aux.getNext().setPrev(aux.getPrev());
						spaceUsed--;
					}else{	 						//// This happen when the node is the last one
						aux.getPrev().setNext(null);
						spaceUsed--;
					}
				}
			}
    	}		
    	return deleted;
    }
    
    int getSpaceUsed() {
    	return spaceUsed;
    }
}
