function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function(){
    console.log("affiliation");
    var content = JSON.parse(sessionStorage.getItem('affiliationInfo'));
    console.log(content);
    if (content==null) {
        alert("No matching results found");
    } else {
        $('#header-affiliation').html(content.name);
        $('#heat-num').html(content.heat);
        $('#paper-num').html(content.documentCount);
        $('#scholar-num').html(content.authorCount);
        $('#cites-num').html(content.referenceCount);
        $('#list-author-num').html(content.authors.length);
        $('#list-paper-num').html(content.documents.length);
        $('#list-meeting-num').html(content.meetings.length);

        getAuthors(content.authors);
        getKeywordCloud(content.directions);

        $('#tab-authors').on("click",function(){
            $('.page-tab').removeClass("page-tab-active");
            $('#tab-authors').addClass("page-tab-active");
            getAuthors(content.authors);
        });
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

function getAuthors(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var j=parseInt(i)+1;
        var author=list[i];

        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(author, "/author?authorName="+author) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
}

function getPapers(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var paper=list[i];
        var j=parseInt(i)+1;

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
        var meeting=list[i];
        var j=parseInt(i)+1;

        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(meeting, "/meeting?meetingName="+meeting) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
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