//# sourceURL=view_resolver.js
var ViewResolver = function () {
	
	var refresh = function (path, data) {
		if (!path) {return;}
		if(!data) {data = '';}
		
		$.ajax({
			method: "post",
			url: contextPath + path,
			contentType: "application/json;charset=utf-8;",
			data: JSON.stringify(data),
			success: function (resData) {
				$('div#container').html('').html(resData);
			},
			error: function (xhr) {
				ErrorUtil.alert(xhr);
			}
		});
	}
	
	return {
		refresh: refresh
	}
}();