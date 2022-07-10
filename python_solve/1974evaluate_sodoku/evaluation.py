
def main():
  input_count = int(input())
  for i in range(input_count):
    sodoku = []
    print(i)
    for _ in range(9):
      sodoku.append(list(map(int, input().split())))
    print(*sodoku, sep='\n')
if __name__ == "__main__":
  main()
