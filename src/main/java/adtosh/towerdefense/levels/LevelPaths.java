package adtosh.towerdefense.levels;

public class LevelPaths {

    // stores an array of an array of coords :)
    // stores coordinates for every path, so [levelNum][coordNum][0] gives you x coord
    static int[][][] paths = {
            { // level-0
                    {0, 540},
                    {550, 540},
                    {550, 900},
                    {160, 900},
                    {160, 80},
                    {800, 80},
                    {800, 900},
                    {1380, 900},
                    {1380, 80},
                    {1050, 80},
                    {1050, 540},
                    {1500, 540}
            },

            {
                //level 1
                    {0, 100},
                    {850,100},
                    {850, 300},
                    {100, 300},
                    {100, 600},
                    {1050, 600},
                    {1050, 100},
                    {1380, 100},
                    {1380, 800},
                    {100, 800},
                    {100, 1000},
                    {1500, 1000}

            }
    };


}
