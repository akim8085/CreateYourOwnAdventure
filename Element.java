package cyoa;

public class Element {
	//Information we want to keep track of
	//element id will help us map the graph on paper
	//and make linking nodes easier
	int elementId;
	//Title will help us keep up the history
	//We can just now connect titles instead of elements
	String title;
	//Contains the story text
	String storyLine;
	//Contains extra story content. It can replace storyline or add to it
	StoryLinkedList storyNodes = new StoryLinkedList();
	boolean change = false;
	//Marks if the node is an ending. It is used while we are runnint play()
	boolean ending;
	//Keep track of the number of times we visited the element
	//We can use it to change the storyline if we want
	int timesVisited = 0;
	String linkedTo = "";
	String historyChain;
	
	//Constructor
	public Element(String story, String title, int elementId) {
		this.storyLine = story;
		this.title = title;
		this.elementId = elementId;
	}
	
	//Prints out the links the element contains.
	//Grabs the linked to string that we update at real time
	//Input: none
	//output: none (prints info)
	//Time: O(1)
	//Space: O(1)
	public void links() {
		System.out.println(elementId + " linked to: " + linkedTo);
	}
	
	//Gives true or false if the element is an ending or not
	//input: none
	//output: true if element is ending and false if its not
	//Time: O(1)
	//Space:O(1)
	public boolean isEnding() {
		return this.ending;
	}
	//returns the timesVisited tracker value
	//input: none
	//output: int value of times visited
	//Time: O(1)
	//Space:O(1)
	public int timesVisited() {
		return this.timesVisited;
	}
	
	//Adds a story node to the storyNodes linked list
	//Uses the written list method to add to the end of the list
	//Input: String text of the story we want to add 
	//Output: none
	//Time: O(1) b/c we use tail to add at end
	//Space: O(1)
	public void addStoryLine(String text) {
		storyNodes.addStoryNode(text);
	}
	
	//Update the storyLine content with the desired node id.
	//Loop through the list to find the id of the node we want
	//Use string stored in storynode to update story line
	//Input: int id of storynode
	//output: none
	//Time: O(n) b/c we loop through the story list
	//Space: O(1)
	public void updateStoryLine(int idOfDesiredStoryLine) {
		StoryNode currentNode = storyNodes.head;
		while(currentNode.id != idOfDesiredStoryLine && currentNode != null) {
			currentNode = currentNode.next;
		}
		storyLine = currentNode.storyText;
	}
	
	//Removes a story node based on the story node id given
	//Loops through and finds id and then links/delinks to remove
	//Input: StoryNode id we want to remove
	//Output: none
	//Time: O(n)
	//Space: O(1)
	public void removeStoryLine(int id) {
		if(storyNodes.getListSize() == 1) {
			System.out.println("Cant delete last story");
		}else {
			storyNodes.deleteAtId(id);
		}
	}
	public int numberOfStoryLines() {
		return storyNodes.getListSize();
	}
	
	//Update the storyLine content with the desired node id.
	//Loop through the list to find the id of the node we want
	//Use string stored in storynode to update story line
	//Use an int threshold of visits or a boolean to check if we 
	//want to change the storyline. if the threshold or boolean pass
	//the check, we update the storyline with the desired storynode id
	//Input: int id of storynode
	//output: none
	//Time: O(n) b/c we loop through the story list
	//Space: O(1)
	public void storyLines(int timesVisitedThreshold, boolean changeStory, int id) {
		if(this.timesVisited == timesVisitedThreshold || changeStory ==true) {
			updateStoryLine(id);
		}
	}
	//Print all stories stored in the storyline lists
	//Input: none
	//Output: none (prints all storylines possible)
	//Time: O(n) b/c we loop through the whole list
	//Space: O(1)
	public void printStory(int id) {
		if(id == 0) {
			System.out.println(storyNodes.head);
		}else {
			System.out.println(storyNodes.tail);
		}
	}
	
}
