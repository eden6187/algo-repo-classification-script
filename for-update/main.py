from posixpath import dirname
import yaml
import sys
import os


# config.ym을 파싱합니다.
def parse_config():
    try:
        with open('config.yml', 'r') as file:
            config = yaml.load(file, Loader=yaml.FullLoader)
    except OSError as e:
        print('config.yml이 현재 디렉토리에 없습니다. config.yml을 만들어주세요.', file=sys.stderr)

    return config;


# parsing한 cofig에서 문제 출처들을 추출합니다.
def find_problem_sources(config):
    problem_sources = config.get('problem-sources')
    if problem_sources is None:
        print('problem-sources가 config.yml에 정의되어 있지 습니다.', file=sys.stderr)
        exit(1)
    return problem_sources


# 문제 분류 별로 참조하기 위한 indx.md파일을 만듭니다.
def make_index(problem_categories):
    with open('../index.md', 'w') as f:
        # 2. 모든 문제 카테고리에 대하여 index.md에 링크를 걸어주어야 한다.
        index_form = '{}. [**{}**](./links/{}.md)'

        index = 1
        for k in problem_categories.keys():
            new_line = f.write(index_form.format(index, k, k) + "\n")
            index += 1


# parsing한 config에서 문제 종류를 추출합니다.
def find_problem_categories(config):
    problem_categories = config.get("problem-categories")
    if problem_categories is None:
        print('problem-categories가 config.yml에 정의되어 있지 않습니다.', file=sys.stderr)
        exit(1)
    return problem_categories


# 카테고리별로 문제를 모아 두기 위한 src/link/각category.md를 만듭니다.
def make_dir_link(problem_categories):
    if not os.path.exists('../links'):
        os.mkdir("../links")
    for k in problem_categories.keys():
        category_md_form = "../links/{}.md"
        f = open(category_md_form.format(k), 'w')


# 각 문제에 달려 있는 tag.yml을 파싱합니다.
def parse_tag_dot_yml(file_path):
    try:
        with open(file_path, 'r') as file:
            tag = yaml.load(file, Loader=yaml.FullLoader)
    except OSError as e:
        print(file_path[3:] + '에 문제 tag.yml이 없습니다. 없습니다. tag.yml을 만들어주세요.', file=sys.stderr)
        exit(1)
    return tag;


# 하나의 문제 소스에 대하여 태그들의 정보를 담은 리스트를 반환합니다.
def get_tags_in(problem_source):
    problem_source_dir_path = '../src/'
    problem_source_dir_path += problem_source

    try:
        dir_names = os.listdir(problem_source_dir_path)
    except OSError as e:
        print('src/' + problem_source + '디렉토리가 없습니다.', file=sys.stderr)
        exit(1)

    tags = []

    for dirname in dir_names:
        tag_path = problem_source_dir_path + '/' + dirname + '/tag.yml'
        tag = parse_tag_dot_yml(tag_path)
        tag['dirname'] = problem_source_dir_path + '/' + dirname
        tags.append(tag)

    return tags


def get_each_problem_info_from(problem_sources):
    problem_list = []
    for source in problem_sources:
        tags = get_tags_in(source)
        problem_list.extend(tags)
    return problem_list


def classify_problem(problem_categories, problem_infos):
    classified_problem = {}

    for problem_category in problem_categories:
        classified_problem[problem_category] = []

    for problem_info in problem_infos:
        categories = problem_info['category']
        for category in categories:
            classified_problem[category].append(problem_info['dirname'])

    return classified_problem


def write_down_classified_problems_into_md(classified_problems):
    index_form = '{}. [**{}**]({})\n'

    for category, infos in classified_problems.items():
        file_path = '../links/' + category + '.md'
        with open(file_path, 'w') as md:
            num = 1
            for info in infos:
                problem_name = info.split('/')[-1]
                problem_path = info
                formatted_str = index_form.format(num, problem_name, problem_path)
                num += 1
                md.write(formatted_str)


    return


if __name__ == "__main__":
    config = parse_config()
    problem_categories = find_problem_categories(config);
    make_index(problem_categories)
    make_dir_link(problem_categories)
    problem_sources = find_problem_sources(config)
    problem_info = get_each_problem_info_from(problem_sources)
    classified_problems = classify_problem(problem_categories, problem_info)
    write_down_classified_problems_into_md(classified_problems)
