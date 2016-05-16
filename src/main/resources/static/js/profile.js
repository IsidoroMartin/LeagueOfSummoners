$(document).ready(function () {
    var $loading = $('#ajax-loader').hide();
    var $table = $('#table-matches');
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

function getMatchList(update) {
    $.get("./api/summoner/matchlist?update=" + update, function (data) {
        var matchs = "";
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
                matchs +=  ((match.itemDto0 != null) ? "<img src='" + match.itemDto0.itemIcon + "' :width='28px' height='28px' title='" + match.itemDto0.itemNameEs +"' alt='" + match.itemDto0.itemNameEs +"' />" : "");
                matchs +=  ((match.itemDto1 != null) ? "<img src='" + match.itemDto1.itemIcon + "' :width='28px' height='28px' title='" + match.itemDto1.itemNameEs +"' alt='" + match.itemDto1.itemNameEs +"' />" : "")
                matchs +=  ((match.itemDto2 != null) ? "<img src='" + match.itemDto2.itemIcon + "' :width='28px' height='28px' title='" + match.itemDto2.itemNameEs +"' alt='" + match.itemDto2itemNameEs +"' />" : "")
                matchs +=  ((match.itemDto3 != null) ? "<img src='" + match.itemDto3.itemIcon + "' :width='28px' height='28px' title='" + match.itemDto3.itemNameEs +"' alt='" + match.itemDto3.itemNameEs +"' />" : "")
                matchs +=  ((match.itemDto4 != null) ? "<img src='" + match.itemDto4.itemIcon + "' :width='28px' height='28px' title='" + match.itemDto4.itemNameEs +"' alt='" + match.itemDto4.itemNameEs +"' />" : "")
                matchs +=  ((match.itemDto5 != null) ? "<img src='" + match.itemDto5.itemIcon + "' :width='28px' height='28px' title='" + match.itemDto5.itemNameEs +"' alt='" + match.itemDto5.itemNameEs +"' />" : "")
                matchs +=  ((match.itemDto6 != null) ? "<img src='" + match.itemDto6.itemIcon + "' :width='28px' height='28px' title='" + match.itemDto6.itemNameEs +"' alt='" + match.itemDto6.itemNameEs +"' />" : "")
                matchs += "</td>";
                matchs += "<td><img src='img/gold.png' alt='oro' title='oro'>" + (match.goldEarned/1000).toFixed(2) +"k</td>";
                matchs += "<td>" + match.stats + "</td>";
                matchs += "</tr>";
            }
        );
        $('#table-matches tbody').html(matchs);
    });
};


function determineClassChampionName(name) {
    if (name.search(" ") >= 0) {
        name = name.replace(" ", "-");
    }
    return name.toLowerCase();
}