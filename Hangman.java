
import java.io.*;
import java.util.Scanner;

public class Hangman {
	public static void main(String[] args) { 
		String [][]title= {{" ___     ___"," ","","","","",""},
				{"|   |  |    |"," ","","","","",""},
				{"|   |__|    |","  ___  __"," __  ___    "," ___  __"," _  ____  _____   ","  ___  __"," __  ___    "},
				{"|    __     |","/   __`  |","   '__  \\ ","/    _`  |","  '__   ` __   \\"," /   __`  |","   '__  \\"},
				{"|   |   |   |","   (__|  |","  |   |  |","   (__|  |","  |   |  |  |   |","   (__|  |","   |  |  |"},
				{" \\__|   |__/"," \\_____,__|","__|   |__|","\\_____,  |","__|   |__|  |___|","\\_____, _|","___|  |__|"},
				{"","","","","                                  _____/  |","",""},
				{"","","","                                 |_______/","","",""}

		};
		
		for(int i=0;i<title.length;i++) {
			for(int j=0;j<title[0].length;j++) {
				System.out.print(title[i][j]);
			}
			System.out.println();
		}
		
		String [][]hangman= {
				{"___________","","",""},
				{"|/        |","","",""},
				{"|","","         ",""},
				{"|","","",""},
				{"|","","",""},
				{"|__________","","",""},
		};
		for(int i=0;i<hangman.length;i++) {
			for(int j=0;j<hangman[0].length;j++) {
				System.out.print(hangman[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		int randomNum;
		String randomWord;
		int noOfLives=6;//number of incorrect characters allowed
		char[] wrongLettersArray=new char [noOfLives];//an array to store wrong letter guesses
		boolean isArrayFull=true;
		int wrongLetterArrIndex=0;//used for storing incorrectly guessed letters at the right index in wrongLetterArray
		Scanner sc=new Scanner(System.in);
		Dictionary dict=new Dictionary();
		randomNum = (int)(Math.random()*931);//generating a random number
		randomWord=dict.getWord(randomNum).toLowerCase();//making sure the lower/upper case doesn't matter

		
		String [] dashArray = new String [randomWord.length()-1];
		for(int i=0;i<dashArray.length;i++) {
			dashArray[i]="__ ";
		}//creating the array for the word
		
	     //System.out.print(randomWord);
	     System.out.println("Let's go!");
		 System.out.println("This word is "+(randomWord.length()-1)+" characters long:");
		 for(int i=0;i<dashArray.length;i++) {
				System.out.print(dashArray[i]);
			}//printing out the initial setup of the game
		
		 
		 // ****************MAIN LOGIC WHILE LOOP***********
		 while(noOfLives>0) {
			    System.out.println();
			    if(noOfLives==1) {
			    	System.out.println("Concentrate! You have only "+noOfLives+" life left!");
			    }else if(noOfLives==6) {
			    	System.out.println("You have "+noOfLives+" lives.");
			    }else{
			    	System.out.println("You have "+noOfLives+" lives left.");
			    }
				System.out.println("Guess one character: ");
				String letter=sc.nextLine();//enabling the user character input
				while(letter.length()!= 1){//restricting the input to only one character
					System.out.println("Enter only one character please!");
					letter = sc.nextLine();
				}
				boolean isLetterCorrect = false;//checking if the word contains the entered letter
				for(int i=0;i<(randomWord.length()-1);i++) {//looping through the WORD
					 if(letter.charAt(0)==randomWord.charAt(i)){
						 dashArray[i] = Character.toString(randomWord.charAt(i));//storing the correctly guessed letter in the array
						 isLetterCorrect = true;
					 }
				}
				System.out.println();
				isArrayFull=true;
				for(int l=0;l<dashArray.length;l++) {//looping through the ARRAY
					if(dashArray[l]=="__ ") {
						isArrayFull=false;
					}
					System.out.print(dashArray[l]+"  ");//printing out the contents of the array
				}
				System.out.println();
				System.out.print("Incorrect guesses:  ");

				if(!isLetterCorrect) {
					wrongLettersArray[wrongLetterArrIndex]=letter.charAt(0);	//storing the incorrectly guessed letters in wrongLettersArray
					wrongLetterArrIndex++;
					noOfLives--;//decrementing the number of lives each time the user guesses a wrong letter
				}
				if(wrongLettersArray[0]==0) {
					System.out.print("none");
				}//if nothing has been stored to wrongLettersArray, print that there are no incorrect guesses 
				
				for(int k=0;k<wrongLetterArrIndex;k++) {
					System.out.print(wrongLettersArray[k]+" ");
				}//printing out the incorrect letter guesses
				if(!isLetterCorrect) {
					switch(wrongLetterArrIndex) {
					case 1:
						hangman[2][3]="O";
						break;
					case 2:
						hangman[3][1]="        /";
						break;
					case 3:
						hangman[3][2]="||";
						break;
					case 4:
						hangman[3][3]="\\";
						break;
					case 5:
						hangman[4][2]="        /";
						break;
					case 6:
						hangman[4][3]=" \\";
						break;
					}//adding body parts to the hangman drawing
					
					System.out.println();
					for(int i=0;i<hangman.length;i++) {
						for(int j=0;j<hangman[0].length;j++) {
							System.out.print(hangman[i][j]);
						}
						System.out.println();
					}//printing out hangman again
				}
				if(isArrayFull==true) {
					break;
				}
		 }
			
		 if(isArrayFull==true) {
			 	System.out.println();
			 	System.out.println();
			 	System.out.println("Wow, I can't believe you won!!");
			}else {
				System.out.println();
				System.out.println();
				System.out.println("Oh no! You lost! :(");
				System.out.print("The word was: "+randomWord);
			}
			System.out.println();
			System.out.println("Try to guess another word by running this code again from the start!");
	}
}

class Dictionary{
    
    private String input[]; 

    public Dictionary(){
        input = load("smallDict.txt");  
    }
    
    public int getSize(){
        return input.length;
    }
    
    public String getWord(int n){
        return input[n];
    }
    
    private String[] load(String file) {
        File aFile = new File(file);     
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader( new FileReader(aFile) );
            String line = null; 
            int i = 0;
            while (( line = input.readLine()) != null){
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        }catch (FileNotFoundException ex){
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }finally{
            try {
                if (input!= null) {
                    input.close();
                }
            }catch (IOException ex){
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(String s: array){
            s.trim();
        }
        return array;
    }
}

