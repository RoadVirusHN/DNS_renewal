# DNS(Developer Network System)

![슬라이드1.png](assets/슬라이드1.png)

> 회원 가입, 해쉬태그 검색, 팔로우, 알림 기능 등을 지원하며, 게시글에 그림, 영상, 코드 등을 공유할 수 있는 웹 기반 SNS

## 시연(Showcase)

<a class="badge-anchor" href="http://ec2-54-180-98-152.ap-northeast-2.compute.amazonaws.com/"><img style="margin: 3px;" class="badge" title="발표 자료" src="https://img.shields.io/badge/시연용 웹사이트(Showcase website)-skyblue?style=for-the-badge&logo=googlechrome"></a> <img src="https://badgen.net/github/status/micromatch/micromatch/4.0.1" > 
- 회원 가입 시, 개인 정보 유출이 우려될 경우 다음 아이디를 사용하세요
	- E-mail: asdf@naver.com
	- PW: asdf123!
<a class="badge-anchor" href="https://docs.google.com/presentation/d/1ATPpyd-QxKbGKslnmjN56FQJNkG6qhc7VcyjozlnZ6I/edit?usp=sharing"><img style="margin: 3px;" class="badge" title="발표 자료" src="https://img.shields.io/badge/발표 자료(PPT)-orange?style=for-the-badge&logo=Microsoft PowerPoint"></a>
<a class="badge-anchor" href="http://ec2-54-180-98-152.ap-northeast-2.compute.amazonaws.com:8000/swagger-ui.html"><img style="margin: 3px;" class="badge" title="발표 자료" src="https://img.shields.io/badge/SWAGGER API DOC-darkgreen?style=for-the-badge&logo=swagger"></a><img src="https://badgen.net/github/status/micromatch/micromatch/4.0.1">
- 서버 비용 관계로 일부 기간 동안에는 작동하지 않습니다.

## 기술 스택 및 구조(Tech stacks & Architecture)

![슬라이드6.png](assets/슬라이드6.png)
### Tech Stacks
<img src="assets/javascript.svg">  <img src="assets/vue.svg">  <img src="assets/vuetify.svg">  <img src="assets/spring_boot.svg">  <img src="assets/mysql.svg">  <img src="assets/jira.svg">  <img src="assets/jenkins.svg">  <img src="assets/docker.svg">  <img src="assets/git.svg">  <img src="assets/aws.svg">
- Vue, Vuetify, AWS, Spring boot, Mysql, Docker, Jenkins, Javascript, Jira, Git
## ERD
![image-20221102131120575.png](assets/image-20221102131120575.png)

## 기능(features)

- **로그인-회원가입, 비밀번호 찾기, 변경**
![image-20221102155927579.png](assets/image-20221102155927579.png)
- **포스트 등록, 댓글 등록, 코드, 영상, 사진 첨부**
![image-20221102155824635.png](assets/image-20221102155824635.png)
- **팔로우, 포스트 스크랩, 알림 기능**
![image-20221102155755141.png](assets/image-20221102155755141.png)
- **해쉬 태그, 본문 검색**
![image-20221102155734607.png](assets/image-20221102155734607.png)


## 팀(Team)
### REMEMBER US
2020년 1월 13일~ 2020년 2월 20일 개발
- 이하연
- 김태동
- 최민혜
- 윤준석
- 남선웅

## 배포(Deployment)
### jenkins 배포
- github 프로젝트의 main branch push 시 빌드 자동 트리거
#### 수동 트리거 시
![image-20221102114301246.png](assets/image-20221102114301246.png)
- 해당 프로젝트의 스케듈 빌드 버튼 클릭
### `docker-compose` 배포
```bash
// 슬레이브 노드 서버에서
cd [user-name]/DNS_renewal
docker-compose up -d
```

## License
@Team Remember Us™ All right reserved.