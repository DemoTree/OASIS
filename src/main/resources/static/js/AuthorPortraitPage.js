function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function () {
    console.log("author");
    var content = JSON.parse(sessionStorage.getItem('authorInfo'));
    console.log(content);
    if (content==null) {
        alert("No matching results found");
    } else {
        $('#header-author').html(content.name);
//        getAffs(content.affiliations);
        $('#header-affiliation').html(content.aff)
        $('#heat-num').html(content.heat);
        $('#paper-num').html(content.documentCount);
        $('#cites-num').html(content.referenceCount);
        $('#papers-num').html(content.documents.length);
        $('#meeting-num').html(content.meetings.length);
        getPapers(content.documents);
        getKeywordCloud(content.directions);

        $('#tab-papers').on("click",function(){
            $('.page-tab').removeClass("page-tab-active");
            $('#tab-papers').addClass("page-tab-active");
            getPapers(content.documents);
        });

        $('#tab-meetings').on("click",function(){
            $('.page-tab').removeClass("page-tab-active");
            $('#tab-meetings').addClass("page-tab-active");
            getMeetings(content.meetings);
        });
    }
});

function getPapers(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var j=parseInt(i)+1;
        var paper=list[i];
        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(paper, "/PaperDetail?name="+paper) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
}

function getMeetings(list){
    var info="";
    console.log(list);
    for(let i=0;i<list.length;i++){
        var j=parseInt(i)+1;
        var meeting=list[i];
        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(meeting, "/meeting?meetingName="+meeting) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
}

function getAffs(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var aff=list[i];
        if(aff!=list[0]){
            info+='; ';
        }
        info += makeLink(aff,"/affiliation?affiliationName="+aff)
    }

    $('#header-affiliation').html(info);

}

function getKeywordCloud(map){
    let list = [];
    for (let keyword in map){
        list.push({
            name: keyword,
            value: map[keyword]
        })
    }
    var chart = echarts.init(document.getElementById('direction-detail'));
    chart.setOption({
        series: [{
            type: 'wordCloud',
            shape: 'circle',
            left: 'center',
            top: 'center',
            width: '70%',
            height: '80%',
            right: null,
            bottom: null,
            sizeRange: [12, 30],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 8,
            drawOutOfBound: true,
            textStyle: {
                normal: {
                    fontFamily: 'sans-serif',
                    fontWeight: 'bold',
                    color: function () {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 4,
                    shadowColor: '#333'
                }
            },
            data: list.map(item => {
                return {
                    name: item.name,
                    value: item.value,
                    url: "/searchPortrait/keyword?keyword="+item.name,
                }
            })
        }]
    });
}