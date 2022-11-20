package com.some_domain.www.interviewquestions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : asraar
 * @date : 20-11-2022 11:50 am
 * <p>
 * Reference : https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
 */
public class RottingOrangesProblem {

    public static void main(String[] args) {

        RottingOrangesProblem instance = new RottingOrangesProblem();

        int array[][] = {{2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};

       /* int array[][] = {{2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}};*/

        /*int array[][] = {{2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}};*/

        int ans = instance.rotOranges(array);
        if (ans == -1)
            System.out.println("All oranges cannot rot");
        else
            System.out.println("Time required for all oranges to rot : " + ans);
        System.out.println("Time and space complexity is O(R * C)");
    }

    private int rotOranges(int[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        int answer = 0;
        Queue<CellDetails> queue = new LinkedList<>();

        //Push all the cells with rotten oranges first
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (array[i][j] == 2) {
                    queue.offer(new CellDetails(i, j));
                }
            }
        }

        // Separate these rotten oranges from the oranges
        // which will rotten due the oranges in first time
        // frame using delimiter which is (-1, -1)
        queue.offer(new CellDetails(-1, -1));

        while (!queue.isEmpty()) {
            boolean flag = false;

            while (!isNotDelimiter(queue.peek())) {
                CellDetails cell = queue.peek();


                //Check if right adjacent cell can be rotten
                if (isValid(cell.x + 1, cell.y, rows, columns) && array[cell.x + 1][cell.y] == 1) {
                    if (!flag) {
                        answer++;
                        flag = true;
                    }
                    //Make the orange rotten
                    array[cell.x + 1][cell.y] = 2;
                    queue.offer(new CellDetails(cell.x + 1, cell.y));
                }

                //Check if left adjacent cell can be rotten
                if (isValid(cell.x - 1, cell.y, rows, columns) && array[cell.x - 1][cell.y] == 1) {
                    if (!flag) {
                        answer++;
                        flag = true;
                    }
                    //Make the orange rotten
                    array[cell.x - 1][cell.y] = 2;
                    queue.offer(new CellDetails(cell.x - 1, cell.y));
                }

                //Check if top adjacent cell can be rotten
                if (isValid(cell.x, cell.y + 1, rows, columns) && array[cell.x][cell.y + 1] == 1) {
                    if (!flag) {
                        answer++;
                        flag = true;
                    }
                    //Make the orange rotten
                    array[cell.x][cell.y + 1] = 2;
                    queue.offer(new CellDetails(cell.x, cell.y + 1));
                }

                //Check if bottom adjacent cell can be rotten
                if (isValid(cell.x, cell.y - 1, rows, columns) && array[cell.x][cell.y - 1] == 1) {
                    if (!flag) {
                        answer++;
                        flag = true;
                    }
                    //Make the orange rotten
                    array[cell.x][cell.y - 1] = 2;
                    queue.offer(new CellDetails(cell.x, cell.y - 1));
                }

                queue.remove();
            }

            //Pop the delimiter
            queue.remove();

            if (!queue.isEmpty()) {
                queue.offer(new CellDetails(-1, -1));
            }
        }
        return checkAllCells(array, rows, columns) ? -1 : answer;
    }

    private boolean isValid(int i, int j, int rows, int columns) {
        return (i >= 0 && j >= 0 && i < rows && j < columns);
    }

    private boolean checkAllCells(int[][] array, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (array[i][j] == 1)
                    return true;
            }
        }
        return false;
    }

    private boolean isNotDelimiter(CellDetails cellDetails) {
        return (cellDetails.x == -1 && cellDetails.y == -1);
    }

    private class CellDetails {
        private int x;
        private int y;

        public CellDetails(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
