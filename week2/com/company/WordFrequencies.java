package com.company;
import java.util.*;
import java.io.*;
import java.io.FileReader;
public class WordFrequencies {

    private ArrayList<String> myWords=new ArrayList<>();
    private ArrayList<Integer> myFreqs=new ArrayList<>();
    WordFrequencies(ArrayList<String> myWords,ArrayList<Integer> myFreqs)
    {
        this.myWords=myWords;
        this.myFreqs=myFreqs;
    }
    void findUnique() throws IOException
    {
        myWords.clear();
        myFreqs.clear();
        File file=new File("/home/zadmin/Downloads/Coursera_course2/src/com/company/testwordfreqs.txt");
        FileReader file_reader=new FileReader(file);
        BufferedReader br=new BufferedReader(file_reader);
        String line_in_file;
        while((line_in_file=br.readLine())!=null)
        {
            String words_array[]=line_in_file.split(" ");
            for(int i=0;i<words_array.length;i++)
            {
                words_array[i]=words_array[i].toLowerCase();
                if(!myWords.contains(words_array[i]))
                {
                    myWords.add(words_array[i]);
                    myFreqs.add(1);
                }
                else
                {
                    int index=myWords.indexOf(words_array[i]);
                    myFreqs.set(index,(myFreqs.get(index)+1));
                }
            }
        }
    }
    void tester() throws IOException
    {
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        for(int i=0;i<myWords.size();i++)
        {
            System.out.println(myFreqs.get(i)+" "+myWords.get(i));
        }
        int highest_frquency=findIndexOfMax();
        System.out.println("The word that occurs most often and its count are "+myWords.get(highest_frquency)+" "+myFreqs.get(highest_frquency));
    }
    int findIndexOfMax()
    {
        int max=0;
        int index=0;
        for(int i=0;i<myFreqs.size();i++)
        {
            int frequency=myFreqs.get(i);
            if(frequency>max)
            {
                max=frequency;
                index=i;
            }
        }
        return index;
    }
    public static void main(String args[]) throws IOException
    {
        ArrayList<String> myWords=new ArrayList<>();
        ArrayList<Integer> myFreqs=new ArrayList<>();
        WordFrequencies wordFrequencies=new WordFrequencies(myWords,myFreqs);
        wordFrequencies.findUnique();
        wordFrequencies.tester();
    }

}
