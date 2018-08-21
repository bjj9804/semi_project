$(function(){
    $("body").scroll(function(){
        var scroll = $("body").scrollTop();
        console.log(scroll);
        if (scroll >= 50) {
            //console.log('a');
            $("#header").addClass("change");
        } else {
            $("#header").removeClass("change");
        }        
    });
});