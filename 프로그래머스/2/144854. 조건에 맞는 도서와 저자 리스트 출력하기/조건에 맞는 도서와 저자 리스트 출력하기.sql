-- 코드를 입력하세요
SELECT b.book_id, a.author_name, DATE_FORMAT(b.PUBLISHED_DATE, '%Y-%m-%d') as published_date
FROM BOOK b
INNER JOIN AUTHOR a ON b.AUTHOR_ID = a.AUTHOR_ID
WHERE b.CATEGORY = '경제'
ORDER BY b.PUBLISHED_DATE asc