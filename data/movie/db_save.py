import pandas as pd
import mysql.connector
import argparse

movie_info_path = 'movie_info.xlsx'

# 엑셀 파일 읽기
df = pd.read_excel(movie_info_path)

# 필요한 열만 선택
selected_columns = ['영화 이미지', '영화 제목', '영화 초성']
df_selected = df[selected_columns]


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

# 데이터 프레임을 튜플 리스트로 변환
prod_data_total = [tuple(x) for x in df_selected.values]

# 데이터 삽입 쿼리

query = "INSERT INTO movie (movie_img, movie_name, movie_chosung) VALUES (%s, %s, %s)"

# 쿼리 실행
cursor.executemany(query, prod_data_total)

# 변경 사항 커밋
conn.commit()

# 연결 종료
conn.close()
