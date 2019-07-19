package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class CharactersInPlay {
    private ArrayList<String> namesOfCharacters=new ArrayList<>();
    private ArrayList<Integer> countsOfCharacters=new ArrayList<>();
    CharactersInPlay(ArrayList<String> namesOfCharacters,ArrayList<Integer> countsOfCharacters)
    {
        this.namesOfCharacters=namesOfCharacters;
        this.countsOfCharacters=countsOfCharacters;
    }
    void update(String person)
    {
        if(!namesOfCharacters.contains(person))
        {
            namesOfCharacters.add(person);
            countsOfCharacters.add(1);
        }
        else
        {
            int index=namesOfCharacters.indexOf(person);
            countsOfCharacters.set(index,countsOfCharacters.get(index)+1);
        }
    }
    void findAllCharacters() throws IOException
    {
        namesOfCharacters.clear();
        countsOfCharacters.clear();
        File file=new File("/home/zadmin/Downloads/Coursera_course2/src/com/company/macbethSmall.txt");
        FileReader fileReader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String line_in_file;
        while((line_in_file= bufferedReader.readLine())!=null)
        {
            if(line_in_file.length()>0) {
                int indexOfFirstPeriod=line_in_file.indexOf('.');
                if(indexOfFirstPeriod!=-1)
                    update(line_in_file.substring(0,indexOfFirstPeriod).trim());
            }
        }
    }
    void tester() throws IOException
    {
        findAllCharacters();
        int i=0;
        double max=0;
        for(i=0;i<countsOfCharacters.size();i++) {
            if (countsOfCharacters.get(i) > max)
                max = countsOfCharacters.get(i);
        }
        int minimumNumberOfDialogues=(int)Math.ceil(max/3);
        int maximumNUmberOfDialogues=(int)max;
        for(i=0;i<countsOfCharacters.size();i++)
        {
            if(countsOfCharacters.get(i)>minimumNumberOfDialogues)
                System.out.println(namesOfCharacters.get(i)+" "+countsOfCharacters.get(i));
        }
        charactersWithNumParts(minimumNumberOfDialogues+1,maximumNUmberOfDialogues);
    }
    void charactersWithNumParts(int num1, int num2)
    {
        for(int i=0; i<countsOfCharacters.size();i++)
        {
            if(countsOfCharacters.get(i)>=num1 && countsOfCharacters.get(i)<=num2)
            {
                System.out.println(namesOfCharacters.get(i));
            }
        }
    }
    public static void main(String args[]) throws IOException
    {
        ArrayList<String> namesOfCharacters=new ArrayList<>();
        ArrayList<Integer> countsOfCharacters=new ArrayList<>();
        CharactersInPlay charactersInPlay=new CharactersInPlay(namesOfCharacters,countsOfCharacters);
        charactersInPlay.findAllCharacters();
        charactersInPlay.tester();
    }
}
