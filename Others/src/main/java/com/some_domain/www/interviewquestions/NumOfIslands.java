package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 22-11-2022 04:05 pm
 * <p>
 * Reference : https://www.youtube.com/watch?v=o8S2bO3pmO4
 */
public class NumOfIslands {

    public static void main(String[] args) {

       /* char[][] grid = {{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};*/

        char[][] grid = {{'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};

        NumOfIslands instance = new NumOfIslands();
        int numberOfIslands = instance.countNumberOfIslands(grid);
        System.out.println("Number of islands is : " + numberOfIslands);
    }

    private int countNumberOfIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islandCount += dfs(grid, i, j);
                }
            }
        }

        return islandCount;
    }

    private int dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0')
            return 0;

        grid[i][j] = '0';  //Set the cell to avoid counting it more than once

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return 1;
    }
}
