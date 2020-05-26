import java.util.Scanner;

public class magicSquare {
    // Main code
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        boolean game = true ;
        int rows = 3;
        while (game){
            int rowSum = 0;
            int colSum = 0;
            global.magic = true;
            global.loShu = true;
            global.answer =0;


            int[] inputs = getInput(rows);
            printMatrix(rows,inputs);
            global.answer= getTotal(rows, inputs);

            int turns = 0;
            for (int j=0 ; j<rows ; j++){
                rowSum =0;
                for (int i=0 ; i<rows ; i++){
                    int position = i+turns;
                    rowSum += inputs[position];
                }
                turns += rows;
                checkMagic(global.answer , rowSum);
            }
            for (int j=0 ; j<rows ; j++){
                colSum =0;
                turns = 0;
                for (int i=0 ; i<rows ; i++){
                    int position = j+turns;
                    colSum += inputs[position];
                    turns += rows;
                }
                checkMagic(global.answer , colSum);}

            checkDiag(rows,inputs,"right");
            checkDiag(rows,inputs,"left");

            System.out.println("Is magic Square :"+ global.magic);
            System.out.println("Is Lo Shu Square :"+ (global.loShu && global.magic));

            System.out.println("Do you wanna give another try?.... \n y = yes , n = no \n type y/n...");
            String play = scan.nextLine();

            if (play.equals("Y") || play.equals("y")){
                continue;
            }
            else{
                System.out.println("Thank you for playing...");
                game = false;
            }
        }
    }

    // Getting user inputs , checking if it is less than 10 & if the numbers are equal
    //(Checking if its a loShu square)
    public static int[] getInput(int rows){
        Scanner scan = new Scanner(System.in);
        int count = 0;
        int[] num_list = new int[rows*rows];
        while (count < rows*rows){
            System.out.print("Enter number " + (count+1) + " : ");
            int num=0;
            try {
                String temp = scan.nextLine();
                num= Integer.parseInt(temp);
                for (int i =0 ; i< num_list.length ; i++){
                    if (num == num_list[i]){
                        global.loShu = false;
                    }
                }

                num_list[count]=num;

                if (num >= 10) {
                    global.loShu = false;
                }

                count += 1;
            }
            catch(Exception e ){
                System.out.println("incorrect type..");
            }
        }
        return num_list;
    }

    //To print the user inputs in a Matrix
    public static void printMatrix(int rows , int[] num_list){
        int position = 0;

        for(int j=0 ; j<rows ; j++){
            for (int i=0 ; i<rows ; i++){
                System.out.print(num_list[position+i]+"  ");
            }
            position += rows;
            System.out.println();
        }
    }

    // creating a class to store some Global variables
    public static class global{
        public static boolean magic;
        public static boolean loShu;
        public static int answer;

    }

    // getting sum of Diagonals
    public static void checkDiag (int rows, int num_list[], String side){
        int total = 0;
        int position;
        if (side == "right"){
            position = 0;
        }
        else{
            position = rows-1;
        }
        for(int i=0 ; i<rows ; i++) {
            total += num_list[position];
            if (side == "right") {
                position += rows + 1;
            } else {
                position += rows - 1;
            }
        }
        checkMagic(global.answer , total);
    }

    // To get total of the first Row
    //This total will be used to compare with other sums
    public static int getTotal(int rows , int[] num_list){
        int total =0;
        for (int i =0; i<rows ; i++){
            total += num_list[i];
        }
        return total;
    }

    //Comparing the answer and total to check if it is a magic Square
    public static void checkMagic (int answer , int total){

        if (answer != total){
            global.magic = false;
        }
    }
}




