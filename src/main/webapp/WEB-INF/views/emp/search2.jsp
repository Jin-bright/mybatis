<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습</title>
<style>
table#tbl-search { margin:0 auto; }
table#tbl-search th,table#tbl-search td {padding:5px 15px; font-weight: normal; }
table#tbl-search td {text-align:left;}

div#emp-container{text-align:center;}
table.tbl-emp{
	margin:0 auto;
	border:1px solid; 
	border-collapse:collapse;
}
table.tbl-emp th, table.tbl-emp td{
	border:1px solid;
	padding:5px;
}
div#search-container{
	padding:15px 0;
}
</style>
</head>
<body>
<div id="emp-container">
	<h2>사원정보 </h2>
	<br /><br />
	 <div id="search-container">
	  <form name="empSearchFrm">
	      <table id="tbl-search">
            <tr>
                <th colspan="2">
	                <select name="searchType" >
		                <option value="">검색타입</option>
		                <!-- required여부를 판단할 value="" 반드시 있어야함.-->
		                <option value="emp_id" <c:if test="${param.searchType eq 'emp_id'}"> selected </c:if>> 사번</option>
		                <option value="emp_name" ${param.searchType eq 'emp_name' ? 'selected' : '' }> 사원명</option>
		                <option value="email" ${param.searchType eq 'email' ? 'selected' : '' }>이메일</option>
		                <option value="phone" ${param.searchType eq 'phone' ? 'selected' : '' } >전화번호</option>
	            	</select>
	            	<input type="search" name="searchKeyword" value="${param.searchKeyword}" />
                </th>
            </tr>
            <!-- 성별 radio 추가 -->
            <tr>
                <th>성별</th>
                <td>
                    <input type="radio" name="gender" value='남' id="gender0" ${param.gender eq '남' ? 'checked' : '' } />
                    <label for="gender0">남</label>
                    <input type="radio" name="gender" value='여' id="gender1" ${param.gender eq '여' ? 'checked' : '' } />
                    <label for="gender1">여</label>
                </td>
            </tr>
            
            <!-- 급여기준 -->
		    <tr>
		        <th>급여</th>
		        <td>
		            <input type="number" name="salary" min="0" step="500000" value="${ param.salary }"/>
		            <input type="radio" name="salaryCompare" id="salaryCompareGE" value='ge' ${ param.salaryCompare eq 'ge' ? 'checked' : '' }/>
		            <label for="salaryCompareGE">이상</label>
		            <input type="radio" name="salaryCompare" id="salaryCompareLE" value='le' ${ param.salaryCompare eq 'le' ? 'checked' : '' }/>
		            <label for="salaryCompareLE">이하</label>
		        </td>
	   		 </tr>	
             <!-- @0210실습문제 : 입사일 조회 -->
	        <tr>
	            <th>입사일</th>
	            <td>
	                <input type="date" name="hire_date" value=""/>    
	                <input type="radio" name="hiredateCompare" id="hiredateCompare1" value='le'/>
	                <label for="hiredateCompare1">이전</label>
	                <input type="radio" name="hiredateCompare" id="hiredateCompare2" value='ge'/>
	                <label for="hiredateCompare2">이후</label>
	            </td>
	        </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" id="btn-search" value="검색"  />
                    <input type="reset"  value="리셋(이리셋은 내가원하는거안됨)" />
                    <input type="button" value="초기화(파라미터다뗀거)" onclick="location.href='search2.do';"/>
                </th>
            </tr>
        </table>
       </form>
	 	<br /><br /><br />
    </div>
	<br /><br />
	<table class="tbl-emp">
		<tr>
			<th></th><!-- 1부터 넘버링 처리  ok -->
			<th>사번</th>
			<th>사원명</th>
			<th>주민번호</th><!--뒷6자리는 ******처리-->
			<th>성별</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>부서코드</th>
			<th>직급코드</th>
			<th>급여레벨</th>
			<th>급여</th><!--원화기호, 세자리마다 콤마표시 ok-->
			<th>보너스율</th><!--percent로 표시  ok -->
			<th>매니져 사번</th>
			<th>입사일</th><!--날짜형식 yyyy/MM/dd-->
			<th>퇴사여부</th>
		</tr>

<%-- ◆◆◆ 내방식 -- 그냥  resultType="map" 썻음 
		<!-- 조회된 데이터가 있는 경우와 없는 경우를 분기처리 하세요 -->
		<c:if test="${not empty empList}">
		<c:forEach items="${empList}" var="emp" varStatus="vs">
		<tr>
			<td>${vs.index+1}</td>
			<td>${emp.EMP_ID}</td>
			<td>${emp.EMP_NAME}</td>
			<td>${fn:substring(emp.EMP_NO,0,7)}*******</td>
		 	<td>${fn:string(${emp.EMP_NO},8,13)}</td> <!-- 주민번호 어케 써??? el안에el못쓴대 ^^ 
			<td>${emp.EMAIL}</td>
			<td>${emp.PHONE}</td>
			<td>${emp.DEPT_CODE}</td>
			<td>${emp.JOB_CODE}</td>
			<td>${emp.SAL_LEVEL}</td>
			<td><fmt:formatNumber value="${emp.SALARY}" type="currency" /> </td>
			<td><fmt:formatNumber value="${emp.BONUS}" type="percent" /></td>
			<td>${emp.MANAGER_ID}</td>
			<td><fmt:formatDate value="${emp.HIRE_DATE}" pattern="yyyy/MM/dd"/></td>
			<td>${emp.QUIT_YN}</td>
		</tr>
		</c:forEach>
		</c:if>
		
		<c:if test="${empty empList}">
		<tr>
			<td colspan="14">조회된 데이터가 없습니다.</td>
		</tr>
		</c:if>
		--%>
		<!--  ******** 쌤방식 ******** -->
		<c:if test="${not empty empList}">
			<c:forEach items="${empList}" var="emp" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${emp.empId}</td>
					<td>${emp.empName}</td>
					<td>${fn:substring(emp.empNo, 0, 7)}******</td>
					<td>${emp.gender}</td>
					<td>${emp.email}</td>
					<td>${emp.phone}</td>
					<td>${emp.deptCode}</td>
					<td>${emp.jobCode}</td>
					<td>${emp.salLevel}</td>
					<td><fmt:formatNumber value="${emp.salary}" type="currency"/></td>
					<td><fmt:formatNumber value="${emp.bonus}" type="percent"/></td>
					<td>${emp.managerId}</td>
					<td><fmt:formatDate value="${emp.hireDate}" pattern="yyyy/MM/dd"/></td>
					<td>${emp.quitYn}</td>
				</tr>
			
			</c:forEach>
		</c:if>
		<c:if test="${empty empList}">
			<tr>
				<td colspan="15">조회된 데이터가 없습니다.</td>
			</tr>
		</c:if>
	</table>
</div>


</body>
</html>
