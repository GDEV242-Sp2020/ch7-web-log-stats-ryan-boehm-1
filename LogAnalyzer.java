/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate the daily access counts.
    private int[] dailyCounts;
    //Where to calculate the monthly access counts.
    private int[] monthlyCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses. 
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dailyCounts = new int[31];
        monthlyCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Create an object to analyze hourly web accesses of a log file with
     * a specific name. 
     */
    public LogAnalyzer(String logName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dailyCounts = new int[31];
        monthlyCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader(logName);
    }
    
    /**
     * Complete the numberOfAccesses method t count total number of accesses record in the log file
     * @return number of accesses
     */
    
    public int numberOfAccesses()
    {
        int total = 0;
        for(int i = 0; i < hourCounts.length; i++) {
            total = total + hourCounts[i]; 
        }
        return total;
    }
    

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData() {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dailyCounts[day-1]++;
        }
    }
    
    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData() {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthlyCounts[month-1]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the daily counts.
     * These should have been set with a prior
     * call to analyzeDailyData.
     */
    public void printDailyCounts() {
        System.out.println("Day: Count");
        for(int day = 0; day < dailyCounts.length; day++) {
            System.out.println(day + ": " + dailyCounts[day]);
        }
    }
    
    /**
     * Print the monthly counts.
     * These should have been set with a prior
     * call to analyzeMonthlyData.
     */
    public void printMonthlyCounts() {
        System.out.println("Month: Count");
        for(int month = 0; month < monthlyCounts.length; month++) {
            System.out.println(month + ": " + monthlyCounts[month]);
        }
    }
    
    /**
     * Returns the busiest hour of the day.
     */
    public int busiestHour() {
        int busiestAmount = 0;
        int busiestHour = 0;
        for(int i = 0; i < hourCounts.length; i++) {
            if(hourCounts[i] > busiestAmount) {
                busiestHour = i;
                busiestAmount = hourCounts[i];
            }
        }
        return busiestHour;
    }
    
    /**
     * Returns the busiest day of the day.
     */
    public int busiestDay() {
        int busiestAmount = 0;
        int busiestDay = 0;
        for(int i = 0; i < dailyCounts.length; i++) {
            if(dailyCounts[i] > busiestAmount) {
                busiestDay = i;
                busiestAmount = dailyCounts[i];
            }
        }
        return busiestDay;
    }
    
    /**
     * Returns the busiest month of the day.
     */
    public int busiestMonth() {
        int busiestAmount = 0;
        int busiestMonth = 0;
        for(int i = 0; i < monthlyCounts.length; i++) {
            if(monthlyCounts[i] > busiestAmount) {
                busiestMonth = i;
                busiestAmount = monthlyCounts[i];
            }
        }
        return busiestMonth;
    }
    
    /**
     * Returnsz the quietest hour of the day.
     */
    public int quietestHour() {
        int temp = busiestHour();
        int quietestAmount = hourCounts[temp];
        int quietestHour = 0;
        for(int i = 0; i < hourCounts.length; i++) {
            if(!(hourCounts[i] > quietestAmount)) {
                quietestHour = i;
                quietestAmount = hourCounts[i];
            }
        }
        return quietestHour;
    }
    
    /**
     * Returns the busiest two-hour period.
     */
    public int busiestTwoHour() {
        int busiestAmount = 0;
        int busiestHour = 0;
        for(int i = 1; i < hourCounts.length; i++) {
            if(hourCounts[i-1] + hourCounts[i] > busiestAmount) {
                busiestHour = i-1;
                busiestAmount = hourCounts[i-1] + hourCounts[i];
            }
        }
        return busiestHour;
    }
    
    /**
     * Returns the total number of accesses in a single month.
     * @return the amount of accesses in a month.
     */
    public int totalAccessesPerMonth() {
        int accesses = 0;
        for(int i = 0; i < dailyCounts.length; i++) {
            accesses = accesses + dailyCounts[i];
        }
        return accesses;
    }
    
    /**
     * Returns the average amount of accesses each month.
     * @return the average amount of accesses each month.
     */
    public int averageAccessesPerMonth() {
        int accesses = 0;
        for(int i = 0; i < monthlyCounts.length; i++) {
            accesses = accesses + monthlyCounts[i];
        }
        return accesses / 12;
    }
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
