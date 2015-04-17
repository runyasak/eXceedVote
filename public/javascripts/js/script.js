$(function(){
	
	var note = $('#note'),
		days = 1;
		hours = 0;
		minutes = 3;
		seconds = 5;
		ts = (new Date()).getTime() + ((days*60*60*24)+(hours*60*60)+(minutes*60)+seconds)*1000;
		
	$('#countdown').countdown({
		timestamp	: ts,
		callback	: function(days, hours, minutes, seconds){ 	}
	});
	
});
