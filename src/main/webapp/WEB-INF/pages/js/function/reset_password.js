//# sourceURL=reset_password.js
$(function() {
	$('title').html('密碼變更');
});

function reset () {
	var form = form2object('resetPasswdForm');
	var newPassword = form.newPassword;
	var confirmPassword = form.confirmPassword;
	
	var result = Validator.input('resetPasswdForm', [], {
		oriPassword: '舊密碼',
		newPassword: '新密碼',
		confirmPassword: '確認新密碼'
	});
	result = Validator.equals(newPassword, confirmPassword, {
		newPassword: '新密碼',
		confirmPassword: '確認新密碼'
	});
	
	if (!result) {return;}

	JsonUtil.post('/Function/LoginManagement/reset', form, function(resData) {
		bootbox.alert({
			size: 'small',
			message: resData.msg,
			callback: function () {
				ViewResolver.refresh('/Function/LoginManagement');
			}
		});
	})
}