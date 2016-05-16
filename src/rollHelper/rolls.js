"use strict"

var imgNum = 1;
var ACCEPTED_INPUT = [0,1,2,3,4,5,6];
window.onload = function() {

	imgNum = prompt("start number?");
	while(!imgNum) {
		imgNum = prompt("start number invalid");
	}

	loadImg();
	window.onkeyup = nextRoll;
	
	
}

function nextRoll(evt) {

	var key = num(evt.keyCode);
	
	if(key == 40) {
		var lis = document.querySelectorAll("li");
		if(lis.length > 0) {
			var removeLi = lis[lis.length-1];
			removeLi.parentNode.removeChild(removeLi);
		}
		
		imgNum--;
		
		console.log(removeLi);
		
	} else if(ACCEPTED_INPUT.indexOf(key) != -1) {
		
		var li = document.createElement("li");
		li.innerHTML = key;
		document.getElementById("inputs").appendChild(li);
		imgNum++;
		
	
	}
	
	loadImg();
	
}

function loadImg() {
	document.getElementById("number").innerHTML = "Displaying Image Number " + imgNum;
	var roll = document.getElementById("roll");
	roll.src = "incarnate-dice/" + imgNum + ".jpeg";
}

function num(keyCode) {
	
	return Math.abs(48 - parseInt(keyCode));
	
}