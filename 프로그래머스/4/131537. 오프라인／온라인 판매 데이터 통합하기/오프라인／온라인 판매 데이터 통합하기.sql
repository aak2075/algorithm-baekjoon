-- 코드를 입력하세요
SELECT date_format(sales_date, '%Y-%m-%d') SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT from online_sale where sales_date >= '2022-03-01' and sales_date < '2022-04-01'
union all
select date_format(sales_date, '%Y-%m-%d') SALES_DATE, PRODUCT_ID, NULL AS USER_ID, SALES_AMOUNT from offline_sale where sales_date >= '2022-03-01' and sales_date < '2022-04-01' order by sales_date asc, product_id asc, user_id asc