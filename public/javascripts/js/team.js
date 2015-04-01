function star(id) {
	var str = id.split("-");
	var j;
	for(j = 1; j <= 5; j++){
		document.getElementById(str[0]+"-"+j).style.color = "white";
	}
	var i;
	for (i = 1; i <= parseInt(str[1]); i++) {
    	document.getElementById(str[0]+"-"+i).style.color = "yellow";
	}
}