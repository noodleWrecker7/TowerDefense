package adtosh.towerdefense.levels;

public class LevelPaths {

    // stores an array of an array of coords :)
    // stores coordinates for every path, so [levelNum][coordNum][0] gives you x coord
    static int[][][] paths = {
            { // level-0

                    {0, 560},
                    {550, 560},
                    {550, 920},
                    {160, 920},
                    {160, 100},
                    {800, 100},
                    {800, 920},
                    {1380, 920},
                    {1380, 100},
                    {1050, 100},
                    {1050, 560},
                    {1600, 560}
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
