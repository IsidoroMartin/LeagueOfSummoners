$(document).ready(function () {
    var $loading = $('#ajax-loader').hide();
    var $table = $('#table-matches').hide();
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
        data.forEach(function (stats) {
                var colorTr = (stats.winner) ? 'success' : 'danger';
                var colorText = (stats.winner) ? 'green' : 'red';
                var result = (stats.winner) ? "Ganada" : 'Perdida';
                matchs += "<tr class='" + colorTr + "'>";
                matchs += "<td class='" + colorText + "'>" + result + "</td>";
                matchs += "<td>" + stats.durationMins + "</td>";
                matchs += "<td> <i class='icon champions-lol-28 " + determineChampionName(stats.championName) + "'></i>&nbsp;" + stats.championName + "</td>";
                matchs += "<td>" + stats.stats + "</td>";
                matchs += "</tr>";
            }
        );
        $('#table-matches tbody').html(matchs);
    });
};


function determineChampionName(name){
    console.log(name);
    if(name.search(" ") >= 0){
        name = name.replace(" ", "-");
        console.log("nuevo nombre: " + name);
    }
    return name.toLowerCase();
}