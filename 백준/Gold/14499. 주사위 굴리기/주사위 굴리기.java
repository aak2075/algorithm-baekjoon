import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> input = readIntLine(br);

        int n = input.get(0);
        int m = input.get(1);
        int y = input.get(2);
        int x = input.get(3);
        int k = input.get(4);

        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> mapLine = readIntLine(br);

            map.add(mapLine);
        }

        List<Integer> commands = readIntLine(br);

        List<Face> dice = IntStream.rangeClosed(1, 6)
                .mapToObj(Face::new)
                .collect(Collectors.toList());

        for (Integer command : commands) {
            boolean isOut = false;
            switch (command) {
                case 1:
                    if (x + 1 > m - 1) {
                        isOut = true;
                        break;
                    }
                    x += 1;
                    break;
                case 2:
                    if (x - 1 < 0) {
                        isOut = true;
                        break;
                    }
                    x -= 1;
                    break;
                case 3:
                    if (y - 1 < 0) {
                        isOut = true;
                        break;
                    }
                    y -= 1;
                    break;
                case 4:
                    if (y + 1 > n - 1) {
                        isOut = true;
                        break;
                    }
                    y += 1;
                    break;
            }
            if (isOut) {
                continue;
            }
            int mapNumber = map.get(y).get(x);
            for (Face face : dice) {
                face.move(command);
                if (face.position == 1) {
                    if (mapNumber == 0) {
                        map.get(y).set(x, face.number);
                    } else {
                        face.number = mapNumber;
                        map.get(y).set(x, 0);
                    }
                }
                if (face.position == 3) {
                    System.out.println(face.number);
                }
            }
        }
    }

    private static List<Integer> readIntLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static class Face {
        int position;
        int number;

        public Face(int position) {
            this.position = position;
            this.number = 0;
        }

        public void move(int direction) {
            switch (direction) {
                case 1:
                    switch (position) {
                        case 1:
                            position = 5;
                            break;
                        case 5:
                            position = 3;
                            break;
                        case 3:
                            position = 6;
                            break;
                        case 6:
                            position = 1;
                            break;
                    }
                    break;
                case 2:
                    switch (position) {
                        case 5:
                            position = 1;
                            break;
                        case 3:
                            position = 5;
                            break;
                        case 6:
                            position = 3;
                            break;
                        case 1:
                            position = 6;
                            break;
                    }
                    break;
                case 3:
                    switch (position) {
                        case 4:
                            position = 1;
                            break;
                        case 1:
                            position = 2;
                            break;
                        case 2:
                            position = 3;
                            break;
                        case 3:
                            position = 4;
                            break;
                    }
                    break;
                case 4:
                    switch (position) {
                        case 1:
                            position = 4;
                            break;
                        case 2:
                            position = 1;
                            break;
                        case 3:
                            position = 2;
                            break;
                        case 4:
                            position = 3;
                            break;
                    }
                    break;
            }
        }
    }
}

// 문제 정리
// 1. 맨 처음 주사위는 0
// 2. 지도의 칸이 0이면 주사위의 숫자를 지도 칸에 복사, 지도의 칸이 0이 아니면 지도의 숫자가 주사위 바닥면으로 복사하고 지도 칸은 0으로 변경.
// 3. 이동했을 때 마다 주사위 윗면의 값
// 바깥으로 이동시 무시

// 접근
// 각 면 객체를 만들어서 이동마다 면의 위치와 칸의 숫자를 바꿔줌
// position: 1:아래 2:앞 3:위 4:뒤 5:왼쪽 6:오른쪽
// 동쪽이동(1): 1 -> 5, 5 -> 3, 3 -> 6, 6 -> 1
// 서쪽이동(2): 5 -> 1, 3 -> 5, 6 -> 3, 1 -> 6
// 북쪽이동(3): 4 -> 1, 1 -> 2, 2 -> 3, 3 -> 4
// 남쪽이동(4): 1 -> 4, 2 -> 1, 3 -> 2, 4 -> 3
// 이동했을 때 position == 1이고 지도의 숫자가 0이면 주사위의 숫자를 반환
// 이동했을 때 position == 1이고 지도의 숫자가 0이 아니면 -1을 반환
// 이동했을 때 position == 3이면 주사위의 숫자를 출력
// 메서드의 결과가 -1이 아니면 반환 숫자를 지도에 저장
