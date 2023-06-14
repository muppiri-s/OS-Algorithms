# OS-Algorithms

CPU Scheduling and Second Chance Page Replacement

# CPU Scheduling

CPU scheduling is the process of deciding which process to run on a CPU at a given time. There are many different CPU scheduling algorithms, each with its own advantages and disadvantages. Some of the most common CPU scheduling algorithms include:

1. First-come, first-served (FCFS): This is the simplest CPU scheduling algorithm. Processes are executed in the order in which they arrive.
2. Shortest-job-first (SJF): This algorithm executes the process with the shortest estimated execution time first.
3. Round-robin (RR): This algorithm divides the CPU time into time slices and assigns each process a time slice. When a process's time slice is up, it is preempted and another process is scheduled to run.
4. Priority scheduling: This algorithm schedules processes based on their priority. Processes with higher priority are executed before processes with lower priority.
The choice of CPU scheduling algorithm depends on the specific needs of the system. For example, a system with real-time applications may require a different CPU scheduling algorithm than a system with batch applications.

# Second Chance Page Replacement

Second chance page replacement is a page replacement algorithm that is used in virtual memory systems. It is a modified version of the first-in, first-out (FIFO) algorithm. In FIFO, the page that has been in memory the longest is replaced when a new page is needed. In second chance, each page is given a "second chance" before it is replaced. A page is marked as "used" when it is accessed by the CPU. When a new page is needed, the OS checks the pages that are marked as "used". If any of these pages have not been used recently, they are marked as "unused" and the least recently used page is replaced.
