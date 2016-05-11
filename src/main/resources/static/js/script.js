/**
 * Esta función controla la bandera que se muestra en el header básandose en la cookie enviada por el servidor
 */
$(function () {
    var locale = $.cookie("locale");
    $(".locale").each(function(){
        if($(this).val() == locale){
            $(this).attr("selected","");
        }
    });
    $('a[href^="#"]').on('click',function (e) {
        e.preventDefault();

        var target = this.hash;
        var $target = $(target);

        $('html, body').stop().animate({
            'scrollTop': $target.offset().top
        }, 900, 'swing', function () {
            window.location.hash = target;
        });
    });
});