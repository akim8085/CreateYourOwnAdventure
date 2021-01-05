package cyoa;

//This class is used to keep a list of storyLines in each element.
//We use this in case certain things change within the story and we have to change the text
public class StoryLinkedList {	
	
	//Data needed to keep track of our linked list
	StoryNode head;
	StoryNode tail;
	private int listSize = 0;
	
	//Constructors
	public StoryLinkedList(){
	}
	public StoryLinkedList(StoryNode firstNode) {
		head = firstNode;
		tail = firstNode;
		listSize++;
	}
	
	//getters
	public StoryNode getHeadNode(){
		return head;
	}
	public StoryNode getTailNode() {
		return tail;
	}
	public int getListSize(){
		return listSize;
	}
	public void setHead(StoryNode head) {
		this.head = head;
	}
	public void setTail(StoryNode tail) {
		this.tail = tail;
	}
	
	//Checks to see if linked list is empty
	//input: none
	//output: true or false depending if list is empty or not
	//time: O(1)
	//space: O(1)
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
	//Adds a storynode to be stored in the list for use later
	//We usually use the head and tail trakers to update 
	//without traversing the list.
	//input: String of the storyline we want to add to list
	//output: none
	//time: O(1)
	//space: O(1)
	public void addStoryNode(String data) {
		
		StoryNode placeHolder;
		if(head == null) {
			StoryNode temp = new StoryNode(data,0);
			this.head = temp;
			this.tail = temp;
			listSize++;
		}else if(listSize == 1) {
			StoryNode temp = new StoryNode(data,1);
			this.tail.next = temp;
			tail = tail.next;
			tail.prev = head;
			listSize++;
		}else {
			StoryNode temp = new StoryNode(data,tail.id+1);
			placeHolder = tail;
			this.tail.next = temp;
			tail = tail.next;
			tail.prev = placeHolder;
			listSize++;
		}
	}
	
	//Loop through until we hit the story id we want to delete
	//Once we hit it we link the adjacent nodes together and 
	//make currentNode next and prev into null
	//Input: story node id
	//output: none
	//Time: O(n) b/c we loop through the list to find id
	//space: O(1)
	public void deleteAtId(int id) {
		StoryNode currentNode = head;
		while(currentNode.id != id && currentNode != null) {
			currentNode = currentNode.next;
		}
		if(currentNode != null) {
			//StoryNode placeHolder = currentNode;
			currentNode.prev.next = currentNode.next;
			currentNode.next.prev = currentNode.prev;
			currentNode.prev = null;
			currentNode.next = null;
		}
	}
	
}

