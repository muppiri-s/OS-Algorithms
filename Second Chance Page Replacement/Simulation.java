import java.io.IOException;
import java.util.*;
import java.io.*;

public class Simulation {
    public static void main(String[] args) throws IOException {
        System.out.println("A simulation of Second Chance Paging for different page sizes while using an inverted page table");
        Scanner user = new Scanner(System.in);

        // Input user to read the trace file
        System.out.println("File Name: ");
        String filename = user.nextLine().trim();
        File file = new File("C:\\Users\\sahit\\IdeaProjects\\os_simulation\\src\\"+filename+".txt");

        // Input user to enter memory size required, By default in KB
        System.out.println("Enter memory and page size in KB");
        System.out.println("Memory Size: ");
        int memory_size = user.nextInt();

        // Input user to enter page size required, By default in KB
        System.out.println("Page Size: ");
        int page_size = user.nextInt();

        // Find number of pages in page table
        int frame_size = memory_size/page_size;

        //Iterate through the lines in java
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.lines().map(line -> line.split("\\s+")[0]).forEach(System.out.println(););
    }

    static boolean update_reference_bit(int x,int arr[], boolean second_chance[],int frames){

        return false;
    }

    static boolean reference_bit(int x,int arr[], boolean second_chance[],int frames){

        return false;
    }

    static void page_faults(String address){

    }


}
