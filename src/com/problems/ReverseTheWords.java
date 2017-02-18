package com.problems;

/**
 * Write code to reverse the words in a given array of characters.
 * Given the input:
 * ['t','h','e',' ','d','o','g',' ','c','h','a','s','e','s',' ','t','h','e',' ','c','a','t']
 *
 * it should produce the output:
 * ['c','a','t',' ','t','h','e',' ','c','h','a','s','e','s',' ','d','o','g',' ','t','h','e']
 */



public class ReverseTheWords {

    private static char[] inputArray = new char[]{'t', 'h','e',' ','d','o','g',' ','c','h','a','s','e','s',' ','t','h','e',' ','c','a','t',' ','f','a','s','t'};

    public static void main(String[] args) {

        String[] outputArray= new String [5];
        int check=0;
        String word = "";
        int l=0;
        for(int i = 0; i< inputArray.length; i++){
//
            if(inputArray[i]== (' ') || i== inputArray.length-1) {
                for(int k=check;k<(i);k++) {
                    word = word+(Character.toString(inputArray[k]));
                }
                if(check==0){
                    outputArray[4-l]=word;
                }else if(i== inputArray.length-1){
                    word = word+(Character.toString(inputArray[i]));
                    outputArray[4-l]=word+" ";
                }else outputArray[4-l]=word+" ";

                check=i+1;
                l = l+1;
                word="";
            }

        }
        char newarr[] = new char[inputArray.length+5];
        int count=0;
        for(int i=0;i<outputArray.length;i++) {
            char outarr[] = outputArray[i].toCharArray();
            for(int k=0;k<outarr.length;k++) {
                    newarr[count]=outarr[k];
                    count+=1;
            }
        }
        System.out.println(inputArray);
        System.out.println(newarr);



    }
}
