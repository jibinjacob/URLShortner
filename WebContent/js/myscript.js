$(document).ready(function(){
	$("#submit").click(function(){
		var longurl = $("#longurl").val();
		if(longurl == ""){
			$("#message").html("<p style='color: red'>Please enter a url</p>");
		}else if (!isUrl(longurl)){
			$("#message").html("<p style='color: red'>This is not a valid url</p>");
		}else if (isAlreadyShortenedUrl(longurl)){
			$("#message").html("<p style='color: red'>This url is already shortened</p>");
		}else{
			$("#message").html("<p style='color: green'>Shortening...</p>");
			$.get('RequestHandler', {longUrl: longurl}, function(responseText){
				$("#message").html("<p style='color: green'>"+responseText+"</p>");
			});
			
		}
	});
	$("#longurl").bind({
		mousemove: function(event){
			console.log("Mouse Move");
			var toolTipX = event.pageX - 6;
			var toolTipY = event.pageY + 6;
			$('div.tooltip').css({top: toolTipY, left: toolTipX});
		},
		mouseenter: function(event){
			console.log("Mouse Enter");
			$('div.tooltip').remove();
			$('body').append('<div class="tooltip">Enter URL (e.g http://www.google.com)</div>');
		},
		mouseleave: function(event){
			console.log("Mouse Leave");
			$('div.tooltip').remove();
		},
		click: function(){
			$('div.tooltip').remove();
		}
	});
	
	function isUrl(s) {
		var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
		//var regexp = /^((https?|ftp):\/\/)?(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.){2}(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/;
		return regexp.test(s);
	}
	function isAlreadyShortenedUrl(s){
		var regexp = /(http):\/\/localhost:8080\/URLShortner*/;
		return regexp.test(s);
	}
});