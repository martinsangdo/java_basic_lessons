import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Question2 {
    static Set<String> getSubStrings(String originalString, int subStringLen){
        Set<String> subArrayList = new HashSet<>();
        if (Objects.isNull(originalString)){
            return subArrayList;
        }
        int strLen = originalString.length();
        
        //calculate how many sub strings
        int subStringsNum = (int)(strLen / subStringLen);
        subStringsNum += (strLen % subStringLen == 0)?0:1;
        
        for (int i=0; i<subStringsNum; i++){
            int endPosition = i*subStringLen + subStringLen;
            if (endPosition >= strLen){
                endPosition = strLen;
            }
            subArrayList.add(originalString.substring(i*subStringLen, endPosition));
        }
        
        return subArrayList;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a string:");
        String originalString = sc.nextLine();
        if (originalString.length() == 0){
            System.out.println("Please input a non-empty string.");
            sc.close();
            return;
        }
        System.out.println("Original string: " + originalString);

        System.out.println("Input the length of subarray:");
        try {
            int subStringLen = sc.nextInt();
            sc.nextLine();
            if (subStringLen <= 0){
                System.out.println("Please input an integer number");
            } else {
                Set<String> subStrings = getSubStrings(originalString, subStringLen);
                if (subStrings.size() > 0){
                    System.out.println("The result array is as below:");
                    for (String subString : subStrings){
                        System.out.println(subString);
                    }
                } else {
                    System.out.println("There is no result");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            //in case user inputs text instead of number
            System.out.println("Exception: Please input an integer number");
        }

        sc.close();
    }
}
