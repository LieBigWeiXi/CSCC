package com.example.atlas.cscc20.MyTool;


/**
 * NumUtil:守护进程标志工具
 * number:在BaseActivity的OnCreate(),OnRestart()方法中置为1，
 *        在OnStop()方法中置为0;
 *        在CheckThread线程中，若()ms间隔内number一直为0，
 *        则判断用户按下Home键，list键，或在MainActivity中按下Back键，
 *        此时则调用run()方法，强制跳转至MainActivity，实现守护功能
 * flag:
 */


public class NumUtil {
    private static volatile int number;
    private static volatile boolean openFlag;

    public static boolean isOpenFlag() {
        return openFlag;
    }

    public static void setOpenFlag(boolean openFlag) {
        NumUtil.openFlag = openFlag;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        NumUtil.number = number;
    }

}
