import java.text.DecimalFormat;

public class Question3 {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00"); // "#.00" ensures two decimal places, even if they are zeros
        
        int days = 6;

        Hostel hostel = new Hostel(5, "abc", 7);
        System.out.println("Budget of hostel: " + df.format(hostel.calculateBudget(days)));

        Resort resort = new Resort(50, "test", 9);
        System.out.println("Budget of resort: " + df.format(resort.calculateBudget(days)));
    }
}

abstract class Hotel {
    int totalRoom;
    String address;
    int dailyPrice;

    public Hotel(int totalRoom, String address, int dailyPrice) {
        this.totalRoom = totalRoom;
        this.address = address;
        this.dailyPrice = dailyPrice;
    }
    public int getTotalRoom() {
        return totalRoom;
    }
    public void setTotalRoom(int totalRoom) {
        this.totalRoom = totalRoom;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getDailyPrice() {
        return dailyPrice;
    }
    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    protected abstract double calculateBudget(int days);
}

class Hostel extends Hotel{

    public Hostel(int totalRoom, String address, int dailyPrice) {
        super(totalRoom, address, dailyPrice);
    }

    @Override
    protected double calculateBudget(int days) {
        return days * dailyPrice * 0.75;
    }
    
}

class Resort extends Hotel{

    public Resort(int totalRoom, String address, int dailyPrice) {
        super(totalRoom, address, dailyPrice);
    }

    @Override
    protected double calculateBudget(int days) {
        if (days >= 5){
            int overstayDays = days - 4;
            double totalBudget = 4 * dailyPrice * 1.3;  //budget for first 4 days
            totalBudget += overstayDays * dailyPrice;
            return totalBudget;
        } else {
            //stay less than 5 days
            return days * dailyPrice * 1.3;
        }
    }
}
