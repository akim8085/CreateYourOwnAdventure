package cyoa;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		//System.out.println("Inner tests for some methods I didnt use");
		//ChooseYourAdventure test = creator();
		//System.out.println(test.numberOfElements());
		//test.endings();
		
		System.out.println("\n\n            The very short adventure created by a sleep deprived CS student.");
		play();

	}
	
	public static ChooseYourAdventure creator() {
		//Ending array
		String[] end = {"You died", "You lived and lived happily ever after", "To Be Continued"};
		//Creation of the CYOA and all elements planned to be implemented this ver
		String start = "You wake up in a an unknown forest with no Memories. You see a road with two paths. Which do you take?"
				+ "\n 1.Right \n 2.Left";
		ChooseYourAdventure myAdventure = new ChooseYourAdventure(start, end);
		Element ele1 = new Element("You go towards the right. You walk until you see a village then you start sprinting", "Right path", 1);
		
		Element ele2 = new Element("You go towards the left.\n"
				+ "You encounter a bear. It mauls you to death.", "Left Path",2);
		
		Element ele3 = new Element("You reach the village. As you walk in, you see a board post that states Rito Village"
				+ " What do you do? \n1.Find someone to talk to\n2. ... dont choose this", "Village", 3);
		//Story updates at village after the initial visit. See below in play() for test.
		ele3.addStoryLine("You are now at Rito Village Square. What do you do? "
				+ "\n1.Find someone to talk to\n2....dont choose this");
		
		Element ele4 = new Element("You look around and see a couple of people. You spot a man and a woman. Who do you talk to?"
				+ "\n1.Man\n2.Woman", "Almost to the end of ch1", 4);
		
		Element ele5 = new Element("You run around like a crazy person. Eventually a guard appears. He starts questioning you. What do you do?"
				+ "\n1. Attack Him\n2. Stay silent", "What are you doing?", 5);
		
		Element ele6 = new Element("You talk to the man. He seems like a wizard. He gives you a sword and you become a king", "Happy end", 6);
		
		Element ele7 = new Element("You talk to the woman. The woman turns out to be a witch."
				+ "\"Hello my name is Sarah. I know it is abrupt but would you help me fight the undead army?\""
				+"\n1.Yes\n2.No", "Oh boy...", 7);
		Element ele8 = new Element("You attack him. Or atleast try to. The guard swings his sword and kills you.", "Why tho", 8);
		Element ele9 = new Element("He leads you away. You are now in jail with a criminal record.", "Uh why'd you go this way", 9);
		//Add all elements to the graph we plan to use to traverse
		myAdventure.addElement(ele1);
		myAdventure.addElement(ele2);
		myAdventure.addElement(ele3);
		myAdventure.addElement(ele4);
		myAdventure.addElement(ele5);
		myAdventure.addElement(ele6);
		myAdventure.addElement(ele7);
		myAdventure.addElement(ele8);
		myAdventure.addElement(ele9);
		
		//Remove test. Should print the element id we removed
		//Added it back in so we can actually play
		myAdventure.removeElement(9);
		myAdventure.addElement(ele9);
		
		//Linking the story elements together
		//Its a directed graph that kinda looks like a tree
		//But we can loop back to previous nodes if we want
		myAdventure.addLink(0, 1);
		myAdventure.addLink(0, 2);
		myAdventure.addLink(2, 100);
		myAdventure.addLink(1, 3);
		myAdventure.addLink(3, 4);
		myAdventure.addLink(3, 5);
		myAdventure.addLink(4, 6);
		myAdventure.addLink(4, 7);
		myAdventure.addLink(6, 99);
		myAdventure.addLink(7, 100);
		myAdventure.addLink(7, 3);
		myAdventure.addLink(5, 8);
		myAdventure.addLink(5, 9);
		myAdventure.addLink(8, 100);
		myAdventure.addLink(9, 98);
		
		//Remove link test and adding back so we can play
		myAdventure.removeLink(9, 98);
		myAdventure.addLink(9, 98);
		//Return this Adventure
		return myAdventure;
	}
	
	public static void play() {
		//Scanner for user input and CYOA story made in creator
		Scanner scan = new Scanner(System.in);
		ChooseYourAdventure myAdventure = creator();
		System.out.println();
		
		int input = 0;
		//initialise the currentElement to start and start the story
		Element currentElement = myAdventure.story[0].head;
		int currentElementIndex = myAdventure.findIndexOf(currentElement.elementId);
		System.out.println(myAdventure.story[0].head.storyLine);
		
		while(!currentElement.isEnding()) {
			//Get the index of the current element to use
			currentElementIndex = myAdventure.findIndexOf(currentElement.elementId);
			//Use the index to find vertices. If it only has one choice 
			//defualt input goes to 1 and we continue with the story
			if(myAdventure.story[currentElementIndex].choiceB == null) {
				input = 1;
			//If it has 2 choices we let the user choose the options
			}else {
				input = 0;
				while((input != 1 && input != 2)) {
					System.out.print("Enter choice: ");
					input = scan.nextInt();
					System.out.println();
				}
			}
			//Change current node depending on choices
			if(input == 1) {
				currentElement = myAdventure.story[currentElementIndex].choiceA;
			}else {
				currentElement = myAdventure.story[currentElementIndex].choiceB;
			}
			myAdventure.history = myAdventure.history + " -> " + currentElement.title;
			//Print story and update the times we visited the node
			System.out.println(currentElement.storyLine);
			currentElement.timesVisited++;
			//Here I am testing the story update. Sequence is: right, find someone to talk with, woman, no
			//Should change the initial Rito village text (initial was the one with the sign)
			if(currentElement.title.compareTo("Village")==0) {
				currentElement.storyLines(1, false, 0);
			}
			//Elements Links to fucntion test. Its annoying to test during the game.
			System.out.println("Links: " + currentElement.linkedTo + "\n");
		}
		//History test
		System.out.println("\nHistory : " + myAdventure.history);
		scan.close();
	}

}
