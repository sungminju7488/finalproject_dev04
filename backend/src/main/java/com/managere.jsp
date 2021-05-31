<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>업체정보수정</title>
    <link rel="stylesheet" href="/siktam/resources/css/headerfooterLayout.css">
    <link rel="stylesheet" href="/siktam/resources/css/registerCompany_2_5.css">
    <script src="/siktam/resources/js/jquery-3.4.1.min.js"></script>

    
</head>
<body>
    <!-- 헤더 시작 -->
    <%@ include file="common/header.jsp" %>

    <!-- 이 안에 작업하기 -->
    <input type="file" id="file" style="display:none;">
    <form action="" method="post" id="form">
        <div class="wrap" align="center">
            <h1 align="center">업체정보 수정</h1>
            <h4 align="center">수정정보 입력</h4><br>
        <table class="regist">
            <tr>
                <th align="left">매장명 </th>
                <td><input type="text" class="form-control" name="id" placeholder="매장명을 입력해주세요"></td>  
                <!-- <td><button id="confirm">중복 확인</button></td>       -->
            </tr>
            <tr>
                <th align="left">매장 사진 </th>
                <td>
                    <!-- <input type="text" class="form-control" name="pass1" placeholder=""> -->
                    <div id="uploadpic1">
                        
                    </div><!--평소에는 보이지 않다가 사진을 등록하면 script를 통해 div를 추가시킬 예정-->
                    <!-- <input type="file" style="width: 100px; height: 45px; background: gray; color:white;" value="매장 사진 추가하기"> -->
                    <button id="upload" type="button" onclick="oncolick=document.all.file.click()">매장 사진 수정하기</button>
                    <!--버튼 클릭시 파일을 삽입할 수 있도록 할 예정-->
                </td>   
            </tr>  
            <tr>
                <th align="left">매장주소 </th>
                <td><input type="text" class="form-control" name="pass2" placeholder=""></td>        
            </tr> 
            <tr>
                <th align="left">매장 전화번호 </th>
                <td><input type="address" class="form-control" name="address" placeholder="012-1234-5678"></td>       
            </tr>
             
            <tr>
                <th align="left">사업자 등록번호 </th><!--사업자 등록 조회 API삽입-->
                <td><input type="text" class="form-control" name="name" disabled>
                    <button>사업자 번호</button>
                </td>       
                
            </tr>
             
            <tr>
                <th align="left">영업시간 </th>
                <td>
                    <input type="time">부터
                    <input type="time">까지
                    <br>
                    <input type="text" style="width:200px" placeholder="휴무일을 입력해주세요">
                    
                </td>
                <!-- <td><input type="text" class="form-control" id="pid1" name="pid" maxlength="6"> -  -->
                <!-- <input type="text" class="form-control" id="pid2" name="pid" maxlength="1"> ****** -->
                <!-- </td>       -->
            </tr>

            <tr>
                <th align="left">메뉴 카테고리 </th>
                <td><div id="category">
                    <input type="checkbox" id="a"><label for="a">한식</label>
                    <input type="checkbox" id="b"><label for="b">중식</label>
                    <input type="checkbox" id="c"><label for="c">분식</label>
                    <input type="checkbox" id="d"><label for="d">양식</label>
                    <input type="checkbox" id="e"><label for="e">일식</label><br>
                    <input type="checkbox" id="f"><label for="f">카페/디저트</label>&nbsp;
                    <input type="checkbox" id="g"><label for="g">치킨</label>
                    <input type="checkbox" id="h"><label for="h">피자</label>
                    <input type="checkbox" id="i"><label for="i">족발/보쌈</label><br>
                    <input type="checkbox" id="j"><label for="j">도시락</label>
                    <input type="checkbox" id="k"><label for="k">찜/탕</label>
                    <input type="checkbox" id="l"><label for="l">프랜차이즈</label>
                </div></td>
                <!-- <td><input type="tel" class="form-control" name="tel"></td> -->
                <!-- <td><button id="confirm">휴대폰인증</button></td> -->
            </tr>
                <!-- <tr>
                    <th align="left">메뉴 등록 </th>
                    <td>
                        <h4>메뉴명<br></h4><input type="text" class="form-control" name="tel"><br>
                        <h4>사진등록<br></h4><input type="text" class="form-control" name="tel">등록한 사진의 경로를 표시
                        &nbsp;
                        <button type="button" id="upload" onclick="document.all.file.click();">메뉴사진등록</button><br>
                        <h4>가격<br></h4>
                        <input type="text" class="form-control" name="tel"><br>
                        
                        <h4>설명<br></h4>
                        <input type="text" class="form-control" name="tel"><br><br>
                        
                        <button type="button" id="menu"><h3>메뉴 등록</h3></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" id="remenu" onclick="noneForm();"><h3>메뉴 수정</h3></button>
                        
                        메뉴등록 버튼을 누르면 등록한 사진과 value를 바탕으로 하단에 메뉴 생성
                        <div id="uploadpic2" style="width: 100px; height: auto; background: white; color:black; margin-top:10px;">
                           이미지를 디코드 할 방법 모색이 필요해 보임 size를 설정해도 div 태그를 넘어감
                        </div>메뉴명의 vaule와 등록한 사진 경로로 이미지 등록할 예정
                    </td>
                </tr> -->


            <tr>
                <th align="left">테이블형태</th>
                <td><div id="tableForm">
                    <input type="checkbox" id="x"><label for="x">바 테이블</label>
                    <input type="checkbox" id="y"><label for="y">칸막이</label>
                    <input type="checkbox" id="z"><label for="z">2인석</label>
                    <input type="checkbox" id="k"><label for="k">셀프주문</label>
                </div></td>
            </tr>
            
 
        </table><br>
        
        <input type="submit" class="btn" value="수정" onclick="return check();">
        <input type="reset" class="btn" value="취소" onclick="location.href = 'mypageShop_5.jsp'">
        </div>
        
    </form>
        
    <div id="modal1">
            <div class="modal_content">
                <h2>업체 정보 수정</h2>
                <br><br>
                <table id="reservationTb1">
                    <thead>
                        <tr class="reservationTr">
                            <th>메뉴명</th>
                            <th>메뉴 사진</th> 
                            <th>메뉴 가격</th> 
                            <th>설명</th> 
                            <th colspan="2">정보 수정/삭제</th>
                         </tr>
                    </thead>
                    <tbody>
                        <tr class="reservationTr">
                            <td><input type="text" placeholder="아이스크림"></td>
                            <td>사진이다</td>
                            <td><input type="text" placeholder="2000">&nbsp;원</td>
                            <td><input type="text" placeholder="차갑다"></td>
                            <td><input type="button" value="수정" class="confirm" id="change" onclick="location.href='registerCompany_2_5.jsp'">
                                <input type="button" value="삭제" class="confirm" id="cancel"></td>
                        </tr>
                        <tr class="reservationTr">
                            <td><input type="text" placeholder="해물순듀뷰"></td>
                            <td>사진</td>
                            <td><input type="text" placeholder="7000">&nbsp;원</td>
                            <td><input type="text" placeholder="순듀뷰와 해물"></td>
                            <td><input type="button" value="수정" class="confirm" id="change" onclick="location.href='registerCompany_2_5.jsp'">
                                <input type="button" value="삭제" class="confirm" id="cancel"></td>
                        </tr>
                        <tr class="reservationTr">
                            <td><input type="text" placeholder="곱창"></td>
                            <td>사진이라고</td>
                            <td><input type="text" placeholder="22000">&nbsp;원</td>
                            <td><input type="text" placeholder="이탐희 최애"></td>
                            <td><input type="button" value="수정" class="confirm" id="change" onclick="location.href='registerCompany_2_5.jsp'">
                                <input type="button" value="삭제" class="confirm" id="cancel"></td>
                        </tr>
                        
                    </tbody>
                </table>
                <br><br>
                <button id="modal_close_btn3" onclick="test6();">창 닫기</button>
            </div>
            <div class="modal_layer"></div>
        </div>  
        
        
      
        <script>
            $("#upload").click(function(){
                $("#uploadpic1").css("display","block");
                console.log("aaaa")
            });
            $("#menu").click(function(){
                $("#uploadpic2").css("display","block").css("background","gray");
                
                console.log("aaaa")
            });
            function noneForm(){
            	document.getElementById('form').style.display = "none";
            	document.getElementById('modal1').style.display = "block";
            }
            
           	function test6(){
           		document.getElementById('form').style.display = "block";
            	document.getElementById('modal1').style.display = "none";
           	}
        </script>
    
    <!-- 푸터 시작 -->
    <%@ include file="common/footer.jsp" %>
</body>
</html>