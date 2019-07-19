package course2Week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Date;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/short-test_log");
        la.printAll();
    }
    public void testUniqueIP()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/short-test_log");
        System.out.println(la.countUniqueIPs());
    }
    public void testPrintAllHigherThanNumber()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/short-test_log");
        la.printAllHigherThanNum(200);
    }
    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/weblog-short_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 30"));
    }
    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/short-test_log");
        System.out.println(la.countUniqueIPsInRange(200,299));
    }
    public void testCountVisitsPerIP()
    {

        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/short-test_log");
        System.out.println(la.countVisitsPerIP());
    }
    public void testMostNumberVisitsByIP()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/weblog3-short_log");
        System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIP()));
    }
    public void testIPsMostVisits()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/weblog3-short_log");
        System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
    }
    public void testIPsForDays()
    {

        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/weblog3-short_log");
        System.out.println(la.iPsForDays());
    }
    public void testDayWithMostIPVisits()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/weblog3-short_log");
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    }
    public void testIPsWithMostVisitsOnDay()
    {

        LogAnalyzer la=new LogAnalyzer();
        la.readFile("src/course2Week3/weblog3-short_log");
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(),"Sep 30"));
    }
    public static void main(String args[])
    {
        Tester t=new Tester();
        t.testLogAnalyzer();
        t.testUniqueIP();
        t.testPrintAllHigherThanNumber();
        t.testUniqueIPVisitsOnDay();
        t.testCountUniqueIPsInRange();
        t.testCountVisitsPerIP();
        t.testMostNumberVisitsByIP();
        t.testIPsMostVisits();
        t.testIPsForDays();
        t.testDayWithMostIPVisits();
        t.testIPsWithMostVisitsOnDay();
    }
}
