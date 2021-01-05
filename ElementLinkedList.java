package cyoa;

//We use this to create a list of elements. This is used for the graph.
//We limit the size of the list to only 3: head, choice a and choice b.
public class ElementLinkedList {
	//Head contains the origin node information
	Element head;
	//Choice a contains the story information for 
	//The first element our origin points to
	Element choiceA;
	//Choice b contains the story information for 
	//The second element our origin points to
	Element choiceB;
	int listSize = 0;
	
	//Constructors
	public ElementLinkedList(){
	}
	public ElementLinkedList(Element baseNode) {
		head = baseNode;
		listSize++;
	}
	public ElementLinkedList(Element baseNode, Element firstPath) {
		head = baseNode;
		choiceA = firstPath;
		listSize+=2;
	}
	public ElementLinkedList(Element baseNode, Element firstPath, Element secondPath) {
		head = baseNode;
		choiceA = firstPath;
		choiceB = secondPath;
		listSize+=3;
	}
	
	//getters
	public Element getOriginElement(){
		return head;
	}
	public Element getChoiceA() {
		return choiceA;
	}
	public Element getChoiceB() {
		return choiceB;
	}
	public int getListSize(){
		return listSize;
	}
	
	//Checks to see if linked list is empty
	//input: none
	//output: true or false depending if list is empty or not
	public boolean isEmpty(){
		//Checks to see if empty.
		//If it is return true
		if(head == null && listSize == 0){
			return true;
		//else return false
		} else{
				return false;
			}
	}
	

}
