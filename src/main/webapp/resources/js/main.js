$(document).ready(function(){
	var $jumbotron = $('.jumbotron');
	$jumbotron.slideUp(0);
	$jumbotron.slideDown('slow');
$('div div div div, li').hover(function(){
	$(this).fadeTo('fast',1);
}, function(){
	$(this).fadeTo('fast',0.75);
});
$('.pull-me').click(function(){
	$('.nav').slideToggle('slow');
});
});