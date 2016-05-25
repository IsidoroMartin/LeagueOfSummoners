var champions = $('.champion');
var filtrosMobile = $("#filtros-mobile select").change(filterChampions);
var porcentaje_bottom_spells = 20;
var iScrollPos = 0;
$(function () {
    $("#modals").scroll(function () {
        var iCurScrollPos = $(this).scrollTop();
        var height = iCurScrollPos + $(window).height();
        pos = height - ( height * (porcentaje_bottom_spells / 100));
        console.log("-------------------------------------------");
        console.log("Window Height: "+$(window).height());
        console.log("Top Height: "+iCurScrollPos);
        console.log("Total Height: "+height);
        console.log("% : "+( height * (porcentaje_bottom_spells / 100)));
        console.log("Pos: "+pos);
        console.log("-------------------------------------------");

        $(".spells").css("top", height-(height*0.2));
        iScrollPos = iCurScrollPos;
    });
});

function filterChampions() {
    var inputValue = $("#campo-busqueda").val();
    campeonesFiltrados = filterChampionsByName(inputValue);
    var type = (this != undefined && this.nodeName == "SELECT") ? getSelectedSelectType()
        : getSelectedRadioType();
    campeonesFiltrados = filterChampionsByType(campeonesFiltrados, type);

    var html = buildHtmlChampions(campeonesFiltrados);
    if (html == "")
        html = "<div class='alert alert-warning fade in'>No existen campeones con estas caracteristicas</div>";
    $('#champions').hide().html(html).fadeIn(500);
}

/**
 * Filtra los campeones por nombre
 *
 * @param inputValue
 * @returns {Array}
 */
function filterChampionsByName(inputValue) {
    // Me creo una expresión regular con lo que ha introducido el usuario
    var regex = new RegExp(inputValue, "i");
    var champsFiltrados = [];
    champions.each(function (index, value) {
        var championImg = $(value).find("img")
        var champName = championImg.attr("title");
        if (champName.search(regex) != -1) {
            champsFiltrados.push($(value).find("img"));
        }
    });
    return champsFiltrados;
}

function filterChampionsByType(listaFiltrada, type) {
    var filtroType = [];
    if (type != "no-type") {
        $(listaFiltrada).each(function (index, value) {
            if ($(value).attr("data-type") == type) {
                filtroType.push(value);
            }
        });
    } else {
        filtroType = listaFiltrada;
    }
    return filtroType;
}

function buildHtmlChampions(listaChampsFiltrada) {
    var html = "";
    $(listaChampsFiltrada)
        .each(
            function (index, championImg) {
                var champName = championImg.attr("title");
                html += "<div class=\"champion\">";
                html += '<div class="col-xs-6 col-md-2 img-separation">';
                html += '<div class="panel panel-default" onclick="prepareModalWindow('
                    + determineIndexChampion(champName) + ')">';
                html += "<div class=\"panel-heading\">";
                html += "<span>" + champName + "<\/span>";
                html += "<\/div>";
                html += "<div class=\"panel-body\">";
                html += "<img class=\"img-responsive lazy\" name=\"champion\" data-original=\""
                    + championImg.attr("data-original")
                    + "\" data-type=\"Mago\""
                    + " src=\""
                    + championImg.attr("src")
                    + "\" title=\""
                    + champName + "\" style=\"display: block;\">";
                html += "<\/div>";
                html += "<\/div>";
                html += "<\/div>";
                html += "<\/div>";
            });
    return html;
}

function getSelectedRadioType() {
    var types = $("#filtros input");
    var type = "no-type";
    types.each(function (index, value) {
        if (value.checked) {
            type = value.value;
            return;
        }
    });
    return type;
}

function getSelectedSelectType() {
    return (filtrosMobile.val() != "Filtrar por tipo campeón") ? filtrosMobile
        .val() : "no-type";
}

/**
 * Este método controla que los radibox se puedan desactivar,además también
 * sirve de listener para filtrar campeones, ya que dependede que elcheckbox
 * cambie de estado
 */
var $unique = $('input.unique');
$unique.on('click', function (event) {
    var $this = $(this);
    var was_checked = $this.data('checked');
    $('input[name=' + $this.prop('name') + ']').data('checked', false);
    $this.data('checked', !was_checked).prop('checked', !was_checked);
    filterChampions("");
});

/**
 * Esta funcion sirve para desplegar los detalles del campeón cuando la ruta sea
 * requerida:
 */
$(function displayChampionDetails() {
    var hash = window.location.hash;
    hash = hash.substring(1);
    prepareModalWindow(determineIndexChampion(hash));
});

function prepareModalWindow(championIndex) {
    var championClicked = jsonChampions[championIndex];
    var modalLink = $('#modal-link');
    var normalizedName = normalizeChampionName(championClicked.championName);
    modalLink.attr("href", "#modals");
    var modalWindow = "";
    modalWindow += '<div id="wrapper-modal">';
    modalWindow += '<div class="close-modals">';
    modalWindow += '<div class="btn-close-modal">X</div>';
    modalWindow += '</div>'
    modalWindow += '<div class="modal-contenido">';
    modalWindow += '<div class="nombre-campeones">';
    modalWindow += '<h1>' + championClicked.championName + '</h1>';
    modalWindow += '<h3 class="champion-title" >'
        + championClicked.championTitleES + '</h3>';
    modalWindow += '</div>'
    modalWindow += '<div class="champion-lore col-xs-6"><h2>Historia de ' + championClicked.championName + "</h2>";
    modalWindow += '<p>' + championClicked.championLoreES + '</p>';
    modalWindow += '</div>'
    modalWindow += '<div class="spells">';
    modalWindow += '<h2>Hechizos</h2>';
    for (var i = 0; i < championClicked.spellsList.length; i++) {
        var title = normalizeDescription("<strong>"
            + championClicked.spellsList[i].spellNameEs + "</strong><br>"
            + championClicked.spellsList[i].spellDescriptionEs);
        modalWindow += '<img src="'
            + championClicked.spellsList[i].spellIcon
            + '" data-toggle="tooltip" data-html="true"  class="img-responsive lazy champion-spell" title=" '
            + title + '">';
    }
    modalWindow += '</div>';
    modalWindow += '</div>';
    modalWindow += '</div>';
    modalWindow += '</div>';
    $('#modals').html(modalWindow);
    var pos = $(window).height() - ($(window).height() * (porcentaje_bottom_spells / 100));
    $(".spells").css("top", pos + "px");
    animateModal(normalizedName, championClicked.splashArtUri);
    $('[data-toggle="tooltip"]').tooltip();
    $('#modal-link').click();

}

function animateModal(champName, splash) {
    var modal = $('#modals');
    $("#modal-link").animatedModal({
        modalTarget: 'modals',
        animatedIn: 'zoomIn',
        animatedOut: 'bounceOutDown',
        color: '#1c1a34',
        wrapper: "modals",

        beforeOpen: function () {
        },
        afterOpen: function () {

        },
        beforeClose: function () {
        },
        afterClose: function () {

        }
    });
    var divModal = $('#' + champName);
    modal.css("background-image", "url('" + splash + "')");
}

/**
 * Determina el índice del campeón en el array dinamicamente generado por
 * Thymleaf
 *
 * @param championName
 * @returns {Number}
 */
function determineIndexChampion(championName) {
    for (var i = 0; i < jsonChampions.length; i++) {
        if (normalizeChampionName(jsonChampions[i].championName) == normalizeChampionName(championName)) {
            return i;
        }
    }

}
