//# sourceURL=json_util.js
var JsonUtil = function () {

	var post = function (url, data, success) {
		$.ajax({
			method: "post",
			url: contextPath + url,
			contentType: "application/json;charset=utf-8;",
			data: JSON.stringify(data),
			success: function (resData) {
				success(resData);
			},
			error: function (xhr) {
				ErrorUtil.alert(xhr);
			}
		});
	}
	
	return {
		post: post
	}
	
}();