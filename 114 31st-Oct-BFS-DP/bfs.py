from collections import deque


n = int(input())
grid = [list(map(int,input().split())) for _ in range(n)]
init = list(map(int,input().split()))
safe = list(map(int,input().split()))
dp = [[float('inf')] * n for _ in range(n)]
dp[init[0]][init[1]] = 0

q = deque()
q.append((init[0], init[1]))
dirs = [
    [1, 0],   
    [-1, 0],
    [0, 1],   
    [0, -1]   
]

while q:
    x, y = q.popleft()
    for dx, dy in dirs:
        nx, ny = x, y
        steps = 0
        while 0 <= nx + dx < n and 0 <= ny + dy < n and grid[nx + dx][ny + dy] == 0:
            nx += dx
            ny += dy
            steps += 1
        if dp[x][y] + steps < dp[nx][ny]:
            dp[nx][ny] = dp[x][y] + steps
            q.append((nx, ny))

res = dp[safe[0]][safe[1]]
print(-1 if res == float('inf') else res)