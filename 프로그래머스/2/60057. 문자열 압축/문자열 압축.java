class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuilder result = new StringBuilder();
            String base = s.substring(0, i);

            int count = 1;
            for (int j = i; j<= s.length(); j+=i) {
                String compare = s.substring(j, Math.min(j + i, s.length()));
                if (base.equals(compare)) {
                    count++;
                } else {
                    if (count > 1) {
                        result.append(count);
                    }
                    result.append(base);
                    base = compare;
                    count = 1;
                }
            }
            result.append(base);
            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}

// 1개 이상 단위로 문자열을 압축해 가장 짧은 길이를 구한다.(초기 길이와도 비교)
// 유닛을 구하고 몇번 연속되는지 해당
