//# sourceURL=init_util.js
var InitUtil = function () {
	
	var datePicker = function (name, option) {
		if (!name) {
			var dialog = bootbox.dialog({
			    title: '初始化失敗!',
			    message: 'name: ' + name
			});
			
			return;
		}
		
		if (!option) {
			option = {
				format: 'yyyy-mm-dd', // 日期格式
				//startDate: formData.begin, 可選起始日期+時間
				//endDate: formData.end, 可選結束日期+時間
				//pickerPosition: "top-left", 設定視窗彈出位置
				autoclose: true, // 選完日期自動關閉日曆
				todayBtn: true  // 顯示[今天]按鈕
			};
		}
		
		$(name).datepicker(option);
	}
	
	var yearSelector = function (name) {
		if (!name) {
			var dialog = bootbox.dialog({
			    title: '初始化失敗!',
			    message: 'name: ' + name
			});

			return;
		}
		
	    for (i = new Date().getFullYear(); i > 1900; i--){
	        $(name).append($('<option />').val(i).html(i));
	    }
	}
	
	var monthSelector = function (name) {
		if (!name) {
			var dialog = bootbox.dialog({
			    title: '初始化失敗!',
			    message: 'name: ' + name
			});

			return;
		}
		
	    for (i = 1; i < 13; i++){
	        $(name).append($('<option />').val(i).html(i));
	    }
	}
	
	var daySelector = function (yearName, monthName, dayName) {
		if (!yearName || !monthName || !dayName) {
			var dialog = bootbox.dialog({
			    title: '初始化失敗!',
			    message: 'yearName: ' + yearName + ', monthName: ' + monthName + ', dayName: ' + dayName
			});

			return;
		}
		
	    updateNumberOfDays(yearName, monthName, dayName); 

	    $(yearName + ', ' + monthName).change(function(){
	        updateNumberOfDays(yearName, monthName, dayName); 
	    });
	}

	/* Private
	 ============================================== */
	var updateNumberOfDays = function (yearName, monthName, dayName) {
	    $(dayName).html('');
	    month = $(monthName).val();
	    year = $(yearName).val();
	    days = daysInMonth(month, year);
	
	    for(i = 1; i < days+1 ; i++){
	        $(dayName).append($('<option />').val(i).html(i));
	    }
	}
	
	var daysInMonth = function (month, year) {
	    return new Date(year, month, 0).getDate();
	}
	
	return {
		datePicker: datePicker,
		yearSelector: yearSelector,
		monthSelector: monthSelector,
		daySelector: daySelector
	}
	
}();