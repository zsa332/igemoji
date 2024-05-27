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

    url = f"https://search.naver.com/search.naver?&query=영화 {keyword} 정보"

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
    

    #div.cm_content_wrap > div.cm_content_area._cm_content_area_info > div > div.detail_info > a > img
    try:

        movie_item = soup.select("div.cm_content_wrap > div.cm_content_area._cm_content_area_synopsis > div > div.intro_box._content > p") 
        movie_img = soup.select_one("div.detail_info > a > img")
        movie_img_final = movie_img.get('src')

        movie_outline = movie_item[0].text
        image = movie_img_final
        # # movie_item이 비어 있는지 확인
        # if movie_item:
        #     movie_outline = movie_item[0].text
        #     print(movie_outline)
        #     print(movie_img_final)
        #     # 나머지 로직 수행
        # else:
        #     print("No movie outline found")\
        
        element = driver.find_element(By.CSS_SELECTOR, "div.cm_top_wrap._sticky._custom_select._header > div.sub_tap_area._scrolling_wrapper_common_tab._scroll_mover._button_scroller_variable > div > div > ul > li:nth-child(3) > a > span")
        element.click()

        # 페이지 로딩 대기
        time.sleep(2)

        # 이후 작업을 위해 새로운 페이지의 HTML을 가져옴
        new_html = driver.page_source
        new_soup = BeautifulSoup(new_html, 'html.parser')
        # print(soup2)
        #                               #main_pack > div.sc_new.cs_common_module.case_empasis.color_10._au_movie_content_wrap > div.cm_content_wrap._actor_wrap > div > div.sec_scroll_cast_member.img_vertical_87_98 > div:nth-child(2) > div > div > div > ul > li:nth-child(2) > a > div > div.title_box
        try:
            main_movie_actors = new_soup.select("div.cm_content_wrap._actor_wrap div.sec_scroll_cast_member.img_vertical_87_98 > div:nth-child(2) > div > div > div > ul")
            # print(movie_actors)
            actors_name = []
            actors_role = []

            for actor_info in main_movie_actors[0].select("li"):

                try:
                    main_actor_name = actor_info.select_one("div.title_box > strong.name").text
                    main_actor_role = actor_info.select_one("div.title_box > span.sub_text").text
                    actors_name.append(main_actor_name)
                    actors_role.append(main_actor_role)
                except:
                    pass
        except:
            pass
        
        try:
            sub_movie_actors = new_soup.select("div.cm_content_wrap._actor_wrap div.sec_scroll_cast_member.img_vertical_87_98 > div:nth-child(3) > div > div > div > ul")
            # print(movie_actors)

            for sub_actor_info in sub_movie_actors[0].select("li"):
                try:
                    sub_actor_name = sub_actor_info.select_one("div.title_box > strong.name").text
                    sub_actor_role = sub_actor_info.select_one("div.title_box > span.sub_text").text
                    actors_name.append(sub_actor_name)
                    actors_role.append(sub_actor_role)
                except:
                    pass
        except:
            pass


        if actors_name and actors_role:
            # 리스트의 요소들을 쉼표로 구분된 문자열로 변환
            actors_name_str = ', '.join(actors_name)
            actors_role_str = ', '.join(actors_role)

        if actors_name_str and actors_role_str:
            movie_set.append([keyword, movie_outline, image, actors_name_str, actors_role_str])
    except:
        pass

        

# print(movie_set)
data = pd.DataFrame(movie_set, columns = ['영화 제목', '영화 줄거리', '영화 이미지', '영화 배우', '영화 배역'])


data.to_excel('./movie_crawling.xlsx', index=False)
