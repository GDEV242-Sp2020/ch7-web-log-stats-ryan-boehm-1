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
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
