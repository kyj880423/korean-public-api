# korean-public-api
korean-public-api

공공데이터 포탈의 서비스 조회 처리 및 결과를 활용하기 위한 중간 코드들을 구현합니다.



## 디렉토리 설명
자바 코드로 구현된 클래스 이름에 따라 API KEY와 URL주소가 설정으로 매칭되게 작성했습니다. 

1. korean-public-api/korean-public-api/services/
  다음 하위 디렉토리는 서비스에 관련된 API URL을 관리합니다. Velocity 문법으로 구현되어 있습니다.
  ex) ArvlInfoInqireServiceImpl.vm 파일안의 url은 ArvlInfoInqireServiceImpl.java 파일에 구현된 URL로 활용됩니다.
2. korean-public-api/korean-public-api-key.properties
  서비스 키들을 등록합니다.
  ex) 설정파일안의 ArvlInfoInqireServiceImpl.key 키 속성은 ArvlInfoInqireServiceImpl.java 파일에 구현된 서비스키 파라미터로 활용됩니다.
  
  
##
