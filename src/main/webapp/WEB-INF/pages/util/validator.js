//# sourceURL=validator.js
var Validator = function () {
	
	var input = function (formName, ignore, msgMap) {
		var inputs = $('form#' + formName).find('input');
		var keys = Object.keys(msgMap);
		var result = '';
		
		$.each(inputs, function () {
			var $this = $(this);
			var name = $this.attr('name');
			
			if (!name) {return;}
			if ($.inArray(name, ignore) >= 0) {return;}
			
			var val = $this.val();
			if (!val) {
				result = name;
				return false;
			}
		});

		$.each(keys, function () {
			if (result == this) {
				bootbox.alert({
					size: 'small',
					message: '請輸入' + msgMap[this]
				});
				
				return false;
			}
		});
		
		return result == '' ? true : false;
	}
	
	var equals = function (t1, t2, msgMap) {
		if (!t1 || !t2) {return false;}
		
		if (t1 == t2) {return true;}

		var keys = Object.keys(msgMap);
		bootbox.alert({
			size: 'small',
			message: msgMap[keys[0]] + '欄位與' + msgMap[keys[1]] + '欄位不符'
		});
		
		return false;
	}
	
	return {
		input: input,
		equals: equals
	}
	
}();