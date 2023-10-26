-- 코드를 입력하세요
select a.author_id, a.author_name, b.category, SUM(bs.sales * b.price) as total_sales
from book b
inner join author a on a.author_id = b.author_id
inner join book_sales bs on b.book_id = bs.book_id
where year(bs.sales_date) = '2022' and month(bs.sales_date) = '01'
group by a.author_id, b.category
order by b.author_id asc, b.category desc
-- 22년 1월을 기준으로
-- 저자별, 카테고리별 매출액

# select a.author_id, a.author_name, b.category, bs.sales * b.price as total_sales
# from book b
# inner join author a on a.author_id = b.author_id
# inner join book_sales bs on b.book_id = bs.book_id