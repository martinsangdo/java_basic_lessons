import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Question4 {
    public static void main(String[] args) {
        // System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        Map<String, ArrayList<Employee>> cityMap = new HashMap<>();    //key: city, value: employee info
        ArrayList<String[]> employees = readFile();
        int employeeNum = employees.size();
        if (employeeNum == 0){
            System.out.println("The file has no data or cannot be read");
            return;
        }
        System.out.println("Number of employees: " + employeeNum);
        //get a list of employees
        Set<Integer> uniqueNumbers = generateUniqueRandomNumbers(employeeNum, 1, 100);
        Iterator<Integer> iterator = uniqueNumbers.iterator();
        int employeeIndex = 0;
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            String[] info = employees.get(employeeIndex++);
            String city = info[2];
            Employee e = new Employee(id, info[0], Integer.parseInt(info[1]), city, info[3]);
            ArrayList<Employee> subList = new ArrayList<>();
            if (cityMap.containsKey(city)){
                subList = cityMap.get(city);
            }
            subList.add(e);
            cityMap.put(city, subList);
        }
        for (String city : cityMap.keySet()){
            storeFiles(city + ".csv", cityMap.get(city));
        }
    }

    public static Set<Integer> generateUniqueRandomNumbers(int n, int min, int max) {
        // Use a HashSet to store unique numbers
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();
        // Loop until the desired number of unique elements is reached
        while (uniqueNumbers.size() < n) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            uniqueNumbers.add(randomNumber); // Add to the set; duplicates are automatically ignored
        }
        return uniqueNumbers;
    }

    public static ArrayList<String[]> readFile(){
        String csvFilePath = "test.csv";
        String line;
        String cvsSplitBy = ",";    // Use comma as separator of each item in a line
        ArrayList<String[]> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                results.add(data);
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading CSV file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void storeFiles(String filename, List<Employee> dataToWrite){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Iterate over each row of data
            for (Employee row : dataToWrite) {
                String line = row.convert2Line();   //1 employee info as a line
                writer.write(line);
                writer.newLine();
            }
            System.out.println("CSV file '" + filename + "' saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving CSV file: " + e.getMessage());
        }
    }
}

class Employee{
    int id;
    String name;
    int age;
    String city;
    String occupation;

    Employee(int id, String name, int age, String city, String occupation){
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.occupation = occupation;
    }

    String convert2Line(){
        String[] line = new String[5];
        line[0] = String.valueOf(id);
        line[1] = name;
        line[2] = String.valueOf(age);
        line[3] = city;
        line[4] = occupation;
        return String.join(",",line);
    }
}
