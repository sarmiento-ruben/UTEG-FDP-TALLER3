package utils;

import utils.LoopHelperExecutable;

public class LoopHelper{   
    public static void runLoop(LoopHelperExecutable action){
        boolean running = true;
        while (running) {
            running = action.run();
        }
    }
}