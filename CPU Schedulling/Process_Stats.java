package scheduling;

public class Process_Stats {
    int pid;
    int arrival_time;
    int burst_time;
    int remaining_burst;
    public int wait_time;
    public int demotion_count;
    int quantum_remaining;
    public Process_Stats(int pid, int arrival_time, int burst_time, int quantum) {
        this.pid = pid;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.wait_time = 0;
        this.demotion_count = 0;
        this.quantum_remaining = quantum;
        this.remaining_burst = burst_time;
    }
}
