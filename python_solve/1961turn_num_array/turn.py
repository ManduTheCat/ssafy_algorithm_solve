import sys
from collections import deque

def turn_othogonal(N, arr: list):
  result = [ deque() for _ in range(N)]
  for fix_index in range(N):
    for move_index in range(N):
      result[fix_index].appendleft(arr[move_index][fix_index])
  #print(*result, sep="\n")
  return result

def main():
  input_count = int(input())
  for i in range(input_count):
    arr = []
    N = int(input())
    for _ in range(N):
      arr.append(list(map(int, sys.stdin.readline().strip().split())))
    #print(*arr, sep="\n")
    print(i)
    first = turn_othogonal(N, arr)
    second = turn_othogonal(N, first)
    third = turn_othogonal(N, second)

if __name__ == "__main__":
  main()
