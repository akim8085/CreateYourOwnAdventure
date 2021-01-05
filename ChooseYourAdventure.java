package cyoa;

public class ChooseYourAdventure {
	//The limit of how many elements the story can have. We 
	//can add more if we want by manipulating this
	int maxElements = 101;
	//Unweighted Directed Graph to store our elements 
	//and where they point to
	ElementLinkedList[] story = new ElementLinkedList[maxElements];
	//Keeps track of the number of vertices and where to add
	//Things in the ElementLinkeList array
	private int numOfElements = 0;
	//Keeps track of where the endings are stored in the array
	private int endingsPointer = maxElements-1;
	//Keeps track of the start element premanantly
	private Element startText;
	//Keeps track of the ending array permanantly
	private String[] endings;
	//Initial history
	String history = "The Beginning";
	
	//Constructor
	public ChooseYourAdventure(String start, String[] endings) {
		//Loops through and initializes the linked lists
		//at all array positions so we avoid null pointers.
		for(int i = 0; i < maxElements; i++) {
			ElementLinkedList temp = new ElementLinkedList();
			story[i] = temp;
		}
		//Create an initial element object to add
		Element startEle = new Element(start, "Start", 0);
		startText = startEle;
		story[0].head = startEle;
		this.endings = endings;
		numOfElements++;
		//Ending counter will help us create the title for endings
		int endingCounter =1;
		//Loop through and add endings to the back of the array
		for(int i = 0; i < endings.length; i++) {
			story[endingsPointer].head = new Element(endings[i], "Ending " + endingCounter, endingsPointer);
			story[endingsPointer].head.ending = true;
			endingCounter++;
			endingsPointer--;
		}
	}
	
	//Givs the number of elements currently in the CYOA
	//returns the tracker
	//Input:none
	//Output: int number of elements
	//Time: O(1)
	//Space:O(1)
	public int numberOfElements() {
		return numOfElements;
	}
	
	//adds a vertices/element to the graph
	//Input: Element object we want to add
	//Output: nonde
	//Time: O(1) <- directly accessing array at specific index
	//Space:O(1)
	public void addElement(Element newElement) {
		if(endingsPointer == numOfElements) {
			System.out.println("Error: Trying to rewrite ending");
		}else {
			story[numOfElements].head = newElement;
			numOfElements++;
		}
	}
	
	//Add a link at the desired source to destination element
	//Use the first element and second element id to add them
	//Input: source id and destination id
	//Output: none
	//Time:O(V) b/c we are looping through to find index of elements
	//Space: O(1)
	public void addLink(int firstElementId, int secondElementId) {
		int indexOfFirstElement = findIndexOf(firstElementId);
		int indexOfSecondElement = findIndexOf(secondElementId);
		
		//If checks are there so that we cant link more than 2 elements to one source element
		if(indexOfFirstElement != -1 && story[indexOfFirstElement].getListSize() > 3) {
			System.out.println("Origin Element already has 2 paths");
			
		}else if(indexOfFirstElement != -1 && story[indexOfFirstElement].getListSize() < 3){
			
			if(story[indexOfFirstElement].choiceA ==null) {
				story[indexOfFirstElement].choiceA = story[indexOfSecondElement].head;
				//Adding to the linked to String to keep track of what source id links to
				story[indexOfFirstElement].head.linkedTo = story[indexOfFirstElement].head.linkedTo +
						" "+ secondElementId;
			}else {
				story[indexOfFirstElement].choiceB = story[indexOfSecondElement].head;
				//Adding to the linked to String to keep track of what source id links to
				story[indexOfFirstElement].head.linkedTo = story[indexOfFirstElement].head.linkedTo +
						" "+ secondElementId;
			}
			
		}else if(indexOfFirstElement == -1 || indexOfSecondElement == -1) {
			System.out.println("Link Index Error");
		}
	}
	
	//remove an element from the list
	//Input: source id and destination id
	//Output: none
	//Time:O(V+L) b/c we are looping throught changing the elements
	//            and then looping through each list at each index
	//            to get rid of dead links
	//Space: O(1)
	public void removeElement(int elementId) {
		if (elementId == 0 || elementId >= endingsPointer) {
			System.out.println("Error: Trying delete permanent element");
		}else {
			int index = findIndexOf(elementId);
			for(int i = index; i < numOfElements; i++) {
				story[i] = story[i+1];
			}
			
			numOfElements--;
		
			for(int i = 0; i < numOfElements; i++) {
				if(story[i].choiceA!= null &&story[i].choiceA.elementId == elementId) {
					story[i].choiceA = null;
					story[i].listSize--;
				}
				if(story[i].choiceA!= null &&story[i].choiceB.elementId == elementId) {
					story[i].choiceB = null;
					story[i].listSize--;
				}
			}
			System.out.println("Removed: " + elementId);
		}
	}
	
	//remove link given source id and destination id
	//Input: source id and destination id
	//Output: none
	//Time:O(V) b/c we are finding the index of source id
	//Space: O(1)
	public void removeLink(int sourceId, int destinationId) {
		int index = findIndexOf(sourceId);
		if(story[index].choiceA.elementId == destinationId) {
			story[index].choiceA = null;
			story[index].listSize--;
		}else {
			story[index].choiceB = null;
			story[index].listSize--;
		}
		System.out.println("Deleted link: "+ sourceId + "->"+ destinationId);
	}
	
	//Print out the history of all our choices
	//We have a string tracker for it
	//Input: none
	//Output: none
	//Time: O(1)
	//Space: O(1)
	public void choiceHistory() {
		 System.out.print(history);
	}
	
	
	//Prints out all the ending stored in the endings indices
	//Input:none
	//Output: none
	//Time:O(k) k is the amount of endings we have
	//Space: O(1);
	public void endings() {
		for(int i = endingsPointer+1 ; i < 101; i++) {
			System.out.print("Id/index: " + story[i].head.elementId +
					", Title: "+ story[i].head.title + " -- ");
		}
		System.out.println();
	}
	
	//Find the index of a element given its id
	//We need to loop trhough the array which means
	//The amount of elements
	//Input: element id to find
	//Output: int number of index of where the element is at
	//Time: O(V+E) <- elements and endings
	//Space:O(1)
	public int findIndexOf(int elementId) {
		int index = -1;
		if(elementId < numOfElements) {
			for(int i = 0; i< numOfElements; i++) {
				if(story[i].head.elementId == elementId){
					index = i;
					break;
				}
			}
			return index;
		} else {
			for(int i = 100; i >endingsPointer ; i--) {
				if(story[i].head.elementId == elementId){
					index = i;
					break;
				}
			}
			return index;
		}
	}
}
