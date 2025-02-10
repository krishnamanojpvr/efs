/*
You have an array of project objects, each with name, 
revenue, and cost. Use filter to include only projects 
where revenue is greater than cost (profitable projects), 
map to calculate the profit for each project, and reduce 
to find the total profit from these profitable projects.


Sample Input:
-------------
5
ProjectA 500 300
ProjectB 200 250
ProjectC 600 400
ProjectD 150 100
ProjectE 300 400

Sample Output: 
--------------
450

Explanation:
------------
Profitable projects are ProjectA, ProjectC, and ProjectD.
Profits for each are: ProjectA = 200, ProjectC = 200, ProjectD = 50.
Total profit = 200 + 200 + 50 = 450

*/

const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
rl.question("", (N) => {
  N = parseInt(N);
  let arr = [];
  let count = 0;
  rl.on("line", (line) => {
    const [name, revenue, cost] = line.split(" ");
    arr.push({
      name: name,
      revenue: parseFloat(revenue),
      cost: parseFloat(cost),
    });
    count++;
    if (count === N) {
      console.log(calculate(arr));
      rl.close();
    }
  });
});
function calculate(arr) {
  return arr
    .filter((item) => item.revenue > item.cost)
    .map((item) => item.revenue - item.cost)
    .reduce((acc, item) => acc + item, 0);
}
