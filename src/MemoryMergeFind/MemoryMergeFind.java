/**
 MemoryMergeFind reads two separate JSON/CSV files and merges them into a new file if a search is performed. It
 takes user input as search criteria, searching the merged file for the search word, and finding the data sets
 containing the search word. The program requires user input. Based off of this input it will behave accordingly. If
 the user does not conduct a search it will provide the total searches and a print statement. If the user does perform
 a search, it will display the total searches, the amount of times each word was searched, and the search word, the
 total data sets containing that word, the time and date of the search, and the duration of each specific search. It
 utilizes a main method, a countSearches method (counts the amount of searches per search word and prints to the
 system), and printSearches method (prints the information for each specific search).
 */

package MemoryMergeFind;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;



// Public class IndiegogoMemoryMergeFind.IndiegogoMemoryMergeFind contains the main method used to run the program
public class MemoryMergeFind {

    /* countSearches method creates a hash map called searchHashMap and puts the search string searched by the user
    as the hash map key and counts the amount of times the search string appears as the hash map value. It then prints
    out the word searched and the number of times the search word was searched */
    public static void countSearches(ArrayList<String> list){

        /* Creates new hash map called searchHashMap, loops list for string i and adds one for each occurrence of i,
        putting i as the key and the number of occurrences as the value
        */
        Map<String, Integer> searchHashMap = new HashMap<>();
        for (String i : list) {
            Integer x = searchHashMap.get(i);
            searchHashMap.put(i, (x == null) ? 1 : x + 1);
        }

        /* For loop to loop the hash map and prints the string plus the search word and its amount of occurrences for
        the hash maps key, value pairs
        */
        for (Map.Entry<String, Integer> occurrence : searchHashMap.entrySet()) {
            System.out.println("You searched for " + occurrence.getKey() + ": " + occurrence.getValue() + " time(s)");
        }
    }

    /* The printSearches method uses an iterator to iterate over the list and print the string plus the corresponding
    data from the list for each search
    */
    public static void printSearches(ArrayList<Object> list) {

        // Initialize arrayIterator as a list iterator
        Iterator arrayIterator = list.iterator();

        /* While the arrayIterator has next, defines the variables searchName, searchDate, searchDataSets, and
        searchTime as the next index in the iterator of the array list and prints each with a string for each aspect of
        each data set
        */
        while (arrayIterator.hasNext()) {
            Object searchName = arrayIterator.next();
            System.out.println("\nWord Searched = " + searchName);
            Object searchDate = arrayIterator.next();
            System.out.println("Date And Time = " + searchDate);
            Object searchDataSets = arrayIterator.next();
            System.out.println("Total Data Sets Found = " + searchDataSets + " data sets");
            Object searchTime = arrayIterator.next();
            System.out.println("Duration Of Search = " + searchTime + " milliseconds\n");
        }
    }

