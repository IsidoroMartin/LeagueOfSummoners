var $table = $('#table-matches');

$(document).ready(function () {
    var $loading = $('#ajax-loader').hide();
    $(document)
        .ajaxStart(function () {
            $table.hide();
            $loading.show();
        })
        .ajaxStop(function () {
            $loading.hide();
            $table.show();
        });
});

/**
 * Obtiene mediante una petición Ajax los stats de las partidas
 * @param update actualiza de la BD de riot o no.
 */
function getMatchList(update) {
    $.get("./api/summoner/matchlist?update=" + update, function (data) {
        var matchs = "";
        var noRankeds = $("#no-rankeds");
        if(data != null && data != ""){
	        data = data.reverse();
	        data.forEach(function (match) {
	                var colorTr = (match.winner) ? 'success' : 'danger';
	                var colorText = (match.winner) ? 'green' : 'red';
	                var result = (match.winner) ? "Ganada" : 'Perdida';
	                matchs += "<tr class='" + colorTr + "'>";
	                matchs += "<td class='" + colorText + "'>" + result + "</td>";
	                matchs += "<td>" + match.durationMins + "</td>";
	                matchs += "<td> <i class='icon champions-lol-28 " + determineClassChampionName(match.championName) + "'></i>&nbsp;" + match.championName + "</td>";
	                matchs += "<td>";
	                matchs += ((match.itemDto0 != null) ? "<img src='" + match.itemDto0.itemIcon + "' : class='itemIcon' title='" + match.itemDto0.itemNameEs + "' alt='" + match.itemDto0.itemNameEs + "' />" : "");
	                matchs += ((match.itemDto1 != null) ? "<img src='" + match.itemDto1.itemIcon + "' : class='itemIcon' title='" + match.itemDto1.itemNameEs + "' alt='" + match.itemDto1.itemNameEs + "' />" : "")
	                matchs += ((match.itemDto2 != null) ? "<img src='" + match.itemDto2.itemIcon + "' : class='itemIcon' title='" + match.itemDto2.itemNameEs + "' alt='" + match.itemDto2itemNameEs + "' />" : "")
	                matchs += ((match.itemDto3 != null) ? "<img src='" + match.itemDto3.itemIcon + "' : class='itemIcon' title='" + match.itemDto3.itemNameEs + "' alt='" + match.itemDto3.itemNameEs + "' />" : "")
	                matchs += ((match.itemDto4 != null) ? "<img src='" + match.itemDto4.itemIcon + "' : class='itemIcon' title='" + match.itemDto4.itemNameEs + "' alt='" + match.itemDto4.itemNameEs + "' />" : "")
	                matchs += ((match.itemDto5 != null) ? "<img src='" + match.itemDto5.itemIcon + "' : class='itemIcon' title='" + match.itemDto5.itemNameEs + "' alt='" + match.itemDto5.itemNameEs + "' />" : "")
	                matchs += ((match.itemDto6 != null) ? "<img src='" + match.itemDto6.itemIcon + "' : class='itemIcon' title='" + match.itemDto6.itemNameEs + "' alt='" + match.itemDto6.itemNameEs + "' />" : "")
	                matchs += "</td>";
	                matchs += "<td><img src='img/gold.png' alt='oro' title='oro'>" + (match.goldEarned / 1000).toFixed(2) + "k</td>";
	                matchs += "<td>" + match.stats + "</td>";
	                matchs += "</tr>";
	            }
	        );
        }

        if (data == null || data == "" || data.length == 0) {
            $table.css('visibility', 'hidden');
            noRankeds.show();
        } else {
            noRankeds.hide();
            $table.css('visibility', 'visible');
        }

        $('#table-matches tbody').html(matchs);
    });
};


function determineClassChampionName(name) {
    if (name.search(" ") >= 0) {
        name = name.replace(" ", "-");
    }
    if(name.search("'") >= 0){
    	name = name.replace("'","");
    }
    return name.toLowerCase();
}