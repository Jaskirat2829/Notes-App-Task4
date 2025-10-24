import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Notes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //user input
        String fileName = "notes.txt"; //file where we can store the notes

        System.out.println("Welcome to the Notes App!");
        //while creates infinite loop and exit using return statement
        while(true){
            System.out.println("\nChoose the oprtion:");

            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete Note");
            System.out.println("4. Clear All Notes");
            System.out.println("5. Exit");
            System.out.println("Enter your Choice:");
            int choice = scanner.nextInt();//read the choice choosed by the user to perform the action
            scanner.nextLine(); //next line
            switch(choice){
                case 1:
                addNote(fileName , scanner ) ;//createing new note
                break;
                case 2:
                viewNotes(fileName);//displaying all notes
                break;
                case 3:
                deleteNote(fileName , scanner);//deleting selected note
                break;
                case 4:
                clearNotes(fileName);//clearing all notes
                break;
                case 5:
                System.out.println("Thanks for Choosing the NotesApp!");
                scanner.close();
                return;//exit the program and default handles the invalid input from the user
                default:
                System.out.println("Error Occured! Please Try Again.");
            }
        }
    }   


//add note
public static void addNote(String fileName , Scanner scanner){
    System.out.println("Enter your note:");
    String note = scanner.nextLine();//read the note from user
    //new filewriter opens the file in append mode
    //try is used to auto close the file after writing
    try(FileWriter fw =new FileWriter(fileName , true)){
        fw.write(note + "\n");//writing the note to the file and then move to next line for better view
        System.out.println("Note added successfully!");
    }
    catch(Exception e){
        System.out.println("Error Occured! While adding note.");
    }
}
//view notes
public static void viewNotes(String fileName){
    //Filereader opens the file for reading anf bufferreader wraps it to allow readline which reads one line at a time
    try(BufferedReader br= new BufferedReader(new FileReader(fileName))){
        String line;
        int count =1;//number of the notes 
        System.out.println("Your Notes:");
        //read and display each line until end of file
        //while loop continues until readline returns null indicating end of file
        while((line=br.readLine()) != null){
            System.out.println(count + ". " + line);
            count++;
        }
        if(count ==1){
            System.out.println("No notes found.");
        }
    }
    //filenotfound exception occurs when notes.txt file is not present 
    catch(FileNotFoundException e){
        System.out.println("Np notes file found.Add the note");
    }
    catch(IOException e){
        System.out.println("Error Occured! While viewing notes.");
    }
}
//delete note
public static void deleteNote(String fileName , Scanner scanner){
    //create a list to hold the lines read from the file
    List<String> notes =new ArrayList<>();
    //read the whole file using bufferreader 
    try(BufferedReader br= new BufferedReader(new FileReader(fileName))){
        String line;
        while((line=br.readLine()) !=null){
            notes.add(line);
        }
    }
    //if read fails due to ioexception then show error and return
    //if note is empty nothing to delete
    catch(IOException e){
        System.out.println("No notes found to delete.");
        return;
    }

    //show the notes
    for(int i=0;i<notes.size();i++){
        System.out.println((i+1)+". " + notes.get(i));
    }
    System.out.println("Enter the note number to delete:");
    int noteNumber = scanner.nextInt();//read the note number by user and then clear newline with scanner.nextline
    scanner.nextLine();

    if(noteNumber<1 || noteNumber >notes.size()){
        System.out.println("Invalid note number.");
        return;
    }
    notes.remove(noteNumber -1); //delete seelected note

    //overwrite the file with remaining notes
    try(FileWriter fw=new FileWriter(fileName)){
        for(String note:notes){
            fw.write(note+"\n");
        }
        System.out.println("Note deleted successfully!");
    }
    catch(IOException e){
        System.out.println("Error Occured! While deleting note.");
    }
}
//clear all notes
public static void clearNotes(String fileName){
    //opening new file writer without append mode which clears the file
    try(FileWriter fw=new FileWriter(fileName)){
        fw.write("");//we write the empty string to clear the file
        System.out.println("All notes cleared successfully!");
    }
    catch(IOException e){
        System.out.println("Error Occured! While clearing notes.");
    }
}
}