package com.avp42.interview;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/3/20 0020
 */
public class FindSuspectAttacker {
    public static final long _1_MINUTES = 60 * 1000;

    static class Log{
        String clientId;
        String userName;
        Long loginTimestamp;
        String verifyCode;
        String inputVerifyCode;
        public Log(String logLine) {
            String[] split = logLine.split(",");
            clientId = split[0];
            userName = split[1];
            loginTimestamp = Long.valueOf(split[2]);
            verifyCode = split[3];
            inputVerifyCode = split[4];
        }
        public boolean verifyFailed(){
            return !verifyCode.equalsIgnoreCase(inputVerifyCode);
        }
    }

    public static Set<String> findSuspect(String fileName) throws IOException {
        Set<String> res = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Map<String, Integer> nameToCount = new HashMap<>(8);
        List<Log> inWindow = new LinkedList<>();
        Log log, leftBound = null;
        String line;
        while((line = reader.readLine()) != null){
            log = new Log(line);
            if(res.contains(log.userName)) continue;
            while(leftBound!=null && leftBound.loginTimestamp + _1_MINUTES < log.loginTimestamp){
                inWindow.remove(0);
                if(leftBound.verifyFailed()){
                    nameToCount.computeIfPresent(leftBound.userName, (k, v) -> v > 0 ? v - 1 : 0);
                }
                if(inWindow.size() == 0) {
                    leftBound = null;
                }else{
                    leftBound = inWindow.get(0);
                }
            }
            if(log.verifyFailed()){
                nameToCount.compute(log.userName, (k, v) -> v == null ? 1 : v + 1);
            }
            inWindow.add(log);
            if(leftBound == null) leftBound =  log;

            Iterator<Map.Entry<String, Integer>> iterator = nameToCount.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Integer> entry = iterator.next();
                if(entry.getValue() >= 10){
                    res.add(entry.getKey());
                    iterator.remove();
                }else if(entry.getValue() <= 0){
                    iterator.remove();
                }
            }
        }
        return res;
    }


    public static Set<String> findSuspect2(String fileName) throws IOException {
        Set<String> res = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Map<String, Integer> nameToCount = new HashMap<>(8);
        List<Log> inWindow = new LinkedList<>();
        Log log, leftBound = null;
        String line;
        while((line = reader.readLine()) != null){
            log = new Log(line);
            if(res.contains(log.userName)) continue;
            while(leftBound!=null && leftBound.loginTimestamp + _1_MINUTES < log.loginTimestamp){
                inWindow.remove(0);
                if(leftBound.verifyFailed()){
                    nameToCount.computeIfPresent(leftBound.userName, (k, v) -> v > 0 ? v - 1 : 0);
                }
                if(inWindow.size() == 0) {
                    leftBound = null;
                }else{
                    leftBound = inWindow.get(0);
                }
            }
            if(log.verifyFailed()){
                Integer count = nameToCount.compute(log.userName, (k, v) -> v == null ? 1 : v + 1);
                if(count >= 10) res.add(log.userName);
            }
            inWindow.add(log);
            if(leftBound == null) leftBound =  log;
        }
        return res;
    }



    public static void main(String[] args) throws IOException {
        String filename = "D:\\myspace\\github\\learning\\datastructure_algorithm\\src\\main\\resources\\findSuspectAttacker.txt";
        Set<String> suspect = findSuspect(filename);
        System.out.println(suspect);
    }

}
