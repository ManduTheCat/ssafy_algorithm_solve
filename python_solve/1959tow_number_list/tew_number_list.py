def find_max(res):
  # N M 같을때 처리
  if res["is_same"]:
    mul_list= []
    for val, idx, in enumerate(res["base"]):
      mul_list.append(res["base"][idx] * val)
    return mul_list

  count = res["diff"]
  #print("base:", res["base"], "diff: ", res["diff"])
  move_list = res["move"]
  sum_list = []

  for i in range(count + 1):
    mul_res = []
    #print("i:",i)
    base_list = res["base"][i:]
    if(len(base_list) == 0 ):
      break
    for idx, val in enumerate(move_list):
      mul_res.append(base_list[idx] *val )
    sum_list.append(sum(mul_res))
  return max(sum_list)

# 단, 더 긴 쪽의 양끝을 벗어나서는 안 된다. 조건 에 따라
# 비교할때이 동할 리스트와 고정된 리스트를 고른다
# 만약 길이가 같다면 이동이 불가능하니 그자리그대로 곱한다.
def select_Base_list(N, M, A_list, B_list):
  if(N > M):
    return { "is_same":False, "base": A_list, "move": B_list, "diff":N-M }
  elif(M > N):
    return { "is_same":False, "base": B_list, "move": A_list, "diff": M-N }
  else:
    return { "is_same":True, "base": A_list, "move": B_list, "diff": 0 }

def main():
  input_count = int(input())
  for i  in range(input_count):
    N, M  = map(int, input().split(" "))
    A_list = list(map(int, input().split(" ")))
    B_list = list(map(int,input().split(" ")))
    print("#"+str(i+1),find_max(select_Base_list(N, M, A_list, B_list)))

if __name__ == "__main__":
  main()
