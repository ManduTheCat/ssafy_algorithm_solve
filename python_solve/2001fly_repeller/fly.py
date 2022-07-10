def sum_fly(start_row_index,start_col_index, area, M):
  sum = 0
  for row in area[start_row_index:start_row_index+ M]:
    for el in row[start_col_index:start_col_index+M]:
      sum+=el
  return sum


def iterate_start_index(M, area):
  sum = 0
  N = len(area)
  for start_row_index, start_row in enumerate(area[:N-M+1]):
    for start_col_index in range(len(start_row[:N-M+1])):
      sum = max(sum, sum_fly(start_row_index,start_col_index, area, M))
  return sum

def main():
  input_count = int(input())
  for i in range(input_count):
    area = []
    N, M = map(int, input().split())
    for _ in range(N):
      row = list(map(int, input().split()))
      area.append(row)
    res = iterate_start_index(M, area)
    print(f'#{i+1} {res}')

if __name__ == "__main__":
  main()
