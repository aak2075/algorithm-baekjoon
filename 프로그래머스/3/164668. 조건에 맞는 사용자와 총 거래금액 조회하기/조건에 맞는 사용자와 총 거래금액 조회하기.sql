-- 코드를 입력하세요
SELECT ugu.USER_ID, ugu.NICKNAME, SUM(ugb.price) as TOTAL_SALES
FROM used_goods_board ugb
INNER JOIN used_goods_user ugu ON ugb.writer_id = ugu.user_id
WHERE ugb.status = 'DONE'
GROUP BY ugu.user_id
HAVING total_sales >= 700000
ORDER BY total_sales asc

