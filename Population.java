import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Population - This class reads in the City
 * data from the usPopData2017.txt file and
 * stores it in the list. It organizes the
 * set of data with SortMethods.java.
 * <p>
 * Requires FileUtils and Prompt classes.
 *
 * @author Jonathan Luo
 * @since January 9, 2023
 */
public class Population {

    // List of cities
    private List<City> cities;

    // List of states
    private List<String> states;

    // List of city names
    private List<String> cityNames;

    // SortMethods object

    private SortMethods sort;
    
    // US data file
    private final String DATA_FILE = "usPopData2017.txt";

    // constructor

    public Population() {
        cities = new ArrayList<City>();
        states = new ArrayList<String>();
        cityNames = new ArrayList<String>();
        sort = new SortMethods();
    }

    // main method

    public static void main(String[] args) {
        Population p = new Population();
        p.readFile();
        p.printIntroduction();
        p.run();
    }

    /**
     * Runs and takes the input of
     * the user for the desired sort.
     */
    public void run() {
        printMenu();
        boolean done = false;
        int selection = 0;
        while (!done) {
            selection = Prompt.getInt("Enter selection");
            if (selection < 10 && selection > 0) done = true;
        }
        long startMillisec = 0;
        long endMillisec = 0;
        if (selection != 9) {
            if (selection == 1) {
                startMillisec = System.currentTimeMillis();
                sort.sortAscendPopulation(cities);
                endMillisec = System.currentTimeMillis();
            }
            else if (selection == 2) {
                startMillisec = System.currentTimeMillis();
                sort.sortDescendPopulation(cities);
                endMillisec = System.currentTimeMillis();
            }
            else if (selection == 3) {
                startMillisec = System.currentTimeMillis();
                sort.sortAscendName(cities);
                endMillisec = System.currentTimeMillis();
            }
            else if (selection == 4) {
                startMillisec = System.currentTimeMillis();
                sort.sortDescendName(cities);
                endMillisec = System.currentTimeMillis();
            }
            else if (selection == 5) {
                done = false;
                String state = "";
                System.out.println();
                while (!done) {
                    state = Prompt.getString("Enter state name (ie. Alabama) ");
                    if (states.contains(state)) done = true;
                    else System.out.println("ERROR: " + state + " is not valid");
                }
                startMillisec = System.currentTimeMillis();
                List<City> arr = sort.sortInState(cities, state);
                endMillisec = System.currentTimeMillis();
                System.out.println("\nFifty most populous cities in " + state);
                System.out.printf("%-3s %-22s %-22s %-12s %12s", "", "State", "City", "Type", "Population");
                System.out.println();
                for(int i = 0; i < 50; i++) {
                    System.out.printf("%2s", (i + 1));
                    System.out.print(": ");
                    System.out.println(arr.get(i));
                }
            }
            else if (selection == 6) {
                done = false;
                String city = "";
                System.out.println();
                while (!done) {
                    city = Prompt.getString("Enter city name ");
                    if (cityNames.contains(city)) done = true;
                    else System.out.println("ERROR: " + city + " is not valid");
                }
                startMillisec = System.currentTimeMillis();
                List<City> arr = sort.matchCity(cities, city);
                endMillisec = System.currentTimeMillis();
                System.out.println("\nCity " + city + " by population");
                System.out.printf("%-3s %-22s %-22s %-12s %12s", "", "State", "City", "Type", "Population");
                System.out.println();
                int i = 0;
                while(i < arr.size()) {
                    System.out.printf("%2s", (i + 1));
                    System.out.print(": ");
                    System.out.println(arr.get(i));
                    i++;
                }
            }
            if(selection < 5) printResult(selection);
            System.out.println("\nElapsed time " + (endMillisec-startMillisec) + " milliseconds\n");
            run();
        }
        else System.out.println("\nThanks for using Population!");
    }

    /**
     * Prints the result after sorting
     * on to the screen.
     */
    public void printResult(int index) {
        if(index == 1) {
            System.out.println("\nFifty least populous cities");
        }
        else if(index == 2) {
            System.out.println("\nFifty most populous cities");
        }
        else if(index == 3) {
            System.out.println("\nFifty cities sorted by name");
        }
        else if(index == 4) {
            System.out.println("\nFifty cities sorted by name descending");
        }
        System.out.printf("%-3s %-22s %-22s %-12s %12s", "", "State", "City", "Type", "Population");
        System.out.println();
        for(int i = 0; i < 50; i++) {
            System.out.printf("%2s", (i + 1));
            System.out.print(": ");
            System.out.println(cities.get(i));
        }

    }

    /**
     * Reads the data file provided.
     * Loads the List<City> cities with data.
     */
    public void readFile() {
        Scanner in = FileUtils.openToRead(DATA_FILE);
        in.useDelimiter("[\t\n]");
        int index = 0;
        while (in.hasNext()) {
            cities.add(new City(in.next(), in.next(), in.next(), in.nextInt()));
            if (!states.contains(cities.get(index).getState())) states.add(cities.get(index).getState());
            if (!cityNames.contains(cities.get(index).getName())) cityNames.add(cities.get(index).getName());
            index++;
        }
    }


    /**
     * Prints the introduction to Population
     */
    public void printIntroduction() {
        System.out.println("   ___                  _       _   _");
        System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
        System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
        System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
        System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
        System.out.println("           |_|");
        System.out.println();
    }

    /**
     * Print out the choices for population sorting
     */
    public void printMenu() {
        System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
        System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
        System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
        System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
        System.out.println("5. Fifty most populous cities in named state");
        System.out.println("6. All cities matching a name sorted by population");
        System.out.println("9. Quit");
    }

}
