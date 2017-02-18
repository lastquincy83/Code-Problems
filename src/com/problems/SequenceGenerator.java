package com.problems;

/**
 * Write a sequence generator where given a sample input "12"
 * it will generate a sequence as follows:
 *
 * 12
 * 1112
 * 3112
 * 132112
 * 1113122112
 * 311311222112
 * 13211321322112
 */



public class SequenceGenerator {
    public static void main(String[] args) {
        String input="12";
        int count=0;
        int i;
        String part="";
        for (int k=0;k<10;k++){
            part="";
            for (i=0;i<input.length();i++){
                count+=1;
                if(i<input.length()-2){
                    while(input.substring(i,i+1).equals(input.substring(i+1,i+2))){
                        count+=1;
                        i+=1;
                        if(i+2==input.length()-1){
                            count+=1;
                            i+=1;
                            if(input.substring(i,i+1).equals(input.substring(input.length()-1))){
                                count+=1;
                                i+=1;
                            }
                            break;
                        }
                    }
                }
                part=part+Integer.toString(count)+input.substring(i,i+1);
                count=0;
            }
            System.out.println(part);
            input=part;
        }
    }
}
