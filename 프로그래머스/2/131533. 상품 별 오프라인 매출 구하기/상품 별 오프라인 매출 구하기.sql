-- 코드를 입력하세요
SELECT p.product_code, p.price * sum(os.sales_amount) as sales
from product p
inner join offline_sale os on p.product_id = os.product_id
group by p.product_code
order by sales desc, p.product_code asc


# SELECT *
# from product p
# inner join offline_sale os on p.product_id = os.product_id