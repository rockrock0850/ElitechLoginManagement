<%@ page pageEncoding="UTF-8" %>
<%@ include file='/WEB-INF/pages/jsp/layout/material/globle.jsp' %>

<div class="card card-login mx-auto mt-5">
	<div class="card-header">發生錯誤</div>
	<div class="card-body">
		<form id='loginForm'>
			<div class="form-group">
				<label for="">錯誤代碼:</label>
				<label>${exception.code}</label>
			</div>
			<div class="form-group">
				<label for="">錯誤訊息:</label>
				<label>${exception.msg}</label>
			</div>
			<div class="form-group">
				<label for="">錯誤發生位置:</label>
			</div>
			<div class="form-group">
				<label>${exception.target}</label>
			</div>
		</form>
	</div>
</div>