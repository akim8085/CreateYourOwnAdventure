package cyoa;

public class StoryNode {
	//Node information needed
	//This is information needed to keep track of the story
	//texts. We use it so we can switch between storyLine elements
	//when certain parameters change.
	String storyText;
	int id;
	int visitThreshold;
	int choiceThreshold;
	boolean unlockedByLastVisited;
	StoryNode next;
	StoryNode prev;
	
	//Constructors
	public StoryNode(){
	}
	public StoryNode(String storyText, int id){
		this.storyText = storyText;
		this.id = id;
	}

	//Setters
	public void setText(String data){
		this.storyText = data;
	}
	

}