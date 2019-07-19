package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Counting_Codons {
    private HashMap<String,Integer> hashMap=new HashMap<>();
    Counting_Codons(HashMap<String , Integer> hashMap)
    {
        this.hashMap=hashMap;
    }
    public void buildCodonMap(int start,String dna)
    {
      //  HashMap<String, Integer> hashmapStart=new HashMap<>();
        hashMap.clear();
        int i=start;
        while(i<=dna.length()-3)
        {
            String codon=dna.substring(i,i+3);
            if(!hashMap.containsKey(codon))
            {
                hashMap.put(codon,1);
            }
            else
                hashMap.put(codon,hashMap.get(codon)+1);
            i=i+3;
        }
        System.out.println("Number of unique codons="+hashMap.size());
        System.out.println("Most common codon: "+getMostCommonCodon());
    }
    public String getMostCommonCodon()
    {
        int max=0;
        String mostFrequentCodon="";
        for(String key: hashMap.keySet())
        {
            if(hashMap.get(key)>max)
            {
                max=hashMap.get(key);
                mostFrequentCodon=key;
            }
        }
        return mostFrequentCodon;
    }
    public void printCodonCounts(int start,int end)
    {
        for(String key: hashMap.keySet()) {
            int codon_count = hashMap.get(key);
            if (codon_count >= start && codon_count <= end) {
                System.out.println(key + " " + codon_count);
            }
        }
    }
    public void tester(String file_path) throws IOException
    {
        File file=new File(file_path);
        FileReader fileReader=new FileReader(file);
        BufferedReader br=new BufferedReader(fileReader);
        String dna_strand=br.readLine();
        dna_strand=dna_strand.toUpperCase().trim();
        for(int i=0;i<3;i++)
        {
            buildCodonMap(i,dna_strand);
            printCodonCounts(1,5);
        }
    }
    public static void main(String args[]) throws IOException
    {
        HashMap<String,Integer> hashmap=new HashMap<>();
        Counting_Codons counting_codons=new Counting_Codons(hashmap);
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the file path");
        String file_name= scanner.next();
        counting_codons.tester(file_name);
    }
}
