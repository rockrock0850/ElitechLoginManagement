<%@ page pageEncoding="UTF-8" %>
<%@ include file='/WEB-INF/pages/jsp/layout/material/globle.jsp' %>

<script type="text/javascript" src="${contextPath}/pages/util/init_util.js"></script>
<script type="text/javascript" src="${contextPath}/pages/js/function/reset_password.js"></script>
<div class="card card-login mx-auto mt-5">
	<div class="card-header">密碼變更</div>
	<div class="card-body">
		<form id='resetPasswdForm'>
			<input class='hidden' name='accountId' value='${account.accountId}' />
			<div class="form-group">
				<label for="">舊密碼</label>
				<input class="form-control" name='oriPassword' placeholder="請輸入帳號">
			</div>
			<div class="form-group">
				<label for="">新密碼</label>
				<input class="form-control" type="password" name='newPassword' placeholder="請輸入密碼">
			</div>
			<div class="form-group">
				<label for="">確認新密碼</label>
				<input class="form-control" type="password" name='confirmPassword' placeholder="請確認新密碼">
			</div>
			<a class="btn btn-primary btn-block" href="javascript: reset();">確定</a>
		</form>
	</div>
</div>