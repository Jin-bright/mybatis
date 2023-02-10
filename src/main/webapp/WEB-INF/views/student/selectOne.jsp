<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 실습</title>

<c:if test="${not empty msg}">
<script>
	alert('${msg}');
</script>
<c:remove var="msg" scope="session"/>
</c:if>


<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:10px auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
	line-height: 2em; /*input태그로 인해 cell이 높이가 길어져 border 두줄현상방지 */
}
table.tbl-student th{text-align:right;}
table.tbl-student td{text-align:left;}
table.tbl-student tr:last-of-type td:first-child{text-align:center;}
</style>
</head>
<body>
	<div id="student-container">
		<h2>학생정보 검색</h2>
		<p>등록된 학생수는 <span id="totalCount"></span>명입니다.</p>
		<form name="studentSearchFrm">
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		<br />
		<br />
		<br />
		<h2>map</h2>
		<form name="studentMapSearchFrm">
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		
		<h2>학생 정보 수정</h2>
        <form  name="studentUpdateFrm" > <!--  수정폼 !!! 여기다 post 쓰면 제출되어버림  -->
            <table class="tbl-student">
                <tr>
                    <th>학생번호</th>
                    <td>
                        <input type="number" name="no" required readonly/>
                    </td>
                </tr>
                <tr>
                    <th>학생이름</th>
                    <td>
                        <input type="text" name="name" required/>
                    </td>
                </tr>
                <tr>
                    <th>학생전화번호</th>
                    <td>
                        <input type="tel" name="tel"  required/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="수정" onclick="updateStudent();"/>
                        <input type="button" value="삭제" onclick="deleteStudent();" />
                    </td>
                </tr>
            </table>
        </form>
		
		
	</div>
	
	<script>
	// ★맵버전-1명조회★ 
	document.studentMapSearchFrm.addEventListener('submit', (e) => {
		e.preventDefault();
		const no = e.target.no; 
		
		if(!no.value)
			return;
		
		$.ajax({
			url : '${pageContext.request.contextPath}/student/studentMap.do',
			data : {no : no.value},
			success(data){
				console.log(data);
			}
		})
	});
	</script>

	<script>
	// ★삭제하기-레알삭제아님★ 
	const deleteStudent = () => {
		const frm = document.studentUpdateFrm;
		/* const data = { no : frm.no.value,
					   name : frm.name.value,
					   tel : frm.tel.value }      내방법 */
		const no = frm.no; // 쌤이한방법
		
		$.ajax({
			method : "post",
			data :{no : no.value}, /* 원래나는 data만썼음 */
			url : "${pageContext.request.contextPath}/student/deleteStudent.do",
			success(data){
				console.log(data);
				alert("삭제 성공")
			},
			error : console.log,
			complete : function(){
				frm.reset();
			}
		});
		
	};
	
	// ★수정하기★ 
	const updateStudent = () => {
//  비동기 - 폼은 현재페이지로 제출한다. 근데 비동기라서 제출하지않고 ajax방식 데이터로 넘긴다 ? 
		//만약제출해버린다면? 화면이 한번 바껴버림 
		const frm = document.studentUpdateFrm;
//		frm.submit();
		
		const data = { //이렇게 폼에서 데이터를 받아와서 ajax에게 넘길거야 
			no :  frm.no.value,
			name : frm.name.value,
			tel : frm.tel.value
		}
		
		// 이폼의 내용이 data로 넘길거임 
		$.ajax({
				method : "post",	
				data,
				dataType : "json",
				url : "${pageContext.request.contextPath}/student/upateStudent.do",
				success(data){
					console.log( data );
					alert("수정 성공");
			
				},
				error : console.log				
			});	

	};
	
	// ★ dto버전 -1명조회★ 
	document.studentSearchFrm.addEventListener('submit', (e) => {
		e.preventDefault(); //폼제출방지
		
		const no = e.target.no;
		if(!no.value){
			return;
		};
		$.ajax({
			url:"${pageContext.request.contextPath}/student/student.do",
			data : {no : no.value},
			success(data){
				console.log(data);
				
				if(data){
					const{no, name, tel} = data;
					const frm = document.studentUpdateFrm;
					frm.no.value = no;
					frm.name.value = name;
					frm.tel.value = tel;
					
					
				}else{
					alert("해당학생은 존재하지 않습니다.");
				}
				
				no.value = "";	
			},
			error : console.log
		});
	});
	
	
	//iify imedi
	( ()=> {
		$.ajax({
			url : "${pageContext.request.contextPath}/student/getTotalCount.do",
			success(data){
				console.log( data );
				
				const totalCount = document.querySelector("#totalCount");
				totalCount.innerHTML += data;
				
				//쌤방식 
//				const {totalCount} = data;
//				 document.querySelector("#totalCount").innerHTML=totalCount;
			},
			error : console.log
		});
		
	})();
	
	</script>
	
</body>
</html>
