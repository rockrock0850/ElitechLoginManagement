<%@ page pageEncoding="UTF-8" %>
<%@ include file='/WEB-INF/pages/jsp/layout/material/globle.jsp' %>

<script type="text/javascript" src="${contextPath}/pages/util/init_util.js"></script>
<script type="text/javascript" src="${contextPath}/pages/js/function/login_management.js"></script>
<div class="card card-login mx-auto mt-5">
	<div class="card-header">登入</div>
	<div class="card-body">
		<form id='loginForm'>
			<div class="form-group">
				<label for="">帳號</label>
				<input class="form-control" name='accountId' placeholder="請輸入帳號" />
			</div>
			<div class="form-group">
				<label for="">密碼</label>
				<span class='color_red font_tip'>(預設密碼與帳號相同)</span>
				<input class="form-control" type="password" name='accountPasswd' placeholder="請輸入密碼" />
			</div>
			<div class="form-group" style='display: none;'>
				<div class="form-check">
					<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember Password
					</label>
				</div>
			</div>
			<a class="btn btn-primary btn-block" href="javascript: authentication();">登入</a>
		</form>
		<div class="text-center" style='display: none;'>
			<a class="d-block small mt-3" href="register.html">Register an Account</a> <a class="d-block small"
				href="forgot-password.html">Forgot Password?</a>
		</div>
	</div>
</div>