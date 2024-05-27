from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
import pandas as pd
import numpy as np
from tqdm import tqdm
import time
import re

excel = './영화테이블.xlsx'

df = pd.read_excel(excel)
# print(df)
movie_set = []

for i in range(len(df)):
    keyword = df['영화 이름'][i]
    print(keyword)
    try:

        url = f"https://search.naver.com/search.naver?&query=영화 {keyword} 명대사"

        # 웹 드라이버 설정 및 URL 접속
        service = Service(executable_path=ChromeDriverManager().install())
        driver = webdriver.Chrome(service = service)
        driver.get(url)
        driver.maximize_window()    # 화면 최대화 

        # 페이지가 완전히 로드될 때까지 잠시 대기
        time.sleep(2)

        # 현재 페이지의 HTML을 가져옴
        html = driver.page_source
        soup = BeautifulSoup(html, 'html.parser')

        # 명대사 10개씩 가져오기
        movie_quote = soup.select("div.cm_content_wrap > div > div > div > div.area_list._area_list > ul")

        cnt = 0
        for quote_info in movie_quote[0].select("li"):
            quote = []
            thumb = []
            cnt += 1
            if cnt == 10:
                break
            else:
                movie_quote_final = quote_info.select_one("div.area_float_box > div.area_text_box > p.area_text_main").text
                movie_quote_thumb = int(quote_info.select_one("div.area_sub_info_box > div > button > span").text)
                quote.append(movie_quote_final)
                thumb.append(movie_quote_thumb)

            if quote:
                quote_str = ', '.join(quote)

        
            if quote_str:
                movie_set.append([keyword, quote_str, thumb])
    except:
        pass

# print(movie_set)
data = pd.DataFrame(movie_set, columns = ['영화 제목', '영화 명대사', '명대사 추천 수'])
data['명대사 추천 수'] = data['명대사 추천 수'].apply(lambda x: x[0])  # 대괄호 제거

data.to_excel('./movie_quote_crawling.xlsx', index=False)
