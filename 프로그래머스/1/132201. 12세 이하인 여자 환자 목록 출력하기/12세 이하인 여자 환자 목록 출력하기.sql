-- 코드를 입력하세요
SELECT PT_NAME, PT_NO, GEND_CD, AGE, 
CASE
    WHEN (tlno IS NULL) THEN 'NONE'
    ELSE tlno
END as TLNO
from patient where age <= 12 and gend_cd = 'W' order by age desc, pt_name asc