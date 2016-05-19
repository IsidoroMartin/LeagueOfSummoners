var champsToken = [];
for (var i = 0;i<champion_list.length;i++){
    champsToken[i] = {name_champion:champion_list[i]};
    console.log()
}
var champions = new Bloodhound({
    datumTokenizer: function (d) {
        return Bloodhound.tokenizers.whitespace(d.name_champion);
    },
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    local:champsToken
});
champions.initialize();
$('.input_champions').typeahead(null, {
    displayKey: 'name_champion',
    source: champions.ttAdapter()

});
