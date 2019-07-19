package course2Week3;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records=new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr=new FileResource(filename);
         for(String line:fr.lines())
         {
             LogEntry l= WebLogParser.parseEntry(line);
             records.add(l);
         }
     }
     public int countUniqueIPs()
     {
         ArrayList<String> temp=new ArrayList<>();
         for(LogEntry l:records)
         {
             if(!temp.contains(l.getIpAddress()))
             {
                temp.add(l.getIpAddress());
             }
         }
         return temp.size();
     }
     public void printAllHigherThanNum(int num)
     {
         for(LogEntry l:records)
             if(l.getStatusCode()>num)
                 System.out.println(l);

     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
         ArrayList<String> temp=new ArrayList<>();
         for(LogEntry l:records)
         {
             if(!temp.contains(l.getIpAddress()) && l.getAccessTime().toString().contains(someday))
             {
                 temp.add(l.getIpAddress());
             }
         }
         return temp;
     }
     public int countUniqueIPsInRange(int low,int high)
     {
         ArrayList<String> temp=new ArrayList<>();
         for(LogEntry l:records)
             if(!temp.contains(l.getIpAddress()) && l.getStatusCode()>=low && l.getStatusCode()<=high)
                 temp.add(l.getIpAddress());
             return temp.size();
     }
     public HashMap<String,Integer> countVisitsPerIP()
     {
         HashMap<String,Integer> IpVisits=new HashMap<>();
         for(LogEntry l:records)
         {
             if(IpVisits.containsKey(l.getIpAddress()))
             {
                 IpVisits.put(l.getIpAddress(),IpVisits.get(l.getIpAddress())+1);
             }
             else
                 IpVisits.put(l.getIpAddress(),1);
         }
         return IpVisits;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> temp)
     {
         int max=0;
         for(Integer i:temp.values())
         {
             if(max<i)
                 max=i;
         }
         return max;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> temp)
     {
         ArrayList<String> mostVisitsByIp=new ArrayList<>();
         int max=mostNumberVisitsByIP(temp);
         for(String s:temp.keySet())
         {
             if(temp.get(s)==max)
             {
                 mostVisitsByIp.add(s);
             }
         }
         return mostVisitsByIp;
     }
     public HashMap<String,ArrayList<String>> iPsForDays()
     {
         HashMap<String,ArrayList<String>> ipAddressesOnDate=new HashMap<>();
         for(LogEntry l:records)
         {
             String date=l.getAccessTime().toString().substring(4,10);
             if(!ipAddressesOnDate.containsKey(date))
             {
                 ArrayList<String> temp=new ArrayList<>();
                 for(LogEntry l1:records)
                 {
                     if(l1.getAccessTime().toString().contains(date))
                     {
                         temp.add(l1.getIpAddress());
                     }
                 }
                 ipAddressesOnDate.put(date,temp);
             }
         }
         return ipAddressesOnDate;
     }
     public String  dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipAddressesOnDate)
     {
         int max=0;
         String date="";
         for(String i:ipAddressesOnDate.keySet())
         {
             if(max<ipAddressesOnDate.get(i).size())
             {
                 max=ipAddressesOnDate.get(i).size();
                 date=i;
             }
         }
         return date;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipAddressesOnDate,String date)
     {
         HashMap<String,Integer> temp=new HashMap<>();
        for(String s:ipAddressesOnDate.get(date))
        {
            if(!temp.containsKey(s))
            {
                temp.put(s,1);
            }
            else
                temp.put(s,temp.get(s)+1);
        }
        return iPsMostVisits(temp);
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
