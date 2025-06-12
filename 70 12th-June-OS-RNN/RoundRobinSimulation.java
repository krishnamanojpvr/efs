// You are tasked with simulating a university print shop that schedules jobs using
// priority-based Round Robin scheduling. Each job has a user type (Staff gets 
// higher priority than Student), an arrival time, and a burst time. Jobs of the 
// same priority follow the Round Robin algorithm with a fixed time quantum.

// Input Format:
// -------------
//  - Number of jobs
//  - For each job: JobID ArrivalTime BurstTime UserType
// - Time Quantum

// Output Format:
// --------------
//  - Per-job waiting time and turnaround time
//  - Average Waiting Time
//  - Average Turnaround Time

// Sample Input-1:
// ---------------
// 4
// P1 0 5 Staff
// P2 1 4 Student
// P3 2 3 Staff
// P4 3 2 Student
// 2

// Sample Output-1:
// -----------------
// JobID  WaitingTime  TurnaroundTime
// P1     3            8
// P2     9            13
// P3     2            5
// P4     7            9
// Average Waiting Time: 5.25
// Average Turnaround Time: 8.75


// Sample Input-2:
// --------------
// 3
// J1 0 6 Student
// J2 1 4 Staff
// J3 2 2 Student
// 2

// Sample Output-2:
// ----------------
// JobID  WaitingTime  TurnaroundTime
// J1     6            12
// J2     1            5
// J3     4            6
// Average Waiting Time: 3.67
// Average Turnaround Time: 7.67

