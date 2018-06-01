//# sourceURL=login_management.js
$(function() {
	$('title').html('登入作業');
});

function authentication () {
	var form = form2object('loginForm');
	var result = Validator.input('loginForm', ['ldap'], {
		accountId: '帳號',
		accountPasswd: '密碼'
	});
	
	if (!result) {return;}
	ViewResolver.refresh('/Function/LoginManagement/authentication', form);
}