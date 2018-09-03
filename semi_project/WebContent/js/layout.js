$(function(){
    $("body").scroll(function(){
        var scroll = $("body").scrollTop();;
        if (scroll >= 50) {
            $("#header").addClass("change");
        } else {
            $("#header").removeClass("change");
        }        
    });
    
    $(".gnb .sub").mouseover(function(){
    	$(this).children(".sub_menu").stop().show();
    	$(".sub_bg").stop().slideDown();
    });
    $(".gnb .sub").mouseout(function(){
    	$(this).children(".sub_menu").stop().hide();
    	$(".sub_bg").stop().slideUp();
    });
});