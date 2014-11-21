<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "com.mycompany.calendar.domain.Event" %>

<html>
<head>
<title>Home</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<style>
th {
	bod	
}

h1 {
	padding:10px;
}
.headline {
	color:red;
	
}

hr {
	color:black;
}
</style>
</head>
<body>
	<h1>
	웹 컴퓨팅 및 서비스 4번째 과제 <br/>
	2008160006 강은석</h1>
	
	<p></p>
	<p></p>
	<hr size="5" color="blue">


	<div>
		<ul>
			<c:forEach var="user" items="${calendarUsers}">

			</c:forEach>
		</ul>
	</div>
	<h1 class="headline">calendarUsers 내용 출력</h1>
	<div>
		<table class="table table-bordered table-hover">
			<caption>calendarUsers</caption>
			<thead>
			<tr class="danger">
				<th>ID</th>
				<th>email</th>
				<th>password</th>
				<th>name</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="calendarUser" items="${calendarUsers}">
				<tr class="info">
					<td>${calendarUser.id}</td>
					<td>${calendarUser.email}</td>
					<td>${calendarUser.password}</td>
					<td>${calendarUser.name}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<br>
	<c:set var="eventstest">${events}</c:set>
	<h1 class="headline">events 내용 출력</h1>
	<div>
		<table class="table table-bordered table-hover">
			<caption>events</caption>
			<thead>
			<tr class="danger">
				<th>id</th>
				<th>when</th>
				<th>summary</th>
				<th>description</th>
				<th>owner</th>
				<th>numLikes</th>
				<th>eventLevel</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="event" items="${events}">
				<%-- <fmt:formatDate value="${event.when}" type="date" dateStyle="short" /> --%>				
				<tr class="info">
					<td>${event.id}</td>
					<td><fmt:formatDate value="${event.when.time}" type="date" dateStyle="full" /></td>
					<td>${event.summary}</td>
					<td>${event.description}</td>
					<td>${event.owner}</td>
					<td>${event.numLikes}</td>
					<td>${event.eventLevel}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<h1 class="headline">eventAttentees 출력</h1>
	<div>
		<table class="table table-bordered table-hover">
		<caption>eventAttentees</caption>
		<thead>
			<tr class="danger">
				<th>id</th>
				<th>event</th>
				<th>attendee</th>
			</tr>
		</thead>

			<c:forEach var="eventAttentee" items="${eventAttendees}">
				<tr class="info">
					<td>${eventAttentee.id}</td>
					<td>${eventAttentee.event}</td>
					<td>${eventAttentee.attendee}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 
	private Integer id;
    private Event event;
    private CalendarUser attendee;
	 -->
	
	<!--  
    private Integer id;
    private Calendar when;
    private String summary;
    private String description;
    private CalendarUser owner;
    private int numLikes;                     /* Updated by Assignment 3 */
    private EventLevel eventLevel;            /* Updated by Assignment 3 */
-->
	<!--  
 	private Integer id;
    private String email;
    private String password;
    private String name;
-->
</body>
</html>
