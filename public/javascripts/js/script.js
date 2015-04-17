$(function(){
	
	var note = $('#note');
	var ts = (new Date(2015, 3, 23)).getTime();
		
	$('#countdown').countdown({
		timestamp	: ts,
		callback	: function(days, hours, minutes, seconds){ 	}
	});
	
});