    /* The main method takes two separate files, merges them to a new file, searches for a search word in the file, and
    prints the corresponding funds_raised_percent and close_date for each dataset in the merged file in which the
    search word is present. */
    public static void main(String[] args) throws IOException, NullPointerException {

        // Initializes two BufferedReaders, bufferedReader1 for the first file and bufferedReader2 for the second file
        BufferedReader bufferedReader1 = null;
        BufferedReader bufferedReader2 = null;

        // Initializes PrintWriter, printWriter to write from both bufferedReader1 and bufferedReader2 to separate file
        PrintWriter printWriter = null;

        // Initializes bufferedSearch, bufferedSearch for the merged file
        BufferedReader bufferedSearch = null;

        // Defines the inputFolder and outputFolder as new file and their corresponding file paths
        File inputFolder = new File("MemoryMergeFind/src/MemoryMergeFind/InputFiles");
        File outputFolder = new File("MemoryMergeFind/src/MemoryMergeFind/OutputFiles");

        /* Defines listInputFolder as list of type file as the inputFolder list of files and listOutputFolder as type
        file as the outputFolder list of files
        */
        File[] listInputFolder = inputFolder.listFiles();
        File[] listOutputFolder = outputFolder.listFiles();

        // Initialize string file1, file2, and file3
        String file1;
        String file2;
        String file3;

        // Try block for executing the below try statements
        try {

            /* Defines file1 and file2 as the index of the listInputFolder 0 and 1 respectively, getting the absolute
            path for each for more dynamic file inputs
            */
            file1 =  listInputFolder[0].getAbsolutePath();
            file2 = listInputFolder[1].getAbsolutePath();
            file3 = listOutputFolder[0].getAbsolutePath();

            // Define bufferedReader1 as new BufferedReader, new FileReader for the first Indiegogo file to be read
            bufferedReader1 = new BufferedReader(new FileReader(file1));

            // Define bufferedReader2 as new BufferedReader, new FileReader for the second Indiegogo file to be read
            bufferedReader2 = new BufferedReader(new FileReader(file2));

            /* Define printWriter as new PrintWriter, new FileWriter to write to a merged file in the IndiegogoMemoryMergeFind.OutputFiles
            folder */
            printWriter = new PrintWriter(new FileWriter(file3));

            /* Define line1 as bufferedReader1.readLine() and line2 as bufferedReader2.readLine(), to read each file
            line by line */
            String line1 = bufferedReader1.readLine();
            String line2 = bufferedReader2.readLine();

            // Define searchScan as new Scanner System.in to take input from the user
            Scanner searchScan = new Scanner(System.in);

            // Prints string that prompts user for input
            System.out.println("Would you like to perform a search? If yes, type yes, if no type anything.");

            /* Defines searchDecision as the searchScan.nextLine(), ie defining it as the line inputted by the user to
            the above string prompt
            */
            String searchDecision = searchScan.nextLine();

            // Initialize totalSearches as 0 and create new ArrayList called searchArray
            int totalSearches = 0;
            ArrayList searchArray = new ArrayList();

            // While the searchDecision (user input for the searchDecision) is equal to yes
            while (Objects.equals(searchDecision, "yes")) {

                /* Increment totalSearches by 1, create new Scanner System.in called inputScan for user input and print
                string for user input to respond to
                */
                totalSearches++;
                Scanner inputScan = new Scanner(System.in);
                System.out.println("Please enter a search word. Then click enter.");

                // Define long searchTimeBegin as the current system time in milliseconds
                long searchTimeBegin = System.currentTimeMillis();

             /* Define the String searchWord as user input "inputScan", used to search the merged file to find datasets
            containing user input and localDateTime as the LocalDateTime.now for the searches local date and time
            */
                String searchWord = inputScan.nextLine();
                LocalDateTime localDateTime = LocalDateTime.now();

                // Print string searching
                System.out.println("Searching...");

                // Add the searchWord to the searchArray and add the localDateTime to searchArray
                searchArray.add(searchWord);
                searchArray.add(localDateTime);

                // Initialize String array lineArray, used to make each line of the file into an array of strings
                String[] lineArray;

                // Initialize int count set to zero to keep a count of data sets containing searchWord
                int count = 0;

                /*New hash set called duplicateCheckSet, used to add lines from the files for the merged file, ensuring
                there are no duplicate lines as hash set will not allow duplicates
                */
                HashSet<String> duplicateCheckSet = new HashSet<>();

            /* While line1 is not equal to null, line1 = bufferedReader1.readLine() to reach each line, if statement to
            see if line can be added to duplicateCheckSet, ensuring no duplicates and if so, printWriter.println(line1)
            to print the line to the new merged file */
                while (line1 != null) {
                    line1 = bufferedReader1.readLine();
                    if (duplicateCheckSet.add(line1)) {
                        printWriter.println(line1);
                    }
                }

            /* While line2 is not equal to null, line2 = bufferedReader2.readLine() to reach each line, if statement to
            see if line can be added to duplicateCheckSet, ensuring no duplicates and if so, printWriter.println(line1)
            to print the line to the new merged file */
                while (line2 != null) {
                    line2 = bufferedReader2.readLine();
                    if (duplicateCheckSet.add(line2)) {
                        printWriter.println(line2);
                    }

                }

            /* Define bufferedSearch as new BufferedReader, new FileReader for new file IndiegogMergedFile that is the
            new merged file written to */
                bufferedSearch = new BufferedReader(new FileReader(file3));

                // Initialize String line, used for each line in the new merged file
                String line;

                // While line = bufferedSearch.readLine() is not equal to null, meaning there is another line to read
                while ((line = bufferedSearch.readLine()) != null) {

                    // If the line is not null
                    if (line != null) {

                        /* lineArray equals the line split with a regex of comma only if the comma has no quotes, or
                        an even number of quotes preceding it
                        */
                        lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                        // Iterate over lineArray, if dataSet in lineArray contains the search word, increment the count and
                        for (String dataSet : lineArray) {
                            if (dataSet.contains(searchWord)) {
                                count++;
                                break;
                            }
                        }
                    }
                }

                // Define searchTimeEnd as the current millisecond time
                long searchTimeEnd = System.currentTimeMillis();

                // Define totalSearchTime as the searchTimeEnd - searchTimeBegin to get the total search time
                long totalSearchTime = searchTimeEnd - searchTimeBegin;

                // Add the count to searchArray and the totalSearchTime to searchArray
                searchArray.add(count);
                searchArray.add(totalSearchTime);

                // searchScan2 = new Scanner System.in, print String to prompt user input and read line of user input
                Scanner searchScan2 = new Scanner(System.in);
                System.out.println("Would you like to perform a search? If yes, type yes, if no type anything.");
                String searchDecision2 = searchScan2.nextLine();

                /* If the user input string does not equal yes, break out of while loop, ending search process, if user
                input is yes, will loop through search process again for a new search
                */
                if (!Objects.equals(searchDecision2, "yes")) {
                    break;
                }
            }

            // Prints String plus the total searches and a new line
            System.out.println("\nTotal searches = " + totalSearches + "\n");

            // Defines searchNamesArray as a new ArrayList
            ArrayList searchNamesArray = new ArrayList();

            /* For loop to iterate over the searchArray, incrementing by 4 to get each search name and adds the search
            name from the searchArray to searchNamesArray
            */
            for (int i = 0; i < searchArray.size(); i = i + 4) {
                searchNamesArray.add(searchArray.get(i));
            }

            // Run method countSearches on searchNamesArray
            countSearches(searchNamesArray);

            // Run method printSearches on searchArray
            printSearches(searchArray);
        }

        // Catch block for FileNotFoundException, if file is not found print string "File not found." and stack trace
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        // Finally block, close bufferedReader1, bufferedReader2, printWriter, and bufferedSearch, ie closes all files
        finally {
            if(bufferedReader1 != null)
                bufferedReader1.close();
            if(bufferedReader2!= null)
                bufferedReader2.close();
            if(printWriter != null)
                printWriter.close();

                                        /* Inner try catch block to catch NullPointerException for bufferedSearch file,
                                        this occurs if the user decides not to perform any searches
                                        */
            try{
                bufferedSearch.close();}
            catch (NullPointerException e){
                System.out.println("You did not conduct or provide any searches.");
            }
        }
    }
}


