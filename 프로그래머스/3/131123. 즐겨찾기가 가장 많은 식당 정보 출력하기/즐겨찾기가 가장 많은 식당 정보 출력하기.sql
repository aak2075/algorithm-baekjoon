-- 코드를 입력하세요
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM REST_INFO as ri
WHERE FAVORITES = (SELECT max(favorites) from rest_info where food_type = ri.food_type)
GROUP BY FOOD_TYPE
ORDER BY FOOD_TYPE DESC
# select * from rest_info order by food_type