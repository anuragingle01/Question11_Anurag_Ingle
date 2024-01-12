import java.util.Random;

public class Main {

    static PenSaleRecord[] generateSalesData(int days) {
        Random random = new Random();
        PenSaleRecord[] salesData = new PenSaleRecord[days];
            int price = random.nextInt(11) + 20;       

        for (int i = 0; i < days; i++) {
            int unitsSold = random.nextInt(901) + 100; 
            int amount = unitsSold * price;

            salesData[i] = new PenSaleRecord(unitsSold, price, amount);
        }

        return salesData;
    }

    static void analyzeAndPrint(String period, PenSaleRecord[] salesData, int chunkSize) {
        for (int i = 0; i < salesData.length; i += chunkSize) {
            int totalUnits = 0;
            int totalAmount = 0;

            for (int j = i; j < i + chunkSize && j < salesData.length; j++) {
                totalUnits += salesData[j].getUnitsSold();
                totalAmount += salesData[j].getAmount();
            }

            System.out.printf("%s %d: Units - %d, Amt - %d%n",
                    period, (i / chunkSize) + 1, totalUnits, totalAmount);
        }
    }

    static int findMaxAmountQuarter(PenSaleRecord[] salesData, int quarters) {
        int maxAmountQuarter = 1;
        int maxAmount = 0;

        for (int i = 0; i < salesData.length; i += salesData.length / quarters) {
            int totalAmount = 0;

            for (int j = i; j < i + salesData.length / quarters && j < salesData.length; j++) {
                totalAmount += salesData[j].getAmount();
            }

            if (totalAmount > maxAmount) {
                maxAmount = totalAmount;
                maxAmountQuarter = i / (salesData.length / quarters) + 1;
            }
        }

        return maxAmountQuarter;
    }

    static int findMaxUnitsQuarter(PenSaleRecord[] salesData, int quarters) {
        int maxUnitsQuarter = 1;
        int maxUnits = 0;

        for (int i = 0; i < salesData.length; i += salesData.length / quarters) {
            int totalUnits = 0;

            for (int j = i; j < i + salesData.length / quarters && j < salesData.length; j++) {
                totalUnits += salesData[j].getUnitsSold();
            }

            if (totalUnits > maxUnits) {
                maxUnits = totalUnits;
                maxUnitsQuarter = i / (salesData.length / quarters) + 1;
            }
        }

        return maxUnitsQuarter;
    }
    public static void main(String[] args) {
        int daysInYear = 365;
        PenSaleRecord[] salesData = generateSalesData(daysInYear);
        
        int monthsInYear = 12;
        int quartersInYear = 4;

        analyzeAndPrint("Month", salesData, daysInYear / monthsInYear);

        analyzeAndPrint("Quarter", salesData, daysInYear / quartersInYear);

        int maxAmountQuarter = findMaxAmountQuarter(salesData, quartersInYear);
        System.out.println("Quarter with Maximum Sales (Amt): " + maxAmountQuarter);
        int maxUnitsQuarter = findMaxUnitsQuarter(salesData, quartersInYear);
        System.out.println("Quarter with Maximum Sales (Units): " + maxUnitsQuarter);
    }
}

class PenSaleRecord {
    private int unitsSold;
    private int price;
    private int amount;

    public PenSaleRecord(int unitsSold, int price, int amount) {
        this.unitsSold = unitsSold;
        this.price = price;
        this.amount = amount;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
