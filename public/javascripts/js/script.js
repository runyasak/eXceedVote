$(function(){
	
	var note = $('#note');
	var ts = (new Date(2015, 4, 7, 9, 20, 0, 0)).getTime();
		
	$('#countdown').countdown({
		timestamp	: ts,
		callback	: function(days, hours, minutes, seconds){ 	}
	});
	
});
