-- 코드를 입력하세요
SELECT distinct(cc.CAR_ID), cc.CAR_TYPE, FLOOR(cc.daily_fee * 30 * (100 - dp.discount_rate) / 100) as FEE
FROM car_rental_company_car cc
INNER JOIN car_rental_company_rental_history rh on cc.car_id = rh.car_id
INNER JOIN car_rental_company_discount_plan dp on dp.car_type = cc.car_type
WHERE cc.car_type IN ('SUV', '세단')
AND Cc.CAR_ID NOT IN (
                        SELECT CAR_ID
                        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                        WHERE START_DATE BETWEEN '2022-11-01' AND '2022-11-30'
                        OR END_DATE BETWEEN '2022-11-01' AND '2022-11-30'
                        OR (START_DATE <= '2022-11-01' AND END_DATE >= '2022-11-30'))
AND dp.duration_type = '30일 이상'
AND FLOOR(cc.daily_fee * 30 * (100 - dp.discount_rate) / 100) BETWEEN 500000 AND 1999999
ORDER BY fee desc, cc.car_type asc, cc.car_id desc

-- 1. 자동차 종류가 세단, SUV인 자동차 중
-- 2. 2022년 11월 1일부터 2022년 11월 30일까지 대여가 가능하고
-- 3. 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차에 대해
-- 4. 자동차 id, 자동차 종류, 대여 금액 리스트를 출력하는데
-- 5. 대여 금액을 기준으로 내림차, 자동차 종류 오름차, 자동차 아이디 내림차
