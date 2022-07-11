from collections import deque

def turn_othogonal(N, arr: list):
  result = [deque() for _ in range(N)]
  for fix_index in range(N):
    for move_index in range(N):
      result[fix_index].appendleft(arr[move_index][fix_index])
  return result

def print_res(N, res: list):
  for i in range(N):
    print()
    for j in range(3):
      print(*res[j][i], sep="", end=" ")
  print()

def main():
  input_count = int(input())
  for i in range(input_count):
    arr = []
    res = []
    N = int(input())
    for _ in range(N):
      arr.append(list(map(int, input().split())))
    first = turn_othogonal(N, arr)
    second = turn_othogonal(N, first)
    third = turn_othogonal(N, second)
    res.append(first)
    res.append(second)
    res.append(third)
    print(f"#{i+1}", end="")
    print_res(N, res)

if __name__ == "__main__":
  main()
