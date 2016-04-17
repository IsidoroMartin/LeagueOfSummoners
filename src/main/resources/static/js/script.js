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
});