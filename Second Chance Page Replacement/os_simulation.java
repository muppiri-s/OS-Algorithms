import java.io.*;
import java.util.*;

public class os_simulation {
    public static void main(String[] args) throws IOException {
        Scanner user = new Scanner(System.in);
        // String inputFileName = user.nextLine().trim();
        // User has to input file name.
        File file = new File("C:\\Users\\sahit\\IdeaProjects\\os_simulation\\src\\trace1.txt");
        System.out.println("Trace File: " + file.getName());
        // Value in KB?
        double bytes = file.length();
        double kilobytes = (bytes / 1024);
        System.out.println("Memory Size: " + kilobytes + " KB");
        // User has to say the page size. !?
        System.out.println("Page Size: ");
        System.out.println("Page Faults: ");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String reference_string;
        int frames = 3;
        while ((reference_string = br.readLine()) != null) {
            printHitsAndFaults(reference_string, frames);
        }
    }

    //If page found, updates the second chance bit to true
    static boolean findAndUpdate(int x, int arr[], boolean second_chance[], int frames) {
        int i;
        for (i = 0; i < frames; i++) {
            if (arr[i] == x) {
                //Mark that the page deserves a second chance
                second_chance[i] = true;
                //Return 'true', that is there was a hit
                //and so there's no need to replace any page
                return true;
            }
        }
        //Return 'false' so that a page for replacement is selected
        //as he reuested page doesn't exist in memory
        return false;

    }


    //Updates the page in memory and returns the pointer
    static int replaceAndUpdate(int x, int arr[], boolean second_chance[], int frames, int pointer) {
        while (true) {
            //We found the page to replace
            if (!second_chance[pointer]) {
                //Replace with new page
                arr[pointer] = x;
                //Return updated pointer
                return (pointer + 1) % frames;
            }
            //Mark it 'false' as it got one chance
            // and will be replaced next time unless accessed again
            second_chance[pointer] = false;
            //Pointer is updated in round robin manner
            pointer = (pointer + 1) % frames;
        }
    }

    static void printHitsAndFaults(String reference_string, int frames) {
        int pointer, i, l, x, pf;
        //initially we consider frame 0 is to be replaced
        pointer = 0;
        //number of page faults
        pf = 0;
        //Create a array to hold page numbers
        int arr[] = new int[frames];
        //No pages initially in frame,
        //which is indicated by -1
        Arrays.fill(arr, -1);
        //Create second chance array.
        //Can also be a byte array for optimizing memory
        boolean second_chance[] = new boolean[frames];
        //Split the string into tokens,
        //that is page numbers, based on space
        String str[] = reference_string.split(" ");
        //get the length of array
        l = str.length;

        for (i = 0; i < l; i++) {
            x = Integer.parseInt(str[i]);
            //Finds if there exists a need to replace
            //any page at all
            if (!findAndUpdate(x, arr, second_chance, frames)) {
                //Selects and updates a victim page
                pointer = replaceAndUpdate(x, arr, second_chance, frames, pointer);
                //Update page faults
                pf++;
            }
        }
        System.out.println("Total page faults were " + pf);
    }
}
