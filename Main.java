package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        double [][] dataset = {{2.7810836,2.550537003,0},
                {1.465489372,2.362125076,0},
                {3.396561688,4.400293529,0},
                {1.38807019,1.850220317,0},
                {3.06407232,3.005305973,0},
                {7.627531214,2.759262235,1},
                {5.332441248,2.088626775,1},
                {6.922596716,1.77106367,1},
                {8.675418651,-0.242068655,1},
                {7.673756466,3.508563011,1}};
        double [] row0 = dataset[0];
        double prediction = prediction(dataset,row0,3);
        System.out.println(prediction);
    }

    public static double dist(double[] row1 ,double[] row2){
        double distance = 0;
        for(int i=0;i<row1.length;i++){
            distance += Math.pow(row1[i]-row2[i], 2);
        }
        return Math.sqrt(distance);
    }

    public static double[][] get_neighbors(double[][] train, double[] test_row, int num_neighbors){
        double [] distances = new double[train.length];
        int index = 0;
        for(double[] train_row: train){
            double dist = dist(test_row, train_row);
            distances[index] = dist;
            index++;
        }
        int [] minDist = findminX(distances, num_neighbors);
        double [][] neighbors = new double[num_neighbors][test_row.length];
        for(int i=0; i<num_neighbors; i++){
            neighbors[i] = train[minDist[i]];
        }
        return neighbors;
    }

    public static double prediction(double[][] train, double[] test_row, int num_neighbors){
        double[][] neighbors = get_neighbors(train, test_row, num_neighbors);
        double[] output = new double[neighbors.length];
        for(int i=0;i<neighbors.length;i++){
            output[i] = neighbors[i][neighbors[i].length-1];
        }
        return getPopularElement(output);
    }


    public static int[] findminX(double[] lst, int x){
        int [] minindex = new int[x];
        double[] copy = lst.clone();
        for(int i=0; i<x; i++){
            int index = 0;
            for(int a=0;a< copy.length;a++){
                if(copy[index] >= 0){
                    if(copy[a] < copy[index] & copy[a] >= 0) {
                        index = a;
                    }
                }else index++;
            }
            copy[index] = -1;
            minindex[i] = index;
        }
        return minindex;
    }

    public static double getPopularElement(double[] a)
    {
        int count = 1, tempCount;
        double popular = a[0];
        double temp;
        for (int i = 0; i < (a.length - 1); i++)
        {
            temp = a[i];
            tempCount = 0;
            for (int j = 1; j < a.length; j++)
            {
                if (temp == a[j])
                    tempCount++;
            }
            if (tempCount > count)
            {
                popular = temp;
                count = tempCount;
            }
        }
        return popular;
    }
}
