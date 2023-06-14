package scheduling;

import java.util.*;
import java.io.*;

public class Simulation {
    public static class Process {

        public int id;
        public int joinedTime;
        public int execTime;
        public int waitTime;
        public int demoLeft;
        public int completionTime;

        public Process(int Id, int joinedTime, int execTime, int waitTime, int demoLeft) {
            this.id = Id;
            this.joinedTime = joinedTime;
            this.execTime = execTime;
            this.waitTime = waitTime;
            this.demoLeft = demoLeft;
            this.completionTime = 0;
        }
    }

    public static void main(String args[]) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter FileName Below :");
        String fileName = scan.nextLine();
        System.out.println("Enter Demotion Criteria :");
        int demotion = scan.nextInt();
        System.out.println("Enter Dispatch Ratio :");
        int dispatchr = scan.nextInt();
        File f = new File("C://Users//sahit//IdeaProjects//OS_AS04//src//"+fileName);
        scan.close();
        Scanner sc = new Scanner(f);

        List<Process> queueA = new ArrayList<Process>();
        List<Process> queueB = new ArrayList<Process>();
        List<Process> dispatched = new ArrayList<Process>();
        Process cpu = null;
        int qAq = 5;
        int qBq = 40;
        int clock = -1;
        int pathCount = 0;
        int quanta = 0;
        int totalexecTime = 0, totalIdle = 0, avgWaitTime = 0;

        Process linepair;
        while (sc.hasNextLine() || queueA.size() > 0 || queueB.size() > 0 || cpu != null) {
            Process removed = null;

            if (cpu != null) {
                // System.out.println(cpu.id + "clock:" + clock);
                if (cpu.execTime == cpu.id) {
                    cpu.completionTime = clock;
                    dispatched.add(cpu);
                    cpu = null;
                } else {
                    if (quanta > 0) {
                        cpu.execTime += 1;
                        quanta -= 1;
                    } else {
                        cpu.demoLeft -= 1;
                        if (cpu.demoLeft > 0) {
                            queueA.add(cpu);
                        } else {
                            queueB.add(cpu);
                        }
                        cpu = null;
                    }
                }
            }
            if (cpu == null) {
                if (queueA.size() > 0 && (dispatchr == 0 || pathCount < dispatchr)) {
                    removed = queueA.remove(0);
                    quanta = qAq - 1;
                    pathCount += 1;
                } else if (queueB.size() > 0) {
                    removed = queueB.remove(0);
                    if (pathCount == dispatchr) {
                        pathCount = 0;
                    } else {
                        pathCount += 1;
                    }
                    quanta = qBq - 1;
                } else {
                    if (queueA.size() > 0) {
                        removed = queueA.remove(0);
                        quanta = qAq;
                    }
                }
                if (removed != null) {
                    cpu = removed;
                    cpu.execTime += 1;
                }

            }
            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.equals("idle")) {
                    int line1 = Integer.parseInt(line);
                    linepair = new Process(line1, clock, 0, 0, demotion);
                    queueA.add(linepair);
                }
            }
            incrementWaitTime(queueA, queueB);

            clock += 1;
            if (cpu == null) {
                totalIdle += 1;
            }
        }
        sc.close();
        for (int i = 0; i < dispatched.size(); i += 1) {
            Process x = dispatched.get(i);
            totalexecTime += x.execTime;
            avgWaitTime += x.waitTime;
        }
        System.out.println("*******Result*******\n");
        System.out.println("EndTime: " + clock);
        System.out.println("Processes Completed: " + dispatched.size());
        System.out.println("Total Execution Time: " + totalexecTime);
        System.out.println("Idle Time: " + totalIdle);
        System.out.println("AvgWait Time: " + avgWaitTime / dispatched.size());
        System.out.println("\n*******End*******");

    }

    public static void incrementWaitTime(List<Process> queueA, List<Process> queueB) {

        for (int i = 0; i < queueA.size(); i += 1) {
            queueA.get(i).waitTime += 1;
        }
        for (int i = 0; i < queueB.size(); i += 1) {
            queueB.get(i).waitTime += 1;
        }
    }
}
