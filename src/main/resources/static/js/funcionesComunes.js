/**
 * Esta función controla la bandera que se muestra en el header básandose en la
 * cookie enviada por el servidor
 */
$(function() {
	var locale = $.cookie("locale");
	$(".locale").each(function() {
		if ($(this).val() == locale) {
			$(this).attr("selected", "");
		}
	});
});

$("img.lazy").lazyload({
	effect : "fadeIn"
});

/**
 * Setea la variable active en el menú
 */
$(function setActive() {
	var aObj = $('.menu-item').get();
	for (i = 0; i < aObj.length; i++) {
		if (document.location.href.indexOf($(aObj[i]).first().attr("href")) >= 0) {
			$(aObj[i]).parent().addClass("active");
		}
	}
});

function determineClassChampionName(name) {
	if (name.search(" ") >= 0) {
		name = name.replace(" ", "-");
	}
	if (name.search("'") >= 0) {
		name = name.replace("'", "");
	}

	if (name.search(".") >= 0) {
		name = name.replace(".", "");
	}

	return name.toLowerCase();
}

function normalizeChampionName(name) {
	if (name.search(" ") >= 0) {
		name = name.replace(" ", "");
	}
	if (name.search("'") >= 0) {
		name = name.replace("'", "");
	}

	if (name.search(".") >= 0) {
		name = name.replace(".", "");
	}

	return name.toLowerCase();
}

function normalizeDescription(description) {
	if (description.search('"') >= 0) {
		description = description.replace(new RegExp('"', 'g'), "'");
	}
	return description;
}

/**
 * Obtiene un parametro de la queryString
 * @param sParam
 * @returns el valor
 */
function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

