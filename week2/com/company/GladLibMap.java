package com.company;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap=new HashMap<>();
    private ArrayList<String> wordsThatHaveBeenUsed=new ArrayList<>();
    private ArrayList<String> categories=new ArrayList<>();
    private Random myRandom;
    private static int numberOfWordsReplaced=0;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
      //  myMap=new HashMap<>();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
      //  myMap=new HashMap<>();
    }

    private void initializeFromSource(String source) {
        String[] array_of_categories=new String[10];
        array_of_categories[0]="noun";
        array_of_categories[1]="adjective";
        array_of_categories[2]="color";
        array_of_categories[3]="country";
        array_of_categories[4]="name";
        array_of_categories[5]="animal";
        array_of_categories[6]="timeframe";
        array_of_categories[7]="verb";
        array_of_categories[8]="fruit";
        array_of_categories[9]="number";
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<9;i++)
        {
            list=readIt(source+"/"+array_of_categories[i]+".txt");
            myMap.put(array_of_categories[i],list);
        }
        list.clear();
        list.add("1");
        myMap.put(array_of_categories[9],list);
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(myMap.get("country"));
        }
        if (label.equals("color")){
            return randomFrom(myMap.get("color"));
        }
        if (label.equals("noun")){
            return randomFrom(myMap.get("noun"));
        }
        if (label.equals("name")){
            return randomFrom(myMap.get("name"));
        }
        if (label.equals("adjective")){
            return randomFrom(myMap.get("adjective"));
        }
        if (label.equals("animal")){
            return randomFrom(myMap.get("animal"));
        }
        if (label.equals("timeframe")){
            return randomFrom(myMap.get("timeframe"));
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb")){
            return randomFrom(myMap.get("verb"));
        }
        if(label.equals("fruit")){
            return randomFrom(myMap.get("fruit"));
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        if(!categories.contains((w.substring(first+1,last))))
        categories.add(w.substring(first+1,last));
        String sub = getSubstitute(w.substring(first+1,last));
        while(!wordsThatHaveBeenUsed.contains(sub))
        {
            sub=getSubstitute(w.substring(first+1,last));
            wordsThatHaveBeenUsed.add(sub);
        }
        numberOfWordsReplaced++;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        System.out.println("\n");
           wordsThatHaveBeenUsed.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
    }
    public int totalWordsInMap()
    {
        int count=0;
        for(String key: myMap.keySet())
        {
            count+=myMap.get(key).size();
        }
        return count;
    }
    public int totalWordsConsidered()
    {
        int total=0;
        for(String category: categories)
        {
            total+=myMap.get(category).size();
        }
        return total;
    }
    public static void main(String args[])
    {

        GladLibMap gladLib=new GladLibMap();
        gladLib.initializeFromSource("/home/zadmin/Downloads/GladLib/data");
        gladLib.makeStory();
        //gladLib.getSubstitute();
        System.out.println();
        System.out.println("Number of words replaced: "+numberOfWordsReplaced);
        int totalWordsInMap=gladLib.totalWordsInMap();
        int totalWordsConsidered=gladLib.totalWordsConsidered();
        System.out.println("Total Words in map "+totalWordsInMap);
        System.out.println("Total Words considered "+totalWordsConsidered);
    }

}
