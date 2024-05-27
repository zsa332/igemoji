import mysql.connector
import pandas as pd
import numpy as np
import argparse



# 명령줄 인수 파서 생성 및 인수 정의
parser = argparse.ArgumentParser(description="Connect to MySQL database and fetch data.")
parser.add_argument("--host", required=True, help="MySQL server address")
parser.add_argument("--user", required=True, help="MySQL user name")
parser.add_argument("--password", required=True, help="MySQL user password")
parser.add_argument("--database", required=True, help="Database name")
parser.add_argument("--port", type=int, default=3306, help="Database port")

# 인수 파싱
args = parser.parse_args()

# MySQL 서버에 연결
conn = mysql.connector.connect(
    host=args.host,
    user=args.user,
    password=args.password,
    database=args.database,
    port=args.port
)

# 커서 생성
cursor = conn.cursor()

# movie_quote.xlsx 파일에서 데이터 읽기
df_movie_quote = pd.read_excel("movie_quote.xlsx")

# 각 영화 제목과 명대사를 데이터베이스에 저장
for index, row in df_movie_quote.iterrows():
    movie_name = row['영화 제목']
    quote = row['영화 명대사']

    # 해당 영화 제목의 movie_id를 조회
    query = "SELECT movie_id FROM movie WHERE movie_name = %s"
    cursor.execute(query, (movie_name,))
    result = cursor.fetchone()

    if result:
        movie_id = result[0]
        # movie_id와 명대사를 famous_line 테이블에 저장
        insert_query = "INSERT INTO famous_line (movie_id, line) VALUES (%s, %s)"
        cursor.execute(insert_query, (movie_id, quote))
        conn.commit()



# 데이터베이스 연결 종료
cursor.close()
conn.close()